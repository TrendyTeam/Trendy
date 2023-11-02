package kh.edu.rupp.ite.trendy.ViewModel.AuthViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kh.edu.rupp.ite.trendy.Model.Repository.User.UserRepository

class UserAuthViewModelFactory(
    private val userRepository: UserRepository
):ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UserAuthViewModel::class.java)){
            return UserAuthViewModel(userRepository) as T
        }

        throw IllegalArgumentException("View Model not found")
    }
}