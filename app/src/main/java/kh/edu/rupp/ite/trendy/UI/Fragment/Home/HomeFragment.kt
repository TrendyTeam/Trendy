package kh.edu.rupp.ite.trendy.UI.Fragment.Home

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.text.util.Linkify
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.interfaces.ItemClickListener
import com.denzcoskun.imageslider.models.SlideModel
import kh.edu.rupp.ite.trendy.Base.BaseFragmentBinding
import kh.edu.rupp.ite.trendy.Model.Entry.ProductModel.ProductListModel
import kh.edu.rupp.ite.trendy.Model.Repository.Product.ProductRepository
import kh.edu.rupp.ite.trendy.Service.api.MyApi
import kh.edu.rupp.ite.trendy.Service.network.NetworkConnectionInterceptor
import kh.edu.rupp.ite.trendy.UI.Adapter.NewProductItemAdapter
import kh.edu.rupp.ite.trendy.UI.Adapter.SaleProductItemAdapter
import kh.edu.rupp.ite.trendy.UI.Fragment.Detail.ProductDetail
import kh.edu.rupp.ite.trendy.ViewModel.ProductViewModel.ProductViewModel
import kh.edu.rupp.ite.trendy.ViewModel.ProductViewModel.ProductViewModelFactory
import kh.edu.rupp.ite.trendy.databinding.FragmentHomeBinding


class HomeFragment : BaseFragmentBinding<FragmentHomeBinding>() {

    private lateinit var networkConnectionInterceptor: NetworkConnectionInterceptor
    private lateinit var api: MyApi
    private lateinit var productRepository: ProductRepository
    private lateinit var factory: ProductViewModelFactory
    private var viewModel: ProductViewModel? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {

        networkConnectionInterceptor = NetworkConnectionInterceptor()
        api = MyApi(networkConnectionInterceptor, requireContext())
        productRepository = ProductRepository(api)
        factory = ProductViewModelFactory(productRepository)
        viewModel = ViewModelProvider(this, factory).get(ProductViewModel::class.java)
        viewModel?.getProductList()

        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun getViewBinding(): FragmentHomeBinding = FragmentHomeBinding.inflate(layoutInflater)

    override fun onViewCreated() {

        val imageSlider = binding.imageSlider
        val imageList = ArrayList<SlideModel>()

        // Add your image URLs and corresponding descriptions here
        imageList.add(SlideModel("https://pinetimeclothing.com/cdn/shop/products/16.9_LOOK_WMN_DUSTYMINT_LANDSCAPE_TEE_00.jpg?v=1652442703"))
        imageList.add(SlideModel("https://pbs.twimg.com/media/CfFezyVWAAAHxCZ.jpg"))
        imageSlider.setImageList(imageList, ScaleTypes.FIT)



        imageSlider.setItemClickListener(object : ItemClickListener {
            override fun doubleClick(position: Int) {
                // TODO: Handle double click if needed
            }

            override fun onItemSelected(position: Int) {
                // Handle click event here
                when (position) {
                    0 -> {
                        Log.d("DataClick", "Clicked on slider 0")
                    }

                    1 -> {
                        Log.d("DataClick", "Clicked on slider 1")
                    }
                }
            }
        })


        //get data form api
        viewModel?.productList?.observe(viewLifecycleOwner, Observer {
//            Log.d("PRODUCT", "Data: $it")
//            _productList.value = it
            binding.superSummerSaleRecyclerView.apply {
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                adapter = SaleProductItemAdapter(
                    context,
                    it,
                    object : SaleProductItemAdapter.OnClickListener {
                        override fun onClickProduct(data: ProductListModel.ProductListModelItem) {
                            val saleProductDetailBottomSheet =
                                ProductDetail(data.id.toString(), data.productName.toString())
                            saleProductDetailBottomSheet.show(
                                requireActivity().supportFragmentManager,
                                "sale_button_sheet_product_detail"
                            )
                        }

                    })
            }
        })

        viewModel?.productList?.observe(viewLifecycleOwner, Observer {
            binding.neverSeenBeforeRecyclerView.apply {
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                adapter = NewProductItemAdapter(
                    context,
                    it,
                    object : NewProductItemAdapter.OnClickListener {
                        override fun onClickListener(data: ProductListModel.ProductListModelItem) {
                            val productDetailBottomSheet =
                                ProductDetail(data.id.toString(), data.productName.toString())
                            productDetailBottomSheet.show(
                                requireActivity().supportFragmentManager,
                                "new_button_sheet_product_detail"
                            )
                        }
                    }
                )
            }
        })

        binding.gitHub.setOnClickListener {
            val url = "https://github.com/TrendyTeam/Trendy.git"
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            startActivity(intent)
        }

        
//        val imageList = ArrayList<SlideModel>() // Create image list
//        imageList.add(SlideModel("https://t.ly/aaa_", "The future is our hands."))
//        imageList.add(SlideModel("https://t.ly/sg91", "Climate change is moving very fast."))
//        imageList.add(SlideModel("https://t.ly/hqW3", "The population has decreased by 27 percent in the last 5 years."))
//
//        imageSlider.setImageList(imageList, ScaleTypes.CENTER_CROP)
//
//        imageSlider.setSlideAnimation(AnimationTypes.ZOOM_OUT)


    }


}