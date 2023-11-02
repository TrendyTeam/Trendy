package kh.edu.rupp.ite.trendy.Model.Entry.UserAuthModel


import com.google.gson.annotations.SerializedName

data class UserLogInResponseModel(
    @SerializedName("access_token")
    var accessToken: String?,
    @SerializedName("error")
    var error: Boolean?,
    @SerializedName("message")
    var message: String?,
    @SerializedName("messages")
    var messages: Messages?,
    @SerializedName("role")
    var role: String?,
    @SerializedName("token")
    var token: String?,
    @SerializedName("user")
    var user: User?
) {
    data class Messages(
        @SerializedName("err")
        var err: String?
    )

    data class User(
        @SerializedName("create_at")
        var createAt: String?,
        @SerializedName("email")
        var email: String?,
        @SerializedName("gender")
        var gender: Int?,
        @SerializedName("phone")
        var phone: String?,
        @SerializedName("role")
        var role: String?,
        @SerializedName("update_at")
        var updateAt: String?,
        @SerializedName("user_id")
        var userId: Int?,
        @SerializedName("username")
        var username: String?
    )
}