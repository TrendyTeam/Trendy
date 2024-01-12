package kh.edu.rupp.ite.trendy.Model.Repository.Cart

import kh.edu.rupp.ite.trendy.Model.Entry.CartModel.CartModel
import kh.edu.rupp.ite.trendy.Model.Entry.CartModel.CheckOutModel
import kh.edu.rupp.ite.trendy.Model.Entry.CartModel.DeleteCartModel
import kh.edu.rupp.ite.trendy.Model.Entry.UserAuthModel.CartItemDeleteModel
import kh.edu.rupp.ite.trendy.Service.SafeApiRequest
import kh.edu.rupp.ite.trendy.Service.api.MyApi

class CartRepository(private val api: MyApi) : SafeApiRequest() {

    suspend fun getCartByUser(id: String): CartModel {
        return apiRequest { api.getCart(id) }
    }

    suspend fun checkOutItem(): CheckOutModel {
        return apiRequest { api.checkOutCart() }
    }

    suspend fun deleteCart() : DeleteCartModel {
        return apiRequest { api.deleteAllCart() }
    }

    suspend fun deleteItemCart(userId:String, cartId:String):CartItemDeleteModel{
        return apiRequest { api.deleteCart(userId,cartId) }
    }
}