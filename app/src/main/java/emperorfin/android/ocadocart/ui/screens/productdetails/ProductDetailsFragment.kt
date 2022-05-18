package emperorfin.android.ocadocart.ui.screens.productdetails

import android.app.Application
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import emperorfin.android.ocadocart.R
import emperorfin.android.ocadocart.databinding.FragmentProductDetailsBinding
import emperorfin.android.ocadocart.ui.screens.productdetails.viewmodels.ProductDetailsViewModel
import emperorfin.android.ocadocart.ui.screens.productdetails.viewmodels.ProductDetailsViewModelFactory
import emperorfin.android.ocadocart.ui.uimodels.ProductOverviewUiModel
import emperorfin.android.ocadocart.ui.utils.ErrorUtil
import androidx.lifecycle.Observer

/**
 * A simple [Fragment] subclass.
 */
class ProductDetailsFragment : Fragment() {

    private lateinit var mViewModel: ProductDetailsViewModel

    private var mSnackBar: Snackbar? = null

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

        mViewModel = getProductDetailsViewModel(application, selectedProductOverview)

        mViewModel.noInternetConnectionError.observe(viewLifecycleOwner, Observer {
            if (it != null && it == ProductDetailsViewModel.ERROR_CODE_NO_INTERNET_CONNECTION){
                mSnackBar = ErrorUtil.showError(
                    binding.rootLayout,
                    R.string.message_no_internet_connection,
                    R.string.message_try_again,
                    View.OnClickListener {
                        mViewModel.getProductDetailsRealDataViaRemoteDataSource(selectedProductOverview)
                    })
            }

            mViewModel.emitNoInternetConnectionError(null)
        })

        binding.viewModel = mViewModel

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()

        dismissSnackBar()
    }

    override fun onDetach() {
        super.onDetach()

        dismissSnackBar()
    }

    private fun getProductDetailsViewModel(
        application: Application, selectedProductOverview: ProductOverviewUiModel
    ): ProductDetailsViewModel {
        val viewModelFactory = ProductDetailsViewModelFactory(application, selectedProductOverview)

        return ViewModelProvider(this, viewModelFactory).get(ProductDetailsViewModel::class.java)
    }

    /**
     * Dismisses any SnackBar error message that is showing.
     */
    private fun dismissSnackBar() {
        mSnackBar?.let {
            it.dismiss()
            mSnackBar = null
        }
    }

}