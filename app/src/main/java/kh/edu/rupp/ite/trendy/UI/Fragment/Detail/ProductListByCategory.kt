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
import kh.edu.rupp.ite.trendy.ViewModel.shopViewModel.CategoryViewModel
import kh.edu.rupp.ite.trendy.ViewModel.shopViewModel.CategoryViewModelFactory
import kotlinx.android.synthetic.main.product_list_layout.view.btnBack
import kotlinx.android.synthetic.main.product_list_layout.view.list_change
import kotlinx.android.synthetic.main.product_list_layout.view.product_list
import kotlinx.android.synthetic.main.product_list_layout.view.subCategory_rec
import kotlinx.android.synthetic.main.product_list_layout.view.titleCategory

class ProductListByCategory(
    private val context: Context,
    private val handleDataModel: SubCategoryHandleData
) : BottomSheetDialogFragment() {

    private var title: TextView? = null
    private var backBtn: ImageView? = null
    private var recyclerViewSubCategory: RecyclerView? = null
    private var viewModel: CategoryViewModel? = null
    private var recyForProduct: RecyclerView? = null
    private var layoutChange: ImageView? = null
    private var subCategoryId: String? = ""
    private var subCategoryName: String? = ""
    private var grid = true
    private var gridLayoutManager: GridLayoutManager? = null
    private var adapterPro: ProductListAdapterForGridLayout? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val networkConnectionInterceptor = NetworkConnectionInterceptor()
        val api = MyApi(networkConnectionInterceptor, requireContext())
        val categoryRepository = CategoryRepository(api)
        val factory = CategoryViewModelFactory(categoryRepository)
        viewModel = ViewModelProvider(this, factory).get(CategoryViewModel::class.java)
        gridLayoutManager = GridLayoutManager(context, SPAN_COUNT_TWO)
        if (handleDataModel.isViewByCategory == true) {
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
        layoutChange = view.list_change
        recyclerViewSubCategory = view.subCategory_rec
        recyForProduct = view.product_list
        backBtn?.setOnClickListener { dismiss() }
        layoutChange?.setOnClickListener {
            grid = !grid
            switchLayout()

            if (grid) {
                layoutChange?.setImageResource(R.drawable.baseline_grid_view_24)

            } else {
                layoutChange?.setImageResource(R.drawable.baseline_view_list_24)
            }
        }

        title?.text = "${handleDataModel.categoryNameHandle}'s ${handleDataModel.subCategoryName}"
        if (handleDataModel.isViewByCategory == true) {
            viewModel?.subCategoryData?.observe(viewLifecycleOwner, Observer {
                initSubCategoryRec(it)
            })
        }
        viewModel?.listProductByCategory?.observe(viewLifecycleOwner, Observer {
            val dataX = ListProductWithDetailByCategory()
            var y = 1
            while (y != 10) {
                dataX.addAll(it)
                y++
            }
            adapterPro = ProductListAdapterForGridLayout(context, dataX, gridLayoutManager!!, object :
                    ProductListAdapterForGridLayout.OnProductClick {
                    override fun onClickProduct(product: ListProductWithDetailByCategory.ListProductWithDetailByCategoryItem) {
                        val saleProductDetailBottomSheet = ProductDetail(product.id.toString(), product.productName.toString())
                        saleProductDetailBottomSheet.show(requireActivity().supportFragmentManager, "sale_button_sheet_product_detail")
                    }

                })
            initProductRecGrid()
        })
        return view
    }

    private fun switchLayout() {
        if (gridLayoutManager?.spanCount == SPAN_COUNT_ONE) {
            gridLayoutManager?.spanCount = SPAN_COUNT_TWO
        } else {
            gridLayoutManager?.spanCount = SPAN_COUNT_ONE
        }

        adapterPro?.notifyItemRangeChanged(0, adapterPro!!.itemCount)

    }

    private fun initProductRecGrid() {
        recyForProduct?.apply {
            layoutManager = gridLayoutManager
            adapter = adapterPro
        }
    }

    private fun initSubCategoryRec(data: SubCategoryModel) {
        recyclerViewSubCategory?.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = SubCategoryListHorizontalAdapter(
                context,
                data,
                object : SubCategoryListHorizontalAdapter.OnSubCategoryClick {
                    @SuppressLint("SetTextI18n")
                    override fun onItemClick(data: SubCategoryModel.SubCategoryModelItem) {
                        viewModel?.getListProductByCategory(data.id.toString())
                        title?.text = "${handleDataModel.categoryNameHandle}'s ${data.name}"
                    }

                })
        }

    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)

        dialog.setOnShowListener { dialogInterface ->
            val bottomSheetDialog = dialogInterface as BottomSheetDialog
            val bottomSheet =
                bottomSheetDialog.findViewById<View>(com.google.android.material.R.id.design_bottom_sheet)
            bottomSheet?.let {
                val behavior = BottomSheetBehavior.from(it)
                behavior.peekHeight = Resources.getSystem().displayMetrics.heightPixels
            }
        }

        return dialog
    }


    companion object {
        const val SPAN_COUNT_ONE = 1
        const val SPAN_COUNT_TWO = 2

    }

}