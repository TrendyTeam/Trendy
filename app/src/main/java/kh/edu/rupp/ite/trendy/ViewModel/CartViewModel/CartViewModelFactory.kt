package kh.edu.rupp.ite.trendy.ViewModel.CartViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kh.edu.rupp.ite.trendy.Model.Repository.Cart.CartRepository

class CartViewModelFactory(private val cartRepository: CartRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CartViewModel::class.java)) {
            return CartViewModel(cartRepository) as T
        }

        throw IllegalArgumentException("View Model not Found")
    }
}