package kh.edu.rupp.ite.trendy.UI.Fragment.Home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import kh.edu.rupp.ite.trendy.Base.BaseFragmentBinding
import kh.edu.rupp.ite.trendy.Model.DataBase.MySharedPreferences
import kh.edu.rupp.ite.trendy.Model.Entry.ProductModel.ProductListModel
import kh.edu.rupp.ite.trendy.Model.Repository.Product.ProductRepository
import kh.edu.rupp.ite.trendy.Model.Repository.User.UserRepository
import kh.edu.rupp.ite.trendy.Service.api.MyApi
import kh.edu.rupp.ite.trendy.Service.network.NetworkConnectionInterceptor
import kh.edu.rupp.ite.trendy.UI.Adapter.SaleProductItemAdapter
import kh.edu.rupp.ite.trendy.ViewModel.AuthViewModel.UserAuthViewModelFactory
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

    }


}