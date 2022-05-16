package emperorfin.android.ocadocart.ui.screens.productdetails.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import emperorfin.android.ocadocart.ui.events.outputs.EventDataImpl
import emperorfin.android.ocadocart.ui.screens.productsoverview.enums.ProductDetailsRequestStatus
import emperorfin.android.ocadocart.ui.uimodels.ProductDetailsUiModel
import emperorfin.android.ocadocart.ui.uimodels.ProductOverviewUiModel
import emperorfin.android.ocadocart.ui.utils.ProductsDetailsSampleDataGeneratorUtil
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


/**
 * @Author: Francis Nwokelo (emperorfin)
 * @Date: Saturday 14th May, 2022.
 */


class ProductDetailsViewModel(
    application: Application,
    private val selectedProductOverview: ProductOverviewUiModel
) : AndroidViewModel(application) {

    companion object{
        const val IMAGE_WIDTH: Int = 1000
        const val IMAGE_HEIGHT: Int = 3900
    }

    // The internal MutableLiveData for the product details.
    private val _productDetails = MutableLiveData<ProductDetailsUiModel>()

    // The external LiveData for the product details.
    val productDetails: LiveData<ProductDetailsUiModel>
        get() = _productDetails

    private val _requestStatus = MutableLiveData<ProductDetailsRequestStatus>()
    val requestStatus: LiveData<ProductDetailsRequestStatus>
        get() = _requestStatus

    // DO NOT REMOVE.
    init {
        // Option 1 of 4 (SAMPLE DATA)
        generateProductDetailsSampleData(selectedProductOverview)
    }

    private fun generateProductDetailsSampleData(selectedProductOverview: ProductOverviewUiModel) = viewModelScope.launch {
        _requestStatus.value = ProductDetailsRequestStatus.LOADING

        delay(2000)

        val productsDetails: ArrayList<ProductDetailsUiModel> = ProductsDetailsSampleDataGeneratorUtil.getTransformedEntityProductsDetails()

//        productsDetails = listOf()

        if (productsDetails.isEmpty()) {
            _requestStatus.value = ProductDetailsRequestStatus.NO_DATA
        } else {
            val id: Int = selectedProductOverview.id
            val size: String = selectedProductOverview.size
            val tag: String = selectedProductOverview.tag

            lateinit var productDetails: ProductDetailsUiModel

            productsDetails.forEach {
                if (it.id == id){
                    productDetails = it

                    return@forEach
                }
            }

            // TODO: Check if (::productDetails.isInitialized) but make productDetails a property 1st.

            val productDetailsNew = ProductDetailsUiModel(
                id = productDetails.id,
                title = productDetails.title,
                price = productDetails.price,
                imageUrl = productDetails.imageUrl,
                description = productDetails.description,
                allergyInformation = productDetails.allergyInformation,
                size = size,
                tag = tag
            )

            _productDetails.value = productDetailsNew

            _requestStatus.value = ProductDetailsRequestStatus.DONE
        }
    }

}