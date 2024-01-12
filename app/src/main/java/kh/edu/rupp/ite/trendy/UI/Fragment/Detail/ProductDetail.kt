package kh.edu.rupp.ite.trendy.UI.Fragment.Detail

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.res.Resources
import android.graphics.Paint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.denzcoskun.imageslider.ImageSlider
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kh.edu.rupp.ite.trendy.Custom.AvailableColorDialog
import kh.edu.rupp.ite.trendy.Custom.AvailableSizeDialog
import kh.edu.rupp.ite.trendy.Model.Entry.ProductModel.OneProductModel
import kh.edu.rupp.ite.trendy.Model.Repository.Category.CategoryRepository
import kh.edu.rupp.ite.trendy.R
import kh.edu.rupp.ite.trendy.Service.api.MyApi
import kh.edu.rupp.ite.trendy.Service.network.NetworkConnectionInterceptor
import kh.edu.rupp.ite.trendy.Util.calculateDiscount
import kh.edu.rupp.ite.trendy.Util.toastHelper
import kh.edu.rupp.ite.trendy.Util.totalPriceFormat
import kh.edu.rupp.ite.trendy.ViewModel.shopViewModel.CategoryViewModel
import kh.edu.rupp.ite.trendy.ViewModel.shopViewModel.CategoryViewModelFactory
import kh.edu.rupp.ite.trendy.ViewModel.shopViewModel.PostListener
import kotlinx.android.synthetic.main.product_detail_layout.view.afterDiscountPriceDetail
import kotlinx.android.synthetic.main.product_detail_layout.view.btnBack
import kotlinx.android.synthetic.main.product_detail_layout.view.button_checkout
import kotlinx.android.synthetic.main.product_detail_layout.view.discountPerceDetail
import kotlinx.android.synthetic.main.product_detail_layout.view.original_priceDetail
import kotlinx.android.synthetic.main.product_detail_layout.view.product_color
import kotlinx.android.synthetic.main.product_detail_layout.view.product_description
import kotlinx.android.synthetic.main.product_detail_layout.view.product_name
import kotlinx.android.synthetic.main.product_detail_layout.view.product_size
import kotlinx.android.synthetic.main.product_detail_layout.view.titleCategory

class ProductDetail(private val productId: String, private val productName: String) : BottomSheetDialogFragment(), PostListener {
//    private var imageList : ArrayList<SlideModel>? = null
    private var viewModel: CategoryViewModel? = null
    private var imageSlider : ImageSlider? = null
    private var imageList : MutableList<SlideModel>? = null
    private var colorBtn : TextView? = null
    private var sizeBtn : TextView? = null
    private var sizePro : String? = ""
    private var colorPro: String? = ""
    private var itemId :String? = ""
    private var amountPro : Int? = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        imageList = mutableListOf<SlideModel>()
        val networkConnectionInterceptor = NetworkConnectionInterceptor()
        val api = MyApi(networkConnectionInterceptor, requireContext())
        val categoryRepository = CategoryRepository(api)
        val factory = CategoryViewModelFactory(categoryRepository)
        viewModel = ViewModelProvider(this, factory).get(CategoryViewModel::class.java)
        viewModel?.getProductByProductId(productId)
        viewModel?.postListener = this

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
        colorBtn = view.product_color
        sizeBtn = view.product_size
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
            initDialog(product!!.items!!)

        })


        view.button_checkout.setOnClickListener {
            if (itemId.isNullOrEmpty()){
                requireContext().toastHelper("Please select size and color!")
            }else{
                if (amountPro != 0){
                    viewModel?.addToCart(1,itemId!!,1)
                }else{
                    requireContext().toastHelper("This item sold out stock!")
                }
            }
        }



        return view
    }

    private fun initDialog(data: ArrayList<OneProductModel.Item>){
        sizeBtn?.setOnClickListener {
            AvailableSizeDialog(requireContext(),data, object : AvailableSizeDialog.OnCreateListener{
                override fun onSuccess(item: OneProductModel.Item) {
                    sizeBtn?.text = item.size
                    sizePro = item.size
                    itemId = ""
                }

            }).show()
        }
        colorBtn?.setOnClickListener {
            if (!sizePro.isNullOrEmpty()){
                val item = data.filter { it.size == sizePro } as ArrayList
               AvailableColorDialog(requireContext(), item, object : AvailableColorDialog.OnCreateListener{
                   override fun onSuccess(item: OneProductModel.Item) {
                       itemId = item.itemId.toString()
                       amountPro = item.amount

                   }

               }).show()

            }else{
                requireContext().toastHelper("Please Select Size!")
            }
        }
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

    override fun onSuccess(message: String) {
        requireContext().toastHelper(message)
    }

    override fun onFail(message: String) {
        requireContext().toastHelper(message)
    }
}