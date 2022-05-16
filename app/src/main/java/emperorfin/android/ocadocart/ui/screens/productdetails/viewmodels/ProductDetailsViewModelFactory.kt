package emperorfin.android.ocadocart.ui.screens.productdetails.viewmodels

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import emperorfin.android.ocadocart.ui.uimodels.ProductOverviewUiModel


/**
 * @Author: Francis Nwokelo (emperorfin)
 * @Date: Saturday 14th May, 2022.
 */


class ProductDetailsViewModelFactory(
    private val application: Application,
    private val selectedProductOverview: ProductOverviewUiModel
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(ProductDetailsViewModel::class.java)){
            return ProductDetailsViewModel(application, selectedProductOverview) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}