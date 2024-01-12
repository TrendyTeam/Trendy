package kh.edu.rupp.ite.trendy.UI.Fragment.Cart

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import kh.edu.rupp.ite.trendy.Base.BaseFragmentBinding
import kh.edu.rupp.ite.trendy.Model.DataBase.MySharedPreferences
import kh.edu.rupp.ite.trendy.Model.Entry.CartModel.CartModel
import kh.edu.rupp.ite.trendy.Model.Repository.Cart.CartRepository
import kh.edu.rupp.ite.trendy.Service.api.MyApi
import kh.edu.rupp.ite.trendy.Service.network.NetworkConnectionInterceptor
import kh.edu.rupp.ite.trendy.UI.Adapter.CartAdapter
import kh.edu.rupp.ite.trendy.ViewModel.CartViewModel.CartViewModel
import kh.edu.rupp.ite.trendy.ViewModel.CartViewModel.CartViewModelFactory
import kh.edu.rupp.ite.trendy.databinding.FragmentCartBinding


class CartFragment : BaseFragmentBinding<FragmentCartBinding>() {

    private lateinit var networkConnectionInterceptor: NetworkConnectionInterceptor
    private lateinit var api: MyApi
    private lateinit var cartRepository: CartRepository
    private lateinit var factory: CartViewModelFactory
    private var viewModel: CartViewModel? = null
    private var listCart : ArrayList<CartModel.Cart> = arrayListOf()
    private var userId = 0
    private var sharePreferences : MySharedPreferences? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        networkConnectionInterceptor = NetworkConnectionInterceptor()
        api = MyApi(networkConnectionInterceptor, requireContext())
        cartRepository = CartRepository(api)
        factory = CartViewModelFactory(cartRepository)
        viewModel = ViewModelProvider(this, factory).get(CartViewModel::class.java)
    }
    override fun onViewCreated() {
        sharePreferences = MySharedPreferences(requireContext())
        sharePreferences?.getUserId().let { viewModel?.getCartList(it.toString()) }
        viewModel?.cartList?.observe(viewLifecycleOwner, Observer {
            listCart = it.cart!!
            initRec()
        })


    }

    private fun initRec(){
        binding.cartData.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = CartAdapter(context, listCart)
        }
    }

    override fun getViewBinding(): FragmentCartBinding = FragmentCartBinding.inflate(layoutInflater)


}