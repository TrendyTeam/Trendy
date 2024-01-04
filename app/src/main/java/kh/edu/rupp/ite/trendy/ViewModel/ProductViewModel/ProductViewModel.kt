package kh.edu.rupp.ite.trendy.ViewModel.ProductViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kh.edu.rupp.ite.trendy.Model.Entry.ProductModel.ProductListModel
import kh.edu.rupp.ite.trendy.Model.Repository.Product.ProductRepository
import kh.edu.rupp.ite.trendy.Util.Coroutines

class ProductViewModel(
    private val productRepository: ProductRepository
) : ViewModel() {

    private  val _productList = MutableLiveData<ProductListModel>()

    val productList: LiveData<ProductListModel>
        get() = _productList

    fun getProductList() {
        Coroutines.ioThanMain(
            {
                productRepository.getProductList()
            },
            {
                _productList.value = it
            }
        )
    }
}