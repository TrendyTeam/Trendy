package kh.edu.rupp.ite.trendy.Model.Entry.UserAuthModel

data class AddToCartBody(
    private var user_id: Int,
    private var product_item_id: String,
    private var quantity: Int

)
