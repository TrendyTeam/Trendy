package kh.edu.rupp.ite.trendy.UI.Activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import kh.edu.rupp.ite.trendy.R
import kh.edu.rupp.ite.trendy.UI.Main.MainActivity

class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        Handler().postDelayed({
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }, 5000)
    }



    companion object{
        fun lunch(context: Context){
            val intent = Intent(context, SplashScreenActivity::class.java)
            context.startActivity(intent)
        }
    }
}