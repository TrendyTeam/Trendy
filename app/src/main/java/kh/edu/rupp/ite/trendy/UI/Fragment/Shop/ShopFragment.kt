package kh.edu.rupp.ite.trendy.UI.Fragment.Shop

import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.tabs.TabLayoutMediator
import kh.edu.rupp.ite.trendy.Base.BaseFragmentBinding
import kh.edu.rupp.ite.trendy.Model.Entry.CategoryModel.TopCategoryModel
import kh.edu.rupp.ite.trendy.Model.Repository.Category.CategoryRepository
import kh.edu.rupp.ite.trendy.Service.api.MyApi
import kh.edu.rupp.ite.trendy.Service.network.NetworkConnectionInterceptor
import kh.edu.rupp.ite.trendy.Util.BaseTabBase
import kh.edu.rupp.ite.trendy.ViewModel.shopViewModel.CategoryViewModel
import kh.edu.rupp.ite.trendy.ViewModel.shopViewModel.CategoryViewModelFactory
import kh.edu.rupp.ite.trendy.databinding.FragmentShopBinding


class ShopFragment : BaseFragmentBinding<FragmentShopBinding>() {
    override fun getViewBinding(): FragmentShopBinding = FragmentShopBinding.inflate(layoutInflater)
    private lateinit var networkConnectionInterceptor : NetworkConnectionInterceptor
    private lateinit var api : MyApi
    private lateinit var categoryRepository : CategoryRepository
    private lateinit var factory : CategoryViewModelFactory
    private var viewModel : CategoryViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        networkConnectionInterceptor = NetworkConnectionInterceptor()
        api = MyApi(networkConnectionInterceptor, requireContext())
        categoryRepository = CategoryRepository(api)
        factory = CategoryViewModelFactory(categoryRepository)
        viewModel = ViewModelProvider(this, factory).get(CategoryViewModel::class.java)
        viewModel?.getTopCategoryData()
    }
    override fun onViewCreated() {

        viewModel?.topCategoryData?.observe(viewLifecycleOwner, Observer {
            Log.d("CATEGORY", "category = $it")
            setUpTab(it, BaseTabBase(requireActivity(),it))

        }) 


    }

    private fun setUpTab(data: TopCategoryModel, adapter: BaseTabBase){

        binding.vwSr.adapter = adapter
        val tabLayout = TabLayoutMediator(
            binding.tabSr,
            binding.vwSr
        ){tab, pos ->

            tab.text = data[pos].name

        }

        tabLayout.attach()

    }



}