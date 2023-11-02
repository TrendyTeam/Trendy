package kh.edu.rupp.ite.trendy.ViewModel.AuthViewModel

import kh.edu.rupp.ite.trendy.Model.Entry.UserLogInResponseModel

interface UserAuthListener {
    fun onStartAuth()
    fun onSuccessAuth(user: UserLogInResponseModel.User)
    fun onFailAuth(message:String)
}