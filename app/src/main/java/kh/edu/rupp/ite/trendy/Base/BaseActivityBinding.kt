package kh.edu.rupp.ite.trendy.Base

import android.os.Bundle
import android.widget.FrameLayout
import androidx.core.content.ContextCompat
import androidx.viewbinding.ViewBinding
import com.danny.coremodule.CoreActivity
import com.danny.coremodule.CoreView
import com.google.android.material.snackbar.Snackbar
import kh.edu.rupp.ite.trendy.Application.TrendyApplication
import kh.edu.rupp.ite.trendy.R
import kh.edu.rupp.ite.trendy.databinding.ActivityBaseBinding

abstract class BaseActivityBinding<VB: ViewBinding>():CoreActivity(),CoreView {

    protected lateinit var binding: VB
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        super.setContentView(R.layout.activity_base)
        binding = getLayoutViewBinding()
        findViewById<FrameLayout>(R.id.content_view).addView(binding.root)
        initView()
    }
    override fun onInternetDisconnect() {
        TrendyApplication.isInternetConnected = false
        val snackBar = Snackbar.make(
            window.decorView.findViewById(android.R.id.content),
            getString(R.string.message_no_internet),
            Snackbar.LENGTH_INDEFINITE   // Offline issue
        )
        snackBar.view.setBackgroundColor(ContextCompat.getColor(this, R.color.internet_disconnect))
        snackBar.show()
    }

    override fun onInternetConnect() {
        if (!TrendyApplication.isInternetConnected) {
            TrendyApplication.isInternetConnected = true
            val snackBar = Snackbar.make(
                window.decorView.findViewById(android.R.id.content),
                "Internet Connected.",
                Snackbar.LENGTH_SHORT
            )
            snackBar.view.setBackgroundColor(ContextCompat.getColor(this, R.color.internet_connect))
            snackBar.show()
        }
    }


    abstract fun getLayoutViewBinding(): VB


}