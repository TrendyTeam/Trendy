package kh.edu.rupp.ite.trendy.Model.Repository.Category

import kh.edu.rupp.ite.trendy.Model.Entry.CategoryModel.SubCategoryModel
import kh.edu.rupp.ite.trendy.Model.Entry.CategoryModel.TopCategoryModel
import kh.edu.rupp.ite.trendy.Model.Entry.ProductModel.ListProductWithDetailByCategory
import kh.edu.rupp.ite.trendy.Model.Entry.ProductModel.OneProductModel
import kh.edu.rupp.ite.trendy.Service.SafeApiRequest
import kh.edu.rupp.ite.trendy.Service.api.MyApi

class CategoryRepository(
    private val api: MyApi
) : SafeApiRequest() {

    suspend fun getTopCategory(): TopCategoryModel {
        return apiRequest { api.getTopCategory() }
    }

    suspend fun getSubCategory(id: String): SubCategoryModel {
        return apiRequest { api.getSubCategory(id) }
    }

    suspend fun getProductByCategory(id: String): ListProductWithDetailByCategory {
        return apiRequest { api.getProductByCategory(id) }
    }

    suspend fun getProductByProductId(id: String) : OneProductModel {
        return apiRequest { api.getOneProduct(id) }
    }


}