package kh.edu.rupp.ite.trendy.ViewModel.AuthViewModel

import androidx.lifecycle.ViewModel
import kh.edu.rupp.ite.trendy.Model.Repository.User.UserRepository
import kh.edu.rupp.ite.trendy.Util.ApiException
import kh.edu.rupp.ite.trendy.Util.Coroutines
import kh.edu.rupp.ite.trendy.Util.NoInternetException

class UserAuthViewModel(
    private val userRepository: UserRepository
):ViewModel() {

    var authListener:UserAuthListener? = null

    fun userLogIn(phone: String, password: String){
        authListener?.onStartAuth()
        if (phone.isNullOrEmpty() || password.isNullOrEmpty()){
            authListener?.onFailAuth("Invalid Phone or Password")
            return
        }
        Coroutines.main {
            try {
                val authRepository = userRepository.userLogIn(phone, password)
                authRepository.user.let {
                    authListener?.onSuccessAuth(it!!)
                }
            }catch (e: ApiException){
                authListener?.onFailAuth(e.message!!)
            }
            catch (e: NoInternetException){
                authListener?.onFailAuth(e.message!!)
            }
        }
    }



}