package kh.edu.rupp.ite.trendy.UI.Fragment.Cart

import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import kh.edu.rupp.ite.trendy.Base.BaseFragmentBinding
import kh.edu.rupp.ite.trendy.Custom.DialogX
import kh.edu.rupp.ite.trendy.Model.DataBase.MySharedPreferences
import kh.edu.rupp.ite.trendy.Model.Entry.CartModel.CartModel
import kh.edu.rupp.ite.trendy.Model.Repository.Cart.CartRepository
import kh.edu.rupp.ite.trendy.R
import kh.edu.rupp.ite.trendy.Service.api.MyApi
import kh.edu.rupp.ite.trendy.Service.network.NetworkConnectionInterceptor
import kh.edu.rupp.ite.trendy.UI.Adapter.CartAdapter
import kh.edu.rupp.ite.trendy.Util.toastHelper
import kh.edu.rupp.ite.trendy.ViewModel.CartViewModel.CartViewModel
import kh.edu.rupp.ite.trendy.ViewModel.CartViewModel.CartViewModelFactory
import kh.edu.rupp.ite.trendy.ViewModel.shopViewModel.PostListener
import kh.edu.rupp.ite.trendy.databinding.FragmentCartBinding


class CartFragment : BaseFragmentBinding<FragmentCartBinding>(), PostListener {

    private lateinit var networkConnectionInterceptor: NetworkConnectionInterceptor
    private lateinit var api: MyApi
    private lateinit var cartRepository: CartRepository
    private lateinit var factory: CartViewModelFactory
    private var viewModel: CartViewModel? = null

    override fun onViewCreated() {

        val sharePreferences = MySharedPreferences(requireContext())



    private var listCart : ArrayList<CartModel.Cart> = arrayListOf()
    private var userId = 0
    private var sharePreferences : MySharedPreferences? = null
    private var cartAdapter : CartAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        networkConnectionInterceptor = NetworkConnectionInterceptor()
        api = MyApi(networkConnectionInterceptor, requireContext())
        cartRepository = CartRepository(api)
        factory = CartViewModelFactory(cartRepository)
        viewModel = ViewModelProvider(this, factory).get(CartViewModel::class.java)
        sharePreferences = MySharedPreferences(requireContext())
        sharePreferences?.getUserId().let { viewModel?.getCartList(it.toString()) }
        sharePreferences?.getUserId().let{userId = it!! }
    }
    override fun onViewCreated() {


        viewModel?.cartList?.observe(viewLifecycleOwner, Observer {
            listCart = it.cart!!
            initRec()
        })

        binding.buttonCheckout.setOnClickListener{
            CheckOutActivity.lunch(requireContext())
        }


    }

    private fun initRec(){
        binding.cartData.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            cartAdapter = CartAdapter(context, listCart, object :CartAdapter.ClickListener{
                override fun onDelete(cartId: Int) {
                    DialogX(requireContext()).showQuestion(
                        requireContext().getString(R.string.are_you_sure),
                        requireContext().getString(R.string.delete_alert)
                    ){
                        viewModel?.deleteCartItem(userId.toString(), cartId.toString())
                        viewModel?.cartList?.observe(viewLifecycleOwner, Observer {
                            listCart = it.cart!!
                            adapter!!.notifyDataSetChanged()
                        })
                    }

                }

            })
            adapter = cartAdapter
        }
    }

    override fun getViewBinding(): FragmentCartBinding = FragmentCartBinding.inflate(layoutInflater)
    override fun onSuccess(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    override fun onFail(message: String) {
        context?.toastHelper(message)
    }


}