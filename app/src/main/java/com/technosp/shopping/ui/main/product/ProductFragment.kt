package com.technosp.shopping.ui.main.product

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.technosp.shopping.R
import com.technosp.shopping.data.api.ApiClient
import com.technosp.shopping.data.model.DataState
import com.technosp.shopping.data.repository.product.ProductRepositoryImpl
import com.technosp.shopping.databinding.FragmentProductBinding
import com.technosp.shopping.ui.loadingprogress.LoadingProgressBar
import com.technosp.shopping.ui.main.product.adapter.ProductAdapter
import com.technosp.shopping.ui.main.product.viewmodel.ProductViewModel
import com.technosp.shopping.ui.main.product.viewmodel.ProductViewModelFactory

class ProductFragment : Fragment() {

    private lateinit var bnd: FragmentProductBinding
    private lateinit var productAdapter: ProductAdapter
    private lateinit var loadingProgressBar: LoadingProgressBar
    private val viewModel by viewModels<ProductViewModel> {
        ProductViewModelFactory(
            ProductRepositoryImpl(
                ApiClient.getApiService()
            )
        )
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        bnd = DataBindingUtil.inflate(inflater, R.layout.fragment_product, container, false)
        return bnd.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loadingProgressBar = LoadingProgressBar(requireContext())

        viewModel.productLiveData.observe(viewLifecycleOwner){

            when (it) {
                is DataState.Success -> {
                    loadingProgressBar.hide()
                    it.data?.let { safeData ->

                        productAdapter = ProductAdapter(requireContext(), safeData, findNavController())
                        bnd.gridViewProduct.adapter = productAdapter

                    } ?: run {
                        Snackbar.make(bnd.root, getString(R.string.no_data), Snackbar.LENGTH_LONG).show()
                    }
                }
                is DataState.Error -> {
                    loadingProgressBar.hide()
                    Snackbar.make(bnd.root, it.message, Snackbar.LENGTH_LONG).show()
                }
                is DataState.Loading -> {
                    loadingProgressBar.show()
                }
            }

        }

    }

}