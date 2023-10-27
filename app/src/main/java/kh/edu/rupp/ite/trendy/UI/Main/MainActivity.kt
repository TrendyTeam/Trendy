package kh.edu.rupp.ite.trendy.UI.Main

import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import kh.edu.rupp.ite.trendy.Base.BaseActivityBinding
import kh.edu.rupp.ite.trendy.R
import kh.edu.rupp.ite.trendy.UI.Fragment.Cart.CartFragment
import kh.edu.rupp.ite.trendy.UI.Fragment.Favorite.FavoriteFragment
import kh.edu.rupp.ite.trendy.UI.Fragment.Home.HomeFragment
import kh.edu.rupp.ite.trendy.UI.Fragment.Profile.ProfileFragment
import kh.edu.rupp.ite.trendy.UI.Fragment.Shop.ShopFragment
import kh.edu.rupp.ite.trendy.databinding.ActivityMainBinding

class MainActivity : BaseActivityBinding<ActivityMainBinding>() {
    private var STATE = 0
    override fun getLayoutViewBinding(): ActivityMainBinding = ActivityMainBinding.inflate(layoutInflater)
    override fun initView() {
        setStatusBarColor(ActivityCompat.getColor(this, R.color.indicator))
        initBottomNavigate()

    }


    private fun initBottomNavigate(){
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
                    showFragment(ProfileFragment())
                    STATE = 4
                    true
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