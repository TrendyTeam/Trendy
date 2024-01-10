package kh.edu.rupp.ite.trendy.UI.Fragment.Detail

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.res.Resources
import android.graphics.Paint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.denzcoskun.imageslider.ImageSlider
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kh.edu.rupp.ite.trendy.Model.Repository.Category.CategoryRepository
import kh.edu.rupp.ite.trendy.R
import kh.edu.rupp.ite.trendy.Service.api.MyApi
import kh.edu.rupp.ite.trendy.Service.network.NetworkConnectionInterceptor
import kh.edu.rupp.ite.trendy.Util.calculateDiscount
import kh.edu.rupp.ite.trendy.Util.totalPriceFormat
import kh.edu.rupp.ite.trendy.ViewModel.shopViewModel.CategoryViewModel
import kh.edu.rupp.ite.trendy.ViewModel.shopViewModel.CategoryViewModelFactory
import kotlinx.android.synthetic.main.product_detail_layout.view.afterDiscountPriceDetail
import kotlinx.android.synthetic.main.product_detail_layout.view.btnBack
import kotlinx.android.synthetic.main.product_detail_layout.view.discountPerceDetail
import kotlinx.android.synthetic.main.product_detail_layout.view.original_priceDetail
import kotlinx.android.synthetic.main.product_detail_layout.view.product_description
import kotlinx.android.synthetic.main.product_detail_layout.view.product_name
import kotlinx.android.synthetic.main.product_detail_layout.view.titleCategory

class ProductDetail(private val productId: String, private val productName: String) : BottomSheetDialogFragment() {
//    private var imageList : ArrayList<SlideModel>? = null
    private var viewModel: CategoryViewModel? = null
    private var imageSlider : ImageSlider? = null
    private var imageList : MutableList<SlideModel>? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        imageList = mutableListOf<SlideModel>()
        val networkConnectionInterceptor = NetworkConnectionInterceptor()
        val api = MyApi(networkConnectionInterceptor, requireContext())
        val categoryRepository = CategoryRepository(api)
        val factory = CategoryViewModelFactory(categoryRepository)
        viewModel = ViewModelProvider(this, factory).get(CategoryViewModel::class.java)
        viewModel?.getProductByProductId(productId)

    }

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.product_detail_layout, container, false)
        imageSlider = view.findViewById<ImageSlider>(R.id.product_image)
        view.titleCategory.text = productName;
        view.btnBack.setOnClickListener {
            dismiss()
        };

        viewModel?.productDetail?.observe(viewLifecycleOwner, Observer{product ->
            if (!product.productDiscount!!.equals(0.0)) {
                view.original_priceDetail.text = "US $${product.productPrice.toString()}"
                product.productDiscount?.let { view.discountPerceDetail.text = "- $it%" }
                view.afterDiscountPriceDetail.text = "$ ${totalPriceFormat(calculateDiscount(product.productDiscount!!, product.productPrice!!))}"
                view.original_priceDetail.paintFlags = view.original_priceDetail.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
                view.discountPerceDetail.visibility = View.VISIBLE
                view.afterDiscountPriceDetail.visibility = View.VISIBLE
            } else {
                view.original_priceDetail.text = "US $${product.productPrice.toString()}"
                view.discountPerceDetail.visibility = View.GONE
                view.afterDiscountPriceDetail.visibility = View.GONE
            }
            view.product_name.text = product.productName
            product.productDescription?.let { view.product_description.text= it }
            if (!product.image.isNullOrEmpty()){
                for (image in product.image!!){
                    initSliderImage(image!!.imageUrl!!)
                }
            }

        })


//        val imageSlider = view.image_slider
//        imageSlider.setImageList(imageList!!, ScaleTypes.FIT)

        return view
    }
    private fun initSliderImage(imageUrl: String){
        imageList!!.add(SlideModel(imageUrl))
        imageSlider?.setImageList(imageList!!, ScaleTypes.FIT)

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