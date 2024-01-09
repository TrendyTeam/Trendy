package kh.edu.rupp.ite.trendy.Model.Repository.Product

import kh.edu.rupp.ite.trendy.Model.Entry.ProductModel.OneProductModel
import kh.edu.rupp.ite.trendy.Service.SafeApiRequest
import kh.edu.rupp.ite.trendy.Service.api.MyApi

class OneProductRepository(
    private val api: MyApi
) : SafeApiRequest() {

    suspend fun getOneProductData() : OneProductModel {
        return apiRequest { api.getOneProduct() }
    }

}