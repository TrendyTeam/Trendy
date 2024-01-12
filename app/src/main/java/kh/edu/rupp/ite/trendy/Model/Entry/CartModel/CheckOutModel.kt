package kh.edu.rupp.ite.trendy.Model.Entry.CartModel


import com.google.gson.annotations.SerializedName

data class CheckOutModel(
    @SerializedName("message")
    var message: String = "",
    @SerializedName("orderDetails")
    var orderDetails: OrderDetails = OrderDetails()
) {
    data class OrderDetails(
        @SerializedName("items")
        var items: ArrayList<Item> = arrayListOf(),
        @SerializedName("totalAmount")
        var totalAmount: Double = 0.0
    ) {
        data class Item(
            @SerializedName("cart_id")
            var cartId: Int = 0,
            @SerializedName("color")
            var color: String = "",
            @SerializedName("color_code")
            var colorCode: String = "",
            @SerializedName("product_discount")
            var productDiscount: Int = 0,
            @SerializedName("product_item_id")
            var productItemId: Int = 0,
            @SerializedName("product_name")
            var productName: String = "",
            @SerializedName("product_price")
            var productPrice: Double = 0.0,
            @SerializedName("quantity")
            var quantity: Int = 0,
            @SerializedName("size")
            var size: String = ""
        )
    }
}