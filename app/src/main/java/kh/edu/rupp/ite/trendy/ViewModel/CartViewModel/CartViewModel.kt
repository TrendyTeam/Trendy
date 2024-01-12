package kh.edu.rupp.ite.trendy.ViewModel.CartViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kh.edu.rupp.ite.trendy.Model.Entry.CartModel.CartModel
import kh.edu.rupp.ite.trendy.Model.Entry.CartModel.CheckOutModel
import kh.edu.rupp.ite.trendy.Model.Repository.Cart.CartRepository
import kh.edu.rupp.ite.trendy.Util.Coroutines
import kh.edu.rupp.ite.trendy.ViewModel.shopViewModel.PostListener

class CartViewModel(private val cartRepository: CartRepository) : ViewModel() {

    private val _cartList = MutableLiveData<CartModel>()
    var postListener : PostListener? = null
    val cartList: LiveData<CartModel>
        get() = _cartList




    private val _checkOutDataList = MutableLiveData<CheckOutModel>()
    val checkOutDataList: LiveData<CheckOutModel>
        get() = _checkOutDataList

    private val _deleteItem = MutableLiveData<String>()
    val deleteIte : LiveData<String>
        get() = _deleteItem

    fun getCartList(id: String) {
        Coroutines.ioThanMain(
            {
                cartRepository.getCartByUser(id)
            },
            {
                _cartList.value = it
            }
        )
    }

    fun deleteCartItem(userid:String, cartId:String){
        Coroutines.ioThanMain(
            {
                cartRepository.deleteItemCart(userid,cartId)

            },
            {
                _deleteItem.value = it!!.message!!
            }
        )
    }

    fun getCheckoutData() {
        Coroutines.ioThanMain(
            {
                cartRepository.checkOutItem()
            },
            {
                _checkOutDataList.value = it
            }
        )
    }
}