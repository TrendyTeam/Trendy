package kh.edu.rupp.ite.trendy.UI.Fragment.Cart

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import kh.edu.rupp.ite.trendy.Base.BaseActivityBinding
import kh.edu.rupp.ite.trendy.Model.Repository.Cart.CartRepository
import kh.edu.rupp.ite.trendy.R
import kh.edu.rupp.ite.trendy.Service.api.MyApi
import kh.edu.rupp.ite.trendy.Service.network.NetworkConnectionInterceptor
import kh.edu.rupp.ite.trendy.UI.Adapter.CartAdapter
import kh.edu.rupp.ite.trendy.UI.Adapter.CheckOutAdapter
import kh.edu.rupp.ite.trendy.Util.toastHelper
import kh.edu.rupp.ite.trendy.ViewModel.CartViewModel.CartViewModel
import kh.edu.rupp.ite.trendy.ViewModel.CartViewModel.CartViewModelFactory
import kh.edu.rupp.ite.trendy.ViewModel.shopViewModel.PostListener
import kh.edu.rupp.ite.trendy.databinding.ActivityCheckOutBinding
import kotlinx.android.synthetic.main.fragment_profile.back_btn

class CheckOutActivity : BaseActivityBinding<ActivityCheckOutBinding>(), PostListener {

    override fun getLayoutViewBinding(): ActivityCheckOutBinding =
        ActivityCheckOutBinding.inflate(layoutInflater)

    private lateinit var networkConnectionInterceptor: NetworkConnectionInterceptor
    private lateinit var api: MyApi
    private lateinit var cartRepository: CartRepository
    private lateinit var factory: CartViewModelFactory
    private var viewModel: CartViewModel? = null

    @SuppressLint("SetTextI18n")
    override fun initView() {

        networkConnectionInterceptor = NetworkConnectionInterceptor()
        api = MyApi(networkConnectionInterceptor, this)
        cartRepository = CartRepository(api)
        factory = CartViewModelFactory(cartRepository)
        viewModel = ViewModelProvider(this, factory).get(CartViewModel::class.java)
        viewModel?.getCheckoutData()


        viewModel?.checkOutDataList?.observe(this, Observer {
            binding.cartRecyclerView.apply {
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                adapter = CheckOutAdapter(
                    context, it.orderDetails.items
                )
            }

            binding.totalPriceData.text = "$ ${it.orderDetails.totalAmount}"
        })

        binding.btnBack.setOnClickListener {
            finish()
        }

        binding.buttonDone.setOnClickListener{
            viewModel?.orderCompleted()
            finish()
        }
    }

    companion object {
        fun lunch(context: Context) {
            val intent = Intent(context, CheckOutActivity::class.java)
            context.startActivity(intent)
        }
    }

    override fun onSuccess(message: String) {
        this.toastHelper(message)
    }

    override fun onFail(message: String) {
        this.toastHelper(message)
    }

}