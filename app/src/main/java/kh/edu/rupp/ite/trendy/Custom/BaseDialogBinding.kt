package kh.edu.rupp.ite.trendy.Custom

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.Gravity
import android.view.ViewGroup
import android.view.Window
import androidx.viewbinding.ViewBinding
import kh.edu.rupp.ite.trendy.R

abstract class BaseDialogBinding<VB : ViewBinding>(context: Context) : Dialog(context) {

    protected lateinit var binding: VB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        binding = setUpViewBinding()
        isCancelable()
        setContentView(binding.root)
        setCancelable(true)
        if (window != null) {
            window!!.setLayout(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
            window!!.attributes.windowAnimations = R.style.DialogAnimationScaleInOut
            window!!.setDimAmount(0.5f)
            window!!.setGravity(Gravity.CENTER)
            window!!.setBackgroundDrawable(isBackgroundDrawable())
        }
        onViewInit()
    }

    open fun isCancelable() = true
    open fun isBackgroundDrawable(): Nothing? = null
    protected open fun setContentBinding(): ViewBinding? = null
    abstract fun setUpViewBinding(): VB
    open fun onViewInit() {}
    override fun show() {
        if (!isShowing)
            super.show()
        else dismiss()
    }
}