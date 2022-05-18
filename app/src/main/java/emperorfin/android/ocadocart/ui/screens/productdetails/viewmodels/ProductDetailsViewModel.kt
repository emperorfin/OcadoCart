package emperorfin.android.ocadocart.ui.screens.productdetails.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import emperorfin.android.ocadocart.data.datasources.remote.frameworks.retrofit.modelsources.ProductDetailsRemoteDataSourceRetrofit
import emperorfin.android.ocadocart.domain.models.ProductDetailsModel
import emperorfin.android.ocadocart.domain.models.ProductOverviewModel
import emperorfin.android.ocadocart.domain.uilayer.events.outputs.ResultData
import emperorfin.android.ocadocart.domain.uilayer.events.outputs.succeeded
import emperorfin.android.ocadocart.ui.events.inputs.productdetails.ProductDetailsParams
import emperorfin.android.ocadocart.ui.events.outputs.EventDataImpl
import emperorfin.android.ocadocart.ui.screens.productsoverview.enums.ProductDetailsRequestStatus
import emperorfin.android.ocadocart.ui.uimodels.ProductDetailsUiModel
import emperorfin.android.ocadocart.ui.uimodels.ProductOverviewUiModel
import emperorfin.android.ocadocart.ui.uimodels.mappers.ProductDetailsUiModelMapper
import emperorfin.android.ocadocart.ui.utils.ProductsDetailsSampleDataGeneratorUtil
import emperorfin.android.ocadocart.ui.utils.InternetConnectivityUtil.hasInternetConnection
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

        const val ERROR_CODE_NO_INTERNET_CONNECTION = "ERROR_CODE_NO_INTERNET_CONNECTION"
    }

    private val applicationContext = getApplication<Application>()

    // The internal MutableLiveData for the product details.
    private val _productDetails = MutableLiveData<ProductDetailsUiModel>()

    // The external LiveData for the product details.
    val productDetails: LiveData<ProductDetailsUiModel>
        get() = _productDetails

    private val _requestStatus = MutableLiveData<ProductDetailsRequestStatus>()
    val requestStatus: LiveData<ProductDetailsRequestStatus>
        get() = _requestStatus

    private val _noInternetConnectionError = MutableLiveData<String>()
    val noInternetConnectionError: LiveData<String>
        get() = _noInternetConnectionError

    // DO NOT REMOVE.
    init {
        // Option 1 of 4 (SAMPLE DATA)
//        generateProductDetailsSampleData(selectedProductOverview)
        // Option 2 of 4 (REAL DATA)
        getProductDetailsRealDataViaRemoteDataSource(selectedProductOverview)
    }

    fun emitNoInternetConnectionError(value: String?){
        _noInternetConnectionError.postValue(value)
    }

    fun getProductDetailsRealDataViaRemoteDataSource(
        selectedProductOverview: ProductOverviewUiModel
    ) = viewModelScope.launch {
        _requestStatus.value = ProductDetailsRequestStatus.LOADING

        if (!hasInternetConnection(applicationContext)){
            _noInternetConnectionError.postValue(ERROR_CODE_NO_INTERNET_CONNECTION)

            _requestStatus.value = ProductDetailsRequestStatus.ERROR

            return@launch
        }

        val remoteDataSourceRoom = ProductDetailsRemoteDataSourceRetrofit(applicationContext)

        val selectedProductId: Int = selectedProductOverview.id
        val selectedProductSize: String = selectedProductOverview.size
        val selectedProductTag: String = selectedProductOverview.tag

        val productDetailsParams = ProductDetailsParams(selectedProductId)

        val resultData: ResultData<ProductDetailsModel> =
            remoteDataSourceRoom.getProductDetails(productDetailsParams)

        if (resultData is ResultData.Error){
            _requestStatus.value = ProductDetailsRequestStatus.ERROR
        } else if (resultData.succeeded){
            val modelProductDetails = (resultData as ResultData.Success).data

            val productDetailsUiModelMapper = ProductDetailsUiModelMapper()

            val productDetails: ProductDetailsUiModel =
                productDetailsUiModelMapper.transform(modelProductDetails)

            val productDetailsNew = ProductDetailsUiModel(
                id = productDetails.id,
                title = productDetails.title,
                price = productDetails.price,
                imageUrl = productDetails.imageUrl,
                description = productDetails.description,
                allergyInformation = productDetails.allergyInformation,
                size = selectedProductSize,
                tag = selectedProductTag
            )

            _productDetails.value = productDetailsNew

            _requestStatus.value = ProductDetailsRequestStatus.DONE
        }
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