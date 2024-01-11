package kh.edu.rupp.ite.trendy.Model.Repository.Cart

import kh.edu.rupp.ite.trendy.Model.Entry.CartModel.CartModel
import kh.edu.rupp.ite.trendy.Service.SafeApiRequest
import kh.edu.rupp.ite.trendy.Service.api.MyApi

class CartRepository(private val api: MyApi) : SafeApiRequest() {


    suspend fun getCartByUser(id: String): CartModel {
        return apiRequest { api.getCart(id) }
    }
}