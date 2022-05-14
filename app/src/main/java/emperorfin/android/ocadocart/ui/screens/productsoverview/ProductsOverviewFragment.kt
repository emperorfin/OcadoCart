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
import emperorfin.android.ocadocart.R
import emperorfin.android.ocadocart.databinding.FragmentProductsOverviewBinding
import emperorfin.android.ocadocart.ui.screens.productsoverview.adapters.ProductOverviewUiModelRecyclerviewAdapter
import emperorfin.android.ocadocart.ui.screens.productsoverview.viewmodels.ProductsOverviewViewModel
import emperorfin.android.ocadocart.ui.screens.productsoverview.viewmodels.ProductsOverviewViewModelFactory
import java.util.*

/**
 * A simple [Fragment] subclass.
 */
class ProductsOverviewFragment : Fragment() {

    private lateinit var mViewModel: ProductsOverviewViewModel

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

        //TODO:
        // Doesn't request for either remote or local venues since they would be available after
        // configuration changes (screen rotation in this case).
//        if (mViewModel.isFirstRun)
//            mViewModel.loadProductsOverviews(...)
//        mViewModel.isFirstRun = false

        binding.lifecycleOwner = this
        binding.viewModel = mViewModel

        val productOverviewAdapterOnClickListener = ProductOverviewUiModelRecyclerviewAdapter.OnClickListener{
            showToastMessage(
                "Product Name: \"${it.title.toUpperCase(Locale.ROOT)}\""
            )
        }

        setupProductsOverviewAdapter(binding, productOverviewAdapterOnClickListener)

        return binding.root
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

    private fun showToastMessage(message: String) =
        Toast.makeText(requireActivity(), message, Toast.LENGTH_SHORT).show()
}