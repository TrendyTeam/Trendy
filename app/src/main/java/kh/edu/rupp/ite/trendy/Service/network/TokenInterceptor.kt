package kh.edu.rupp.ite.trendy.Service.network

import android.content.Context
import kh.edu.rupp.ite.trendy.Model.DataBase.MySharedPreferences
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class TokenInterceptor(context: Context):Interceptor {
    private var token : String? = null
    private val sharedPreferences = MySharedPreferences(context)
    override fun intercept(chain: Interceptor.Chain): Response {
        token = sharedPreferences.getToken()
        val originalRequest = chain.request()
        val modifiedRequest: Request = originalRequest.newBuilder()
            .header("Authorization", "Bearer $token")
            .build()
        return chain.proceed(modifiedRequest)
    }


}