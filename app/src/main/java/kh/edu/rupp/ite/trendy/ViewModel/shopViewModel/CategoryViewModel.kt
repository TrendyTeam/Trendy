package kh.edu.rupp.ite.trendy.ViewModel.shopViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kh.edu.rupp.ite.trendy.Model.Entry.CategoryModel.SubCategoryModel
import kh.edu.rupp.ite.trendy.Model.Entry.CategoryModel.TopCategoryModel
import kh.edu.rupp.ite.trendy.Model.Entry.ProductModel.ListProductWithDetailByCategory
import kh.edu.rupp.ite.trendy.Model.Entry.ProductModel.OneProductModel
import kh.edu.rupp.ite.trendy.Model.Repository.Category.CategoryRepository
import kh.edu.rupp.ite.trendy.Util.Coroutines

class CategoryViewModel (
    private val categoryRepository: CategoryRepository
) :ViewModel() {

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