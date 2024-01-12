package kh.edu.rupp.ite.trendy.Model.Entry.CartModel


import com.google.gson.annotations.SerializedName

data class OrderCompleteModel(
    @SerializedName("message")
    var message: String = ""
)