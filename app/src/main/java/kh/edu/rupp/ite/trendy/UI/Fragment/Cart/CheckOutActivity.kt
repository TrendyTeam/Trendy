package kh.edu.rupp.ite.trendy.UI.Fragment.Cart

import android.content.Context
import android.content.Intent
import android.os.Bundle
import kh.edu.rupp.ite.trendy.Base.BaseActivityBinding
import kh.edu.rupp.ite.trendy.R
import kh.edu.rupp.ite.trendy.databinding.ActivityCheckOutBinding

class CheckOutActivity : BaseActivityBinding<ActivityCheckOutBinding>() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_check_out)
    }
    override fun getLayoutViewBinding(): ActivityCheckOutBinding = ActivityCheckOutBinding.inflate(layoutInflater)

    override fun initView() {

    }



    companion object{
        fun lunch(context: Context){
            val intent = Intent(context, CheckOutActivity::class.java)
            context.startActivity(intent)
        }
    }

}