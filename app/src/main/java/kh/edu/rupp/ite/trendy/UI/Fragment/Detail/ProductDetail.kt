package kh.edu.rupp.ite.trendy.UI.Fragment.Detail

import android.app.Dialog
import android.content.res.Resources
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kh.edu.rupp.ite.trendy.Model.Entry.ProductModel.OneProductModel
import kh.edu.rupp.ite.trendy.Model.Repository.Category.CategoryRepository
import kh.edu.rupp.ite.trendy.R
import kh.edu.rupp.ite.trendy.Service.api.MyApi
import kh.edu.rupp.ite.trendy.Service.network.NetworkConnectionInterceptor
import kh.edu.rupp.ite.trendy.ViewModel.shopViewModel.CategoryViewModel
import kh.edu.rupp.ite.trendy.ViewModel.shopViewModel.CategoryViewModelFactory
import kotlinx.android.synthetic.main.fragment_home.view.image_slider
import kotlinx.android.synthetic.main.item_product_single_list_layout.productName
import kotlinx.android.synthetic.main.product_detail_layout.view.btnBack
import kotlinx.android.synthetic.main.product_detail_layout.view.product_description
import kotlinx.android.synthetic.main.product_detail_layout.view.product_name
import kotlinx.android.synthetic.main.product_detail_layout.view.product_original_price
import kotlinx.android.synthetic.main.product_detail_layout.view.titleCategory
import kotlin.math.log

class ProductDetail(private val productId: String, private val productName: String) :

    BottomSheetDialogFragment() {
//    private var imageList : ArrayList<SlideModel>? = null
        private var viewModel: CategoryViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val networkConnectionInterceptor = NetworkConnectionInterceptor()
        val api = MyApi(networkConnectionInterceptor, requireContext())
        val categoryRepository = CategoryRepository(api)
        val factory = CategoryViewModelFactory(categoryRepository)
        viewModel = ViewModelProvider(this, factory).get(CategoryViewModel::class.java)


        viewModel?.getProductByProductId(productId)

//        imageList = ArrayList<SlideModel>()
//
//        imageList?.add(SlideModel("https://images.unsplash.com/photo-1489987707025-afc232f7ea0f?q=80&w=3540&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"))
//        imageList?.add(SlideModel("https://imageio.forbes.com/blogs-images/msolomon/files/2015/05/0504_armani-suit-2013-wolf-of-wall-street_1200x675-1152x648.jpg?format=jpg&height=900&width=1600&fit=bounds"))

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.product_detail_layout, container, false)

        view.titleCategory.text = productName;
        view.btnBack.setOnClickListener {
            dismiss()
        };

        viewModel?.productDetail?.observe(viewLifecycleOwner, Observer{
            Log.d("DATA11111", "DATA11111 = $it")
            view.product_name.text = it.productName
            view.product_original_price.text = it.productPrice.toString()
            view.product_description.text = it.productDescription
        })

//        val imageSlider = view.image_slider
//        imageSlider.setImageList(imageList!!, ScaleTypes.FIT)

        return view
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
}