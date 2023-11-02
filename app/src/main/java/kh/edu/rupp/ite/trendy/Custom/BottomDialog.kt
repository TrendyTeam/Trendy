package kh.edu.rupp.ite.trendy.Custom

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import androidx.viewbinding.ViewBinding

abstract class BottomDialog<VB: ViewBinding>(context: Context): Dialog(context)  {
    private lateinit var binding: VB
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = setUpViewBinding()
    }






    abstract fun setUpViewBinding(): VB
}