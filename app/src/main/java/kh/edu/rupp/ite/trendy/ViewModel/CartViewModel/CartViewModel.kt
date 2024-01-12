package kh.edu.rupp.ite.trendy.ViewModel.CartViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kh.edu.rupp.ite.trendy.Model.Entry.CartModel.CartModel
import kh.edu.rupp.ite.trendy.Model.Repository.Cart.CartRepository
import kh.edu.rupp.ite.trendy.Util.Coroutines

class CartViewModel (private val cartRepository: CartRepository) : ViewModel() {

    private val _cartList = MutableLiveData<CartModel>()

    val cartList: LiveData<CartModel>
        get() = _cartList



    fun getCartList(id : String) {
        Coroutines.ioThanMain(
            {
                cartRepository.getCartByUser(id)
            },
            {
                _cartList.value = it
            }
        )
    }
}