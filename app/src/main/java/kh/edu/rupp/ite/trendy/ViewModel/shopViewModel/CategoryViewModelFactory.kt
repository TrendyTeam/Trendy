package kh.edu.rupp.ite.trendy.ViewModel.shopViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kh.edu.rupp.ite.trendy.Model.Repository.Category.CategoryRepository

class CategoryViewModelFactory(
    private val categoryRepository: CategoryRepository
):ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CategoryViewModel::class.java)){
            return CategoryViewModel(categoryRepository) as T
        }


        throw IllegalArgumentException("View Model not found")
    }

}