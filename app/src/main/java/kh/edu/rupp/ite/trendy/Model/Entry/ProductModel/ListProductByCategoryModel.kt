package kh.edu.rupp.ite.trendy.Model.Entry.ProductModel


import com.google.gson.annotations.SerializedName


class ListProductByCategoryModel : ArrayList<ListProductByCategoryModel.ListProductByCategoryModelItem>(){

    data class ListProductByCategoryModelItem(
        @SerializedName("category_id")
        var categoryId: Int = 0,
        @SerializedName("created_at")
        var createdAt: String = "",
        @SerializedName("description")
        var description: String = "",
        @SerializedName("id")
        var id: Int = 0,
        @SerializedName("product_discount")
        var productDiscount: Any = Any(),
        @SerializedName("product_name")
        var productName: String = "",
        @SerializedName("product_price")
        var productPrice: Int = 0,
        @SerializedName("updated_at")
        var updatedAt: String = ""
    )
}