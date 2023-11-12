package kh.edu.rupp.ite.trendy.Model.Repository.User

import kh.edu.rupp.ite.trendy.Model.DataBase.MySharedPreferences
import kh.edu.rupp.ite.trendy.Model.Entry.UserAuthModel.UserDetailModel
import kh.edu.rupp.ite.trendy.Model.Entry.UserAuthModel.UserLogInResponseModel
import kh.edu.rupp.ite.trendy.Model.Entry.UserAuthModel.UserLoginBody
import kh.edu.rupp.ite.trendy.Model.Entry.UserAuthModel.UserSignUpBody
import kh.edu.rupp.ite.trendy.Model.Entry.UserAuthModel.UserSignUpModel
import kh.edu.rupp.ite.trendy.Service.SafeApiRequest
import kh.edu.rupp.ite.trendy.Service.api.MyApi

class UserRepository(
    private val api: MyApi,
    private val sharedPreferences: MySharedPreferences
): SafeApiRequest() {

    suspend fun userLogIn(phone: String, password: String): UserLogInResponseModel {
        val bodyLogin = UserLoginBody(phone, password)
        return apiRequest { api.userLogin(bodyLogin) }
    }

    suspend fun userSignUp(username: String, phone: String, password: String, gender: Int):UserSignUpModel{
        val bodySignUp = UserSignUpBody(username,phone,gender,password)
        return apiRequest { api.userSignUp(bodySignUp) }
    }

    suspend fun saveToken(token: String) = sharedPreferences.saveToken(token)
    fun getToken(): String? {
        return sharedPreferences.getToken()
    }
    suspend fun clareToken() = sharedPreferences.clearToken()

    suspend fun getUserDetail() : UserDetailModel{
        return apiRequest { api.getUserDetail() }
    }

}