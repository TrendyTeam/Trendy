package kh.edu.rupp.ite.trendy.Model.Entry.ProductModel


import com.google.gson.annotations.SerializedName

class ProductListModel : ArrayList<ProductListModel.ProductListModelItem>(){
    data class ProductListModelItem(
        @SerializedName("amount")
        var amount: Int?,
        @SerializedName("available_color")
        var availableColor: List<AvailableColor?>?,
        @SerializedName("available_size")
        var availableSize: List<AvailableSize?>?,
        @SerializedName("category_id")
        var categoryId: Int?,
        @SerializedName("id")
        var id: Int?,
        @SerializedName("image")
        var image: List<Any?>?,
        @SerializedName("items")
        var items: List<Item?>?,
        @SerializedName("product_discount")
        var productDiscount: Int?,
        @SerializedName("product_name")
        var productName: String?,
        @SerializedName("product_price")
        var productPrice: Double?
    ) {
        data class AvailableColor(
            @SerializedName("amount")
            var amount: Int?,
            @SerializedName("color")
            var color: String?,
            @SerializedName("color_code")
            var colorCode: String?
        )
    
        data class AvailableSize(
            @SerializedName("amount")
            var amount: Int?,
            @SerializedName("color_onSize")
            var colorOnSize: List<ColorOnSize?>?,
            @SerializedName("size")
            var size: String?
        ) {
            data class ColorOnSize(
                @SerializedName("amount")
                var amount: Int?,
                @SerializedName("color")
                var color: String?,
                @SerializedName("color_code")
                var colorCode: String?
            )
        }
    
        data class Item(
            @SerializedName("amount")
            var amount: Int?,
            @SerializedName("color")
            var color: String?,
            @SerializedName("color_code")
            var colorCode: String?,
            @SerializedName("item_id")
            var itemId: Int?,
            @SerializedName("size")
            var size: String?
        )
    }
}