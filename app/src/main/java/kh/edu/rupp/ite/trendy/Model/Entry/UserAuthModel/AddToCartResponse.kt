package kh.edu.rupp.ite.trendy.Model.Entry.UserAuthModel


import com.google.gson.annotations.SerializedName

data class AddToCartResponse(
    @SerializedName("message")
    var message: String? = ""
)