package kh.edu.rupp.ite.trendy.ViewModel.shopViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kh.edu.rupp.ite.trendy.Model.Entry.CategoryModel.SubCategoryModel
import kh.edu.rupp.ite.trendy.Model.Entry.CategoryModel.TopCategoryModel
import kh.edu.rupp.ite.trendy.Model.Entry.ProductModel.ListProductWithDetailByCategory
import kh.edu.rupp.ite.trendy.Model.Entry.ProductModel.OneProductModel
import kh.edu.rupp.ite.trendy.Model.Entry.UserAuthModel.AddToCartResponse
import kh.edu.rupp.ite.trendy.Model.Repository.Category.CategoryRepository
import kh.edu.rupp.ite.trendy.Util.ApiException
import kh.edu.rupp.ite.trendy.Util.Coroutines
import kh.edu.rupp.ite.trendy.Util.NoInternetException

class CategoryViewModel (
    private val categoryRepository: CategoryRepository
) :ViewModel() {

    var postListener: PostListener? = null

    private val _topCategoryData = MutableLiveData<TopCategoryModel>()
    val topCategoryData : LiveData<TopCategoryModel>
        get () = _topCategoryData

    private val _subCategoryData = MutableLiveData<SubCategoryModel>()

    val subCategoryData : LiveData<SubCategoryModel>
        get() = _subCategoryData

    private val _listProductByCategory = MutableLiveData<ListProductWithDetailByCategory>()
    val listProductByCategory : LiveData<ListProductWithDetailByCategory>
        get() = _listProductByCategory

    private val _productDetail = MutableLiveData<OneProductModel>()
    val productDetail : LiveData<OneProductModel>
        get() = _productDetail
    private val _addToCartResponse = MutableLiveData<AddToCartResponse>()
    val addToCartResponse : LiveData<AddToCartResponse>
        get() = _addToCartResponse
    fun addToCart(userid: Int, itemId: String, quantity:Int){
        Coroutines.main {
            try {
                val addToCart = categoryRepository.addToCart(userid,itemId,quantity)
                addToCart.message?.let {
                    postListener?.onSuccess(it)
                    return@main
                }
            }catch (e:ApiException){
                postListener?.onFail(e.message!!)
            }catch (e:NoInternetException){
                postListener?.onFail(e.message!!)
            }
        }
    }
    fun getTopCategoryData(){
        Coroutines.ioThanMain(
            {
                categoryRepository.getTopCategory()
            },
            {
                _topCategoryData.value = it
            }
        )
    }

    fun getSubCategoryData(id:String){
        Coroutines.ioThanMain(
            {
                categoryRepository.getSubCategory(id)
            },
            {
                _subCategoryData.value = it
            }
        )
    }

    fun getListProductByCategory(id: String){
        Coroutines.ioThanMain(
            {categoryRepository.getProductByCategory(id)},
            {_listProductByCategory.value = it}
        )
    }


    fun getProductByProductId(id: String){
        Coroutines.ioThanMain(
            {categoryRepository.getProductByProductId(id)},
            {_productDetail.value = it}
        )
    }


}