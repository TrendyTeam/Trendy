package kh.edu.rupp.ite.trendy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kh.edu.rupp.ite.trendy.Base.BaseActivityBinding
import kh.edu.rupp.ite.trendy.databinding.ActivityMainBinding

class MainActivity : BaseActivityBinding<ActivityMainBinding>() {
    override fun getLayoutViewBinding(): ActivityMainBinding = ActivityMainBinding.inflate(layoutInflater)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

}