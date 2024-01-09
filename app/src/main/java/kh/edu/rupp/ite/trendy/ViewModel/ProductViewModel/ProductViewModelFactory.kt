package kh.edu.rupp.ite.trendy.ViewModel.ProductViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kh.edu.rupp.ite.trendy.Model.Repository.Product.ProductRepository

class ProductViewModelFactory(
    private val productRepository: ProductRepository,
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ProductViewModel::class.java)) {
            return ProductViewModel(productRepository) as T
        }

        throw IllegalArgumentException("View Model not found")
    }
}
