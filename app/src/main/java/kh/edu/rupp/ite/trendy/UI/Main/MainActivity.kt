package kh.edu.rupp.ite.trendy.UI.Main

import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kh.edu.rupp.ite.trendy.Base.BaseActivityBinding
import kh.edu.rupp.ite.trendy.Model.DataBase.MySharedPreferences
import kh.edu.rupp.ite.trendy.Model.Repository.User.UserRepository
import kh.edu.rupp.ite.trendy.R
import kh.edu.rupp.ite.trendy.Service.api.MyApi
import kh.edu.rupp.ite.trendy.Service.network.NetworkConnectionInterceptor
import kh.edu.rupp.ite.trendy.UI.Auth.LoginBottomSheetFragment
import kh.edu.rupp.ite.trendy.UI.Fragment.Cart.CartFragment
import kh.edu.rupp.ite.trendy.UI.Fragment.Favorite.FavoriteFragment
import kh.edu.rupp.ite.trendy.UI.Fragment.Home.HomeFragment
import kh.edu.rupp.ite.trendy.UI.Fragment.Profile.ProfileFragment
import kh.edu.rupp.ite.trendy.UI.Fragment.Shop.ShopFragment
import kh.edu.rupp.ite.trendy.Util.logCus
import kh.edu.rupp.ite.trendy.ViewModel.AuthViewModel.UserAuthViewModel
import kh.edu.rupp.ite.trendy.ViewModel.AuthViewModel.UserAuthViewModelFactory
import kh.edu.rupp.ite.trendy.databinding.ActivityMainBinding

class MainActivity : BaseActivityBinding<ActivityMainBinding>() {
    private var STATE = 0
    override fun getLayoutViewBinding(): ActivityMainBinding = ActivityMainBinding.inflate(layoutInflater)
    override fun initView() {
        setStatusBarColor(ActivityCompat.getColor(this, R.color.indicator))
        val networkConnectionInterceptor = NetworkConnectionInterceptor()
        val api = MyApi(networkConnectionInterceptor)
        val sharedPreferences = MySharedPreferences(this)
        val userRepository = UserRepository(api, sharedPreferences)
        val factory = UserAuthViewModelFactory(userRepository)
        val viewModel = ViewModelProvider(this, factory).get(UserAuthViewModel::class.java)
        viewModel.loadToken()
        var token = viewModel.token.observe(this, Observer { token ->
            logCus("token = $token")
        })

        initBottomNavigate(viewModel)

    }


    private fun initBottomNavigate(viewModel: UserAuthViewModel){
        binding.bottomNavigation.setOnItemSelectedListener { item->
            when(item.itemId){
                R.id.home ->{
                    showFragment(HomeFragment())
                    STATE = 0
                    true
                }
                R.id.shop -> {
                    showFragment(ShopFragment())
                    STATE = 1
                    true
                }
                R.id.cart ->{
                    showFragment(CartFragment())
                    STATE = 2
                    true
                }
                R.id.favorite ->{
                    showFragment(FavoriteFragment())
                    STATE = 3
                    true
                }
                R.id.profile ->{
                    viewModel.token.observe(this, Observer {token ->
                        STATE = if (token.isEmpty()){
                            val bottomSheet = LoginBottomSheetFragment(this, this@MainActivity)
                            bottomSheet.show(supportFragmentManager, "bottom_sheet_login_fragment")
                            4
                        } else{
                            val bottomSheet = ProfileFragment(this, this@MainActivity)
                            bottomSheet.show(supportFragmentManager, "bottom_sheet_profile_fragment")
                            4
                        }
                    })

                    false
                }
                else ->{
                    false
                }
            }

        }
    }


    private fun showFragment(fragment:Fragment){
        val fragmentManger = supportFragmentManager
        val fragmentTransaction = fragmentManger.beginTransaction()
        fragmentTransaction.replace((R.id.container), fragment )
        fragmentTransaction.commit()
    }


    override fun onResume() {
        super.onResume()

        when(STATE){
            0 ->{
                binding.bottomNavigation.selectedItemId = R.id.home
            }
            1 -> {
                binding.bottomNavigation.selectedItemId = R.id.shop
            }
            2 -> {
                binding.bottomNavigation.selectedItemId = R.id.cart
            }
            3 -> {
                binding.bottomNavigation.selectedItemId = R.id.favorite
            }
            4 -> {
                binding.bottomNavigation.selectedItemId = R.id.profile
            }
        }
    }

}