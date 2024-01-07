package kh.edu.rupp.ite.trendy.Model.Entry.CategoryModel


import com.google.gson.annotations.SerializedName

class TopCategoryModel : ArrayList<TopCategoryModel.TopCategoryModelItem>(){

    data class TopCategoryModelItem(
        @SerializedName("created_at")
        var createdAt: String = "",
        @SerializedName("description")
        var description: Any = Any(),
        @SerializedName("id")
        var id: Int = 0,
        @SerializedName("image_url")
        var imageUrl: String = "",
        @SerializedName("name")
        var name: String = "",
        @SerializedName("parent_id")
        var parentId: Any = Any(),
        @SerializedName("updated_at")
        var updatedAt: String = ""
    )
}