package kh.edu.rupp.ite.trendy.Service.network

import kh.edu.rupp.ite.trendy.Application.TrendyApplication
import kh.edu.rupp.ite.trendy.Util.NoInternetException
import okhttp3.Interceptor
import okhttp3.Response

class NetworkConnectionInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        if (!TrendyApplication.isInternetConnected)
            throw NoInternetException("Make sure you have an active data connection")


        return chain.proceed(chain.request())
    }


}