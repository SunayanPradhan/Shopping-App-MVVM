package com.technosp.shopping.ui.auth.signup.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.technosp.shopping.data.repository.auth.AuthRepository
import com.technosp.shopping.data.repository.user.UserRepository

class SignUpViewModelFactory(
    private val authRepository: AuthRepository,
    private val userRepository: UserRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return SignUpViewModel(authRepository, userRepository) as T
    }
}