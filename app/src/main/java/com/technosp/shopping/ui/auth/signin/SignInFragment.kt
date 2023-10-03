package com.shopping.app.ui.auth.signin

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.technosp.shopping.data.model.DataState
import com.technosp.shopping.data.model.User
import com.technosp.shopping.data.preference.UserPref
import com.technosp.shopping.data.repository.auth.AuthRepositoryImpl
import com.technosp.shopping.data.repository.user.UserRepositoryImpl
import com.technosp.shopping.ui.auth.signin.viewmodel.SignInViewModel
import com.technosp.shopping.ui.auth.signin.viewmodel.SignInViewModelFactory
import com.technosp.shopping.ui.loadingprogress.LoadingProgressBar
import com.technosp.shopping.utils.AlertMessageViewer.showAlertDialogMessage
import com.technosp.shopping.R
import com.technosp.shopping.databinding.FragmentSignInBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SignInFragment : Fragment() {

    private lateinit var bnd: FragmentSignInBinding
    private lateinit var loadingProgressBar: LoadingProgressBar
    private val viewModel: SignInViewModel by viewModels{
        SignInViewModelFactory(
            AuthRepositoryImpl(),
            UserRepositoryImpl()
        )
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        bnd = DataBindingUtil.inflate(inflater, R.layout.fragment_sign_in, container, false)
        return bnd.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init(){

        bnd.viewModel = viewModel
        loadingProgressBar = LoadingProgressBar(requireContext())

        viewModel.userLiveData.observe(viewLifecycleOwner){

            handleSignIn(it)

        }

    }

    private fun handleSignIn(it: DataState<User>){

        when(it){

            is DataState.Loading -> {
                loadingProgressBar.show()
            }

            is DataState.Success -> {
                loadingProgressBar.cancel()
                saveUserData(it.data)
            }

            is DataState.Error -> {
                loadingProgressBar.cancel()
                showAlertDialogMessage(requireContext(), it.message)
            }

        }

    }

    private fun saveUserData(user: User){

        val userPref = UserPref(requireContext())
        CoroutineScope(Dispatchers.Main).launch {

            userPref.setUsername(user.username!!)
            userPref.setEmail(user.email!!)

            findNavController().navigate(R.id.action_authFragment_to_mainMenuFragment)

        }

    }

}