package emperorfin.android.ocadocart.ui.screens.productdetails

import android.app.Application
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import emperorfin.android.ocadocart.R
import emperorfin.android.ocadocart.databinding.FragmentProductDetailsBinding
import emperorfin.android.ocadocart.ui.screens.productdetails.viewmodels.ProductDetailsViewModel
import emperorfin.android.ocadocart.ui.screens.productdetails.viewmodels.ProductDetailsViewModelFactory
import emperorfin.android.ocadocart.ui.uimodels.ProductOverviewUiModel

/**
 * A simple [Fragment] subclass.
 */
class ProductDetailsFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val selectedProductOverview =
            ProductDetailsFragmentArgs.fromBundle(requireArguments()).selectedProductOverview

        val binding: FragmentProductDetailsBinding = FragmentProductDetailsBinding.inflate(inflater)
        binding.lifecycleOwner = this

        val application = requireNotNull(activity).application

        val viewModel = getProductDetailsViewModel(application, selectedProductOverview)

        binding.viewModel = viewModel

        return binding.root
    }

    private fun getProductDetailsViewModel(
        application: Application, selectedProductOverview: ProductOverviewUiModel
    ): ProductDetailsViewModel {
        val viewModelFactory = ProductDetailsViewModelFactory(application, selectedProductOverview)

        return ViewModelProvider(this, viewModelFactory).get(ProductDetailsViewModel::class.java)
    }

}