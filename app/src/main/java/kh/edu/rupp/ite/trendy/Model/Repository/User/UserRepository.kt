package kh.edu.rupp.ite.trendy.Model.Repository.User

import kh.edu.rupp.ite.trendy.Model.Entry.UserAuthModel.UserLogInResponseModel
import kh.edu.rupp.ite.trendy.Model.Entry.UserAuthModel.UserLoginBody
import kh.edu.rupp.ite.trendy.Service.SafeApiRequest
import kh.edu.rupp.ite.trendy.Service.api.MyApi

class UserRepository(
    private val api: MyApi
): SafeApiRequest() {

    suspend fun userLogIn(phone: String, password: String): UserLogInResponseModel {
        val bodyLogin = UserLoginBody(phone, password)
        return apiRequest { api.userLogin(bodyLogin) }
    }

}