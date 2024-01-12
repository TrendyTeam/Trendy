package kh.edu.rupp.ite.trendy.ViewModel.CartViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kh.edu.rupp.ite.trendy.Model.Entry.CartModel.CartModel
import kh.edu.rupp.ite.trendy.Model.Entry.CartModel.CheckOutModel
import kh.edu.rupp.ite.trendy.Model.Repository.Cart.CartRepository
import kh.edu.rupp.ite.trendy.Util.ApiException
import kh.edu.rupp.ite.trendy.Util.Coroutines
import kh.edu.rupp.ite.trendy.Util.NoInternetException
import kh.edu.rupp.ite.trendy.ViewModel.shopViewModel.PostListener

class CartViewModel(private val cartRepository: CartRepository) : ViewModel() {

    private val _cartList = MutableLiveData<CartModel>()
    var postListener : PostListener? = null
    val cartList: LiveData<CartModel>
        get() = _cartList


    private val _checkOutDataList = MutableLiveData<CheckOutModel>()
    val checkOutDataList: LiveData<CheckOutModel>
        get() = _checkOutDataList


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
        Coroutines.main {
            try {
                val deleteCart = cartRepository.deleteItemCart(userid,cartId)
                deleteCart.message?.let {
                    postListener?.onSuccess(it)
                    return@main
                }
            }catch (e:ApiException){
                postListener?.onFail(e.message.toString())
            }catch (e:NoInternetException){
                postListener?.onFail(e.message.toString())
            }
        }
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