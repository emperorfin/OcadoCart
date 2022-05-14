package emperorfin.android.ocadocart.ui.screens.productsoverview.viewmodels

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider


/**
 * @Author: Francis Nwokelo (emperorfin)
 * @Date: Thursday 12th May, 2022.
 */


class ProductsOverviewViewModelFactory(private val application: Application) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(ProductsOverviewViewModel::class.java)){
            return ProductsOverviewViewModel(application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}