package kh.edu.rupp.ite.trendy.UI.Fragment.Detail

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Context
import android.content.res.Resources
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kh.edu.rupp.ite.trendy.Model.Entry.CategoryModel.SubCategoryModel
import kh.edu.rupp.ite.trendy.Model.Entry.ProductModel.ListProductWithDetailByCategory
import kh.edu.rupp.ite.trendy.Model.Entry.SubCategoryHandleData
import kh.edu.rupp.ite.trendy.Model.Repository.Category.CategoryRepository
import kh.edu.rupp.ite.trendy.R
import kh.edu.rupp.ite.trendy.Service.api.MyApi
import kh.edu.rupp.ite.trendy.Service.network.NetworkConnectionInterceptor
import kh.edu.rupp.ite.trendy.UI.Adapter.ProductListAdapterForGridLayout
import kh.edu.rupp.ite.trendy.UI.Adapter.SubCategoryListHorizontalAdapter
import kh.edu.rupp.ite.trendy.Util.toastHelper
import kh.edu.rupp.ite.trendy.ViewModel.shopViewModel.CategoryViewModel
import kh.edu.rupp.ite.trendy.ViewModel.shopViewModel.CategoryViewModelFactory
import kotlinx.android.synthetic.main.product_list_layout.view.btnBack
import kotlinx.android.synthetic.main.product_list_layout.view.product_list
import kotlinx.android.synthetic.main.product_list_layout.view.subCategory_rec
import kotlinx.android.synthetic.main.product_list_layout.view.titleCategory

class ProductListByCategory(private val context: Context, private val handleDataModel: SubCategoryHandleData):BottomSheetDialogFragment() {

    private var title: TextView? = null
    private var backBtn: ImageView? = null
    private var recyclerViewSubCategory : RecyclerView? = null
    private var viewModel : CategoryViewModel? = null
    private var recyForProduct: RecyclerView? = null
    private var subCategoryId:String? = ""
    private var subCategoryName: String? = ""



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val networkConnectionInterceptor = NetworkConnectionInterceptor()
        val api = MyApi(networkConnectionInterceptor, requireContext())
        val categoryRepository = CategoryRepository(api)
        val factory = CategoryViewModelFactory(categoryRepository)
        viewModel = ViewModelProvider(this, factory).get(CategoryViewModel::class.java)

        if (handleDataModel.isViewByCategory == true){
            viewModel?.getSubCategoryData(handleDataModel.categoryId!!)
            viewModel?.getListProductByCategory(handleDataModel.subCategoryId!!)
        }


    }

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.product_list_layout, container, false)
        title = view.titleCategory
        backBtn = view.btnBack
        recyclerViewSubCategory = view.subCategory_rec
        recyForProduct = view.product_list
        title?.text = "${handleDataModel.categoryNameHandle}'s ${handleDataModel.subCategoryName}"
        if (handleDataModel.isViewByCategory == true){
            viewModel?.subCategoryData?.observe(viewLifecycleOwner, Observer {
                recyclerViewSubCategory?.apply {
                    layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                    adapter = SubCategoryListHorizontalAdapter(context, it, object :SubCategoryListHorizontalAdapter.OnSubCategoryClick{
                        override fun onItemClick(data: SubCategoryModel.SubCategoryModelItem) {
                            viewModel?.getListProductByCategory(data.id.toString())
                        }

                    })
                }
            })
        }
        backBtn?.setOnClickListener { dismiss() }

        viewModel?.listProductByCategory?.observe(viewLifecycleOwner, Observer {
            recyForProduct?.apply {
                layoutManager = GridLayoutManager(context, 2, GridLayoutManager.VERTICAL, false)
                adapter = ProductListAdapterForGridLayout(context,it,object :ProductListAdapterForGridLayout.OnProductClick{
                    override fun onClickProduct(product: ListProductWithDetailByCategory.ListProductWithDetailByCategoryItem) {
                        context.toastHelper("click on ${product.productName}")
                    }

                })
            }
        })



        return view
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)

        dialog.setOnShowListener{ dialogInterface ->
            val bottomSheetDialog = dialogInterface as BottomSheetDialog
            val bottomSheet = bottomSheetDialog.findViewById<View>(com.google.android.material.R.id.design_bottom_sheet)
            bottomSheet?.let {
                val behavior = BottomSheetBehavior.from(it)
                behavior.peekHeight = Resources.getSystem().displayMetrics.heightPixels
            }
        }

        return dialog
    }



}