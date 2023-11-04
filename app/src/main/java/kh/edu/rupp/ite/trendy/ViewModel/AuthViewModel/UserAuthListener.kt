package kh.edu.rupp.ite.trendy.ViewModel.AuthViewModel

import kh.edu.rupp.ite.trendy.Model.Entry.UserAuthModel.UserLogInResponseModel
import kh.edu.rupp.ite.trendy.Model.Entry.UserAuthModel.UserSignUpModel

interface UserAuthListener {
    fun onStartAuth()
    fun onSuccessAuth(user: UserLogInResponseModel.User)
    fun onFailAuth(message:String)
    fun onSignUpSuccess(model: UserSignUpModel)
}