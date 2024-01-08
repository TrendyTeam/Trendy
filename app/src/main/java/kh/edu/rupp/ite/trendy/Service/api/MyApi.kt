package kh.edu.rupp.ite.trendy.Service.api

import android.content.Context
import kh.edu.rupp.ite.trendy.Model.Entry.CategoryModel.SubCategoryModel
import kh.edu.rupp.ite.trendy.Model.Entry.CategoryModel.TopCategoryModel
import kh.edu.rupp.ite.trendy.Model.Entry.ProductModel.ProductListModel
import kh.edu.rupp.ite.trendy.Model.Entry.UserAuthModel.UserDetailModel
import kh.edu.rupp.ite.trendy.Model.Entry.UserAuthModel.UserLogInResponseModel
import kh.edu.rupp.ite.trendy.Model.Entry.UserAuthModel.UserLoginBody
import kh.edu.rupp.ite.trendy.Model.Entry.UserAuthModel.UserSignUpBody
import kh.edu.rupp.ite.trendy.Model.Entry.UserAuthModel.UserSignUpModel
import kh.edu.rupp.ite.trendy.Service.network.NetworkConnectionInterceptor
import kh.edu.rupp.ite.trendy.Service.network.TokenInterceptor
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

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
    ): Response<UserSignUpModel>

    @GET("user/get-one")
    suspend fun getUserDetail(): Response<UserDetailModel>
    @GET("categories")
    suspend fun getTopCategory(): Response<TopCategoryModel>

    @GET("categories/{id}/subcategories")
    suspend fun getSubCategory(
        @Path("id") id: String
    ): Response<SubCategoryModel>
    @GET("products")
    suspend fun getProductList() : Response<ProductListModel>


    companion object {
        operator fun invoke(
            networkConnectionInterceptor: NetworkConnectionInterceptor,
            context: Context
        ): MyApi {
            val tokenInterceptor = TokenInterceptor(context)
            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(networkConnectionInterceptor)
                .addInterceptor(tokenInterceptor)
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