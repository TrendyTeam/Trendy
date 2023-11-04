package kh.edu.rupp.ite.trendy.Model.Entry.UserAuthModel

data class UserSignUpBody(
    private var username: String,
    private var phone: String,
    private var gender:Int,
    private var password: String
)
