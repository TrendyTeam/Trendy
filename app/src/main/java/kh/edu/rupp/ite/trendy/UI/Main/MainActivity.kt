package kh.edu.rupp.ite.trendy.UI.Main

import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import kh.edu.rupp.ite.trendy.Base.BaseActivityBinding
import kh.edu.rupp.ite.trendy.R
import kh.edu.rupp.ite.trendy.UI.Fragment.Cart.CartFragment
import kh.edu.rupp.ite.trendy.UI.Fragment.Home.HomeFragment
import kh.edu.rupp.ite.trendy.databinding.ActivityMainBinding

class MainActivity : BaseActivityBinding<ActivityMainBinding>() {
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
                    true
                }
                R.id.cart ->{
                    showFragment(CartFragment())
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




}