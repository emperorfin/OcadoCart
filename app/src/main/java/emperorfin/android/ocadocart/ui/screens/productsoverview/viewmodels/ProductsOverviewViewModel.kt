package emperorfin.android.ocadocart.ui.screens.productsoverview.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import emperorfin.android.ocadocart.ui.screens.productsoverview.enums.ProductsOverviewRequestStatus
import emperorfin.android.ocadocart.ui.uimodels.ProductOverviewUiModel
import emperorfin.android.ocadocart.ui.utils.ProductsOverviewsSampleDataGeneratorUtil
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


/**
 * @Author: Francis Nwokelo (emperorfin)
 * @Date: Thursday 12th May, 2022.
 */


class ProductsOverviewViewModel(
    application: Application
) : AndroidViewModel(application) {

    private val applicationContext = getApplication<Application>()

    var isFirstRun = true

    private val _requestStatus = MutableLiveData<ProductsOverviewRequestStatus>()
    val requestStatus: LiveData<ProductsOverviewRequestStatus>
        get() = _requestStatus

    private val _productsOverviews = MutableLiveData<List<ProductOverviewUiModel>>()
    val productsOverviews: LiveData<List<ProductOverviewUiModel>>
        get() = _productsOverviews

    // DO NOT REMOVE.
    init{
        // Option 1 of 4 (SAMPLE DATA)
        generateProductsOverviewsSampleData()
    }

    private fun generateProductsOverviewsSampleData() = viewModelScope.launch {
        _requestStatus.value = ProductsOverviewRequestStatus.LOADING

        delay(2000)

        _productsOverviews.value =
            ProductsOverviewsSampleDataGeneratorUtil.getTransformedEntityProductsOverviews()

//        _productsOverviews.value = listOf()

        if (_productsOverviews.value?.isEmpty() == true) {
            _requestStatus.value = ProductsOverviewRequestStatus.NO_DATA
        } else {
            _requestStatus.value = ProductsOverviewRequestStatus.DONE
        }
    }
}