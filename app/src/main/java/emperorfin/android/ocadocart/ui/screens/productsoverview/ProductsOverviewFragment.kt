package emperorfin.android.ocadocart.ui.screens.productsoverview

import android.app.Application
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import emperorfin.android.ocadocart.R
import emperorfin.android.ocadocart.databinding.FragmentProductsOverviewBinding
import emperorfin.android.ocadocart.ui.events.outputs.EventDataImpl
import emperorfin.android.ocadocart.ui.screens.productsoverview.adapters.ProductOverviewUiModelRecyclerviewAdapter
import emperorfin.android.ocadocart.ui.screens.productsoverview.viewmodels.ProductsOverviewViewModel
import emperorfin.android.ocadocart.ui.screens.productsoverview.viewmodels.ProductsOverviewViewModelFactory
import emperorfin.android.ocadocart.ui.uimodels.ProductOverviewUiModel
import emperorfin.android.ocadocart.ui.utils.ErrorUtil
import androidx.lifecycle.Observer
import java.util.*

/**
 * A simple [Fragment] subclass.
 */
class ProductsOverviewFragment : Fragment() {

    private lateinit var mViewModel: ProductsOverviewViewModel

    private var mSnackBar: Snackbar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding =
            DataBindingUtil.inflate<FragmentProductsOverviewBinding>(
                inflater, R.layout.fragment_products_overview, container, false
            )

        val application = requireNotNull(this.activity).application

        mViewModel = getProductsOverviewViewModel(application)

        mViewModel.noInternetConnectionError.observe(viewLifecycleOwner, Observer {
            if (it != null && it == ProductsOverviewViewModel.ERROR_CODE_NO_INTERNET_CONNECTION){
                mSnackBar = ErrorUtil.showError(
                    binding.rootLayout,
                    R.string.message_no_internet_connection,
                    R.string.message_try_again,
                    View.OnClickListener {
                        mViewModel.loadProductsOverviews()
                    })
            }

            mViewModel.emitNoInternetConnectionError(null)
        })

        binding.lifecycleOwner = this
        binding.viewModel = mViewModel

        val productOverviewAdapterOnClickListener = ProductOverviewUiModelRecyclerviewAdapter.OnClickListener{
//            showToastMessage(
//                "Product Name: \"${it.title.toUpperCase(Locale.ROOT)}\""
//            )

            mViewModel.openProductDetails(it)
        }

        setupProductsOverviewAdapter(binding, productOverviewAdapterOnClickListener)

        setupNavigation()

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

    private fun getProductsOverviewViewModel(application: Application): ProductsOverviewViewModel{
        val viewModelFactory = ProductsOverviewViewModelFactory(application)

        return ViewModelProvider(this, viewModelFactory).get(ProductsOverviewViewModel::class.java)
    }

    private fun setupProductsOverviewAdapter(
        binding: FragmentProductsOverviewBinding,
        productOverviewAdapterOnClickListener: ProductOverviewUiModelRecyclerviewAdapter.OnClickListener
    ){
        binding.rvProductsOverviews.adapter =
            ProductOverviewUiModelRecyclerviewAdapter(productOverviewAdapterOnClickListener)
    }

    private fun setupNavigation(){
        mViewModel.openProductDetailsEvent.observe(viewLifecycleOwner, EventDataImpl.EventDataImplObserver{
            openProductDetails(it)
        })
    }

    private fun openProductDetails(productOverview: ProductOverviewUiModel){
        val action =
            ProductsOverviewFragmentDirections
                .actionProductsOverviewFragmentToProductDetailsFragment(productOverview)

        this.findNavController().navigate(action)

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

    private fun showToastMessage(message: String) =
        Toast.makeText(requireActivity(), message, Toast.LENGTH_SHORT).show()
}