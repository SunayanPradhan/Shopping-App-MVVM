package com.technosp.shopping.ui.auth.authbase.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.technosp.shopping.data.model.TabLayoutFragment
import com.shopping.app.ui.auth.signin.SignInFragment
import com.technosp.shopping.ui.auth.signup.SignUpFragment
import com.technosp.shopping.R

class AuthBaseViewModel : ViewModel() {

    val fragmentLiveData = MutableLiveData<TabLayoutFragment>()

    init {
        initFragments()
    }

    private fun initFragments(){

        val fragments = TabLayoutFragment(
            listOf(
                SignInFragment(),
                SignUpFragment()
            ),
            listOf(
                R.string.signin,
                R.string.signup
            )
        )

        fragmentLiveData.value = fragments

    }

}