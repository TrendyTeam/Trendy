package kh.edu.rupp.ite.trendy.Model.Entry.UserAuthModel


import com.google.gson.annotations.SerializedName


data class CartItemDeleteModel(
    @SerializedName("message")
    var message: String? = ""
)