package kh.edu.rupp.ite.trendy.UI.Fragment.Shop

import android.os.Bundle
import android.view.animation.AnimationUtils
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import kh.edu.rupp.ite.trendy.Base.BaseFragmentBinding
import kh.edu.rupp.ite.trendy.Model.Entry.CategoryModel.SubCategoryModel
import kh.edu.rupp.ite.trendy.Model.Entry.SubCategoryHandleData
import kh.edu.rupp.ite.trendy.Model.Repository.Category.CategoryRepository
import kh.edu.rupp.ite.trendy.R
import kh.edu.rupp.ite.trendy.Service.api.MyApi
import kh.edu.rupp.ite.trendy.Service.network.NetworkConnectionInterceptor
import kh.edu.rupp.ite.trendy.UI.Adapter.ListSubCategoryAdapter
import kh.edu.rupp.ite.trendy.UI.Fragment.Detail.ProductListByCategory
import kh.edu.rupp.ite.trendy.ViewModel.shopViewModel.CategoryViewModel
import kh.edu.rupp.ite.trendy.ViewModel.shopViewModel.CategoryViewModelFactory
import kh.edu.rupp.ite.trendy.databinding.FragmentBaseSubCategoryBinding

class BaseSubCategoryFragment(private var idCategory:Int, private var categoryName: String) : BaseFragmentBinding<FragmentBaseSubCategoryBinding>() {
    override fun getViewBinding(): FragmentBaseSubCategoryBinding = FragmentBaseSubCategoryBinding.inflate(layoutInflater)
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
        viewModel?.getSubCategoryData(idCategory.toString())
    }
    override fun onViewCreated() {

        val animationLayout = AnimationUtils.loadLayoutAnimation(context, R.anim.layout_slide_item_scale)
        viewModel?.subCategoryData?.observe(viewLifecycleOwner, Observer {
            binding.recSubCategory.apply {
                layoutAnimation = animationLayout
                layoutManager = LinearLayoutManager(context)
                adapter = ListSubCategoryAdapter(context, it, object :ListSubCategoryAdapter.OnClickListenerItem{
                    override fun onItemClick(data: SubCategoryModel.SubCategoryModelItem) {
                        val productListByCategoryBottomSheet = ProductListByCategory(context, SubCategoryHandleData(
                            categoryId = idCategory.toString(),
                            categoryNameHandle = categoryName,
                            subCategoryId = data.id.toString(),
                            isViewByCategory = true,
                            isViewAllHandle = false,
                            subCategoryName = data.name
                        ))
                        productListByCategoryBottomSheet.show(requireActivity().supportFragmentManager, "bottom_sheet_productList_fragment")

                    }

                })
            }
        })

    }

}