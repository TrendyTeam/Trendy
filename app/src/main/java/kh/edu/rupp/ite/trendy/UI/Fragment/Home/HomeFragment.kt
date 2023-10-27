package kh.edu.rupp.ite.trendy.UI.Fragment.Home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kh.edu.rupp.ite.trendy.Base.BaseFragmentBinding
import kh.edu.rupp.ite.trendy.R
import kh.edu.rupp.ite.trendy.databinding.FragmentHomeBinding


class HomeFragment : BaseFragmentBinding<FragmentHomeBinding>() {

    override fun getViewBinding(): FragmentHomeBinding = FragmentHomeBinding.inflate(layoutInflater)

    override fun onViewCreated() {

    }


}