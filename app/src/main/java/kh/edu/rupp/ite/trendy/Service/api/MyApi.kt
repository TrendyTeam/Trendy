package kh.edu.rupp.ite.trendy.Service.api

import kh.edu.rupp.ite.trendy.Model.Entry.UserAuthModel.UserLogInResponseModel
import kh.edu.rupp.ite.trendy.Model.Entry.UserAuthModel.UserLoginBody
import kh.edu.rupp.ite.trendy.Model.Entry.UserAuthModel.UserSignUpBody
import kh.edu.rupp.ite.trendy.Model.Entry.UserAuthModel.UserSignUpModel
import kh.edu.rupp.ite.trendy.Service.network.NetworkConnectionInterceptor
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST

interface MyApi {

    //user logIn
    @POST("user/login/user")
    suspend fun userLogin(
        @Body info: UserLoginBody
    ): Response<UserLogInResponseModel>

    //user signUp
    @POST("user/create")
    suspend fun userSignUp(
        @Body info: UserSignUpBody
    ):Response<UserSignUpModel>




    companion object{
        operator fun invoke(
            networkConnectionInterceptor: NetworkConnectionInterceptor
        ) :MyApi{
            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(networkConnectionInterceptor)
                .build()
            return Retrofit.Builder()
                .baseUrl("http://10.0.2.2:5001/api/")
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(MyApi::class.java)
        }
    }
}