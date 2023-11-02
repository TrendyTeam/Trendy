package kh.edu.rupp.ite.trendy.Model.Entry


import com.google.gson.annotations.SerializedName

data class UserLoginErrorModel(
    @SerializedName("error")
    var error: Boolean?,
    @SerializedName("message")
    var message: String?,
    @SerializedName("messages")
    var messages: Messages?
) {
    data class Messages(
        @SerializedName("err")
        var err: String?
    )
}