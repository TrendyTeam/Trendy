package kh.edu.rupp.ite.trendy.UI.Fragment.Cart

import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import kh.edu.rupp.ite.trendy.Base.BaseFragmentBinding
import kh.edu.rupp.ite.trendy.Model.DataBase.MySharedPreferences
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

    override fun onViewCreated() {

        val sharePreferences = MySharedPreferences(requireContext())


        networkConnectionInterceptor = NetworkConnectionInterceptor()
        api = MyApi(networkConnectionInterceptor, requireContext())
        cartRepository = CartRepository(api)
        factory = CartViewModelFactory(cartRepository)
        viewModel = ViewModelProvider(this, factory).get(CartViewModel::class.java)
        sharePreferences.getUserId()?.let { viewModel?.getCartList(it) }


        viewModel?.cartList?.observe(viewLifecycleOwner, Observer {
//            Log.d("CART", "CART DATA = $it" )
            binding.cartData.apply {
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                adapter = CartAdapter(
                    context, it.cart
                )
            }

        })


    }

    override fun getViewBinding(): FragmentCartBinding = FragmentCartBinding.inflate(layoutInflater)


}