package kh.edu.rupp.ite.trendy.Model.Repository.Product

import kh.edu.rupp.ite.trendy.Model.Entry.ProductModel.ProductListModel
import kh.edu.rupp.ite.trendy.Service.SafeApiRequest
import kh.edu.rupp.ite.trendy.Service.api.MyApi

class ProductRepository(
    private val api: MyApi,
) : SafeApiRequest() {

    suspend fun getProductList(): ProductListModel {
        return  apiRequest { api.getProductList() }
    }


}