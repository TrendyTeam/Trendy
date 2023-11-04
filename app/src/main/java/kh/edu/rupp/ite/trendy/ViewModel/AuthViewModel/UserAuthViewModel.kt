package kh.edu.rupp.ite.trendy.ViewModel.AuthViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kh.edu.rupp.ite.trendy.Model.Repository.User.UserRepository
import kh.edu.rupp.ite.trendy.Util.ApiException
import kh.edu.rupp.ite.trendy.Util.Coroutines
import kh.edu.rupp.ite.trendy.Util.NoInternetException
import kh.edu.rupp.ite.trendy.Util.logCus

class UserAuthViewModel(
    private val userRepository: UserRepository
):ViewModel() {
    private val _token = MutableLiveData<String>()
    val token : LiveData<String>
        get() = _token
    var authListener:UserAuthListener? = null

    //get user token
     fun loadToken(){
        val token = userRepository.getToken()
        _token.value = token ?: ""
    }

    private suspend fun saveToken(token:String){
        userRepository.saveToken(token)
        loadToken()
    }

    suspend fun clearToken(){
        userRepository.clareToken()
        loadToken()
    }


    fun userLogIn(phone: String, password: String){
        authListener?.onStartAuth()
        if (phone.isEmpty() || password.isEmpty()){
            authListener?.onFailAuth("Invalid Phone or Password")
            return
        }
        Coroutines.main {
            try {
                val authRepository = userRepository.userLogIn(phone, password)
                authRepository.user.let {
                    authListener?.onSuccessAuth(it!!)
                    saveToken(authRepository.accessToken!!)
                    return@main
                }
            }catch (e: ApiException){
                authListener?.onFailAuth(e.message!!)
            }
            catch (e: NoInternetException){
                authListener?.onFailAuth(e.message!!)
            }
        }
    }

    fun userSignUp(username: String, phone: String, password: String, gender: Int){
        authListener?.onStartAuth()
        if (phone.isEmpty() || password.isEmpty() || username.isEmpty() || gender == 0){
            authListener?.onFailAuth("Please complete all field.")
            return
        }
        Coroutines.main {
            try {
                val authRepository = userRepository.userSignUp(username,phone,password,gender)
                authRepository.error.let {
                    if (it == false){
                       authListener?.onSignUpSuccess(authRepository)
                        return@main
                    }
                }
            }catch (e:ApiException){
                authListener?.onFailAuth(e.message!!)
            }
            catch (e:NoInternetException){
                authListener?.onFailAuth(e.message!!)
            }
        }
    }

    fun userClearToken(){
        Coroutines.main {
            try {
                clearToken()
            }catch (e:Exception){
                logCus("${e.message}")
            }
        }
    }



}