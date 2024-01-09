package kh.edu.rupp.ite.trendy.UI.Fragment.Home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel
import kh.edu.rupp.ite.trendy.Base.BaseFragmentBinding
import kh.edu.rupp.ite.trendy.Model.Entry.ProductModel.ProductListModel
import kh.edu.rupp.ite.trendy.Model.Repository.Product.ProductRepository
import kh.edu.rupp.ite.trendy.Service.api.MyApi
import kh.edu.rupp.ite.trendy.Service.network.NetworkConnectionInterceptor
import kh.edu.rupp.ite.trendy.UI.Adapter.NewProductItemAdapter
import kh.edu.rupp.ite.trendy.UI.Adapter.SaleProductItemAdapter
import kh.edu.rupp.ite.trendy.UI.Fragment.Detail.ProductDetail
import kh.edu.rupp.ite.trendy.Util.toastHelper
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
        imageList.add(SlideModel("https://images.unsplash.com/photo-1489987707025-afc232f7ea0f?q=80&w=3540&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"))
        imageList.add(SlideModel("https://imageio.forbes.com/blogs-images/msolomon/files/2015/05/0504_armani-suit-2013-wolf-of-wall-street_1200x675-1152x648.jpg?format=jpg&height=900&width=1600&fit=bounds"))
        imageSlider.setImageList(imageList, ScaleTypes.FIT)

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
                        override fun onClickProduct(
                            data: ProductListModel.ProductListModelItem,
                            position: Int
                        ) {
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
                        override fun onClickListener(
                            data: ProductListModel.ProductListModelItem,
                            position: Int
                        ) {
                            val productDetailBottomSheet = ProductDetail(data.id.toString())
                            productDetailBottomSheet.show(
                                requireActivity().supportFragmentManager,
                                "button_sheet_product_detail"
                            )
                            context.toastHelper("Hhhh")
                        }
                    }
                )
            }
        })


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