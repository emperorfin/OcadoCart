package emperorfin.android.ocadocart.ui.screens.productsoverview.viewmodels

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import emperorfin.android.ocadocart.R
import emperorfin.android.ocadocart.data.datasources.local.frameworks.room.AppRoomDatabase
import emperorfin.android.ocadocart.data.datasources.local.frameworks.room.entitysources.ProductOverviewLocalDataSourceRoom
import emperorfin.android.ocadocart.data.datasources.remote.frameworks.retrofit.modelsources.ProductOverviewRemoteDataSourceRetrofit
import emperorfin.android.ocadocart.data.repositories.ProductsOverviewRepositoryImpl
import emperorfin.android.ocadocart.domain.exceptions.ProductOverviewFailure.LocalProductOverviewError
import emperorfin.android.ocadocart.domain.exceptions.ProductOverviewFailure.ListNotAvailableLocalProductOverviewError
import emperorfin.android.ocadocart.domain.exceptions.ProductOverviewFailure.RemoteProductOverviewError
import emperorfin.android.ocadocart.domain.exceptions.ProductOverviewFailure.ListNotAvailableRemoteProductOverviewError
import emperorfin.android.ocadocart.domain.models.ProductOverviewModel
import emperorfin.android.ocadocart.domain.models.mappers.ProductOverviewModelMapper
import emperorfin.android.ocadocart.domain.uilayer.events.inputs.productoverview.Params
import emperorfin.android.ocadocart.ui.events.outputs.EventDataImpl
import emperorfin.android.ocadocart.ui.screens.productsoverview.enums.ProductsOverviewRequestStatus
import emperorfin.android.ocadocart.ui.uimodels.ProductOverviewUiModel
import emperorfin.android.ocadocart.ui.uimodels.mappers.ProductOverviewUiModelMapper
import emperorfin.android.ocadocart.ui.utils.ProductsOverviewsSampleDataGeneratorUtil
import emperorfin.android.ocadocart.domain.uilayer.events.outputs.ResultData
import emperorfin.android.ocadocart.domain.uilayer.events.outputs.ResultData.Success
import emperorfin.android.ocadocart.domain.uilayer.events.outputs.ResultData.Error
import emperorfin.android.ocadocart.domain.uilayer.events.outputs.succeeded
import emperorfin.android.ocadocart.ui.events.inputs.productoverview.None
import emperorfin.android.ocadocart.ui.utils.InternetConnectivityUtil.hasInternetConnection
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.properties.Delegates


/**
 * @Author: Francis Nwokelo (emperorfin)
 * @Date: Thursday 12th May, 2022.
 */


class ProductsOverviewViewModel(
    application: Application
) : AndroidViewModel(application) {

    companion object {

        const val ERROR_CODE_NO_INTERNET_CONNECTION = "ERROR_CODE_NO_INTERNET_CONNECTION"

    }

    private val applicationContext = getApplication<Application>()

    var isFirstRun = true

    private val _requestStatus = MutableLiveData<ProductsOverviewRequestStatus>()
    val requestStatus: LiveData<ProductsOverviewRequestStatus>
        get() = _requestStatus

    private val _productsOverviews = MutableLiveData<List<ProductOverviewUiModel>>()
    val productsOverviews: LiveData<List<ProductOverviewUiModel>>
        get() = _productsOverviews

    private val _openProductDetailsEvent = MutableLiveData<EventDataImpl<ProductOverviewUiModel>>()

    val openProductDetailsEvent: LiveData<EventDataImpl<ProductOverviewUiModel>>
        get() = _openProductDetailsEvent

    private val _noInternetConnectionError = MutableLiveData<String>()
    val noInternetConnectionError: LiveData<String>
        get() = _noInternetConnectionError

    private val productsOverviewsRepository = getProductsOverviewsRepository(applicationContext)

    // DO NOT REMOVE.
    init{
        // Option 1 of 5 (SAMPLE DATA)
//        generateProductsOverviewsSampleData()
        // Option 2 of 5 (SAMPLE DATA)
//        getDatabaseProductsOverviewsSampleDataWithoutLocalDataSource()
        // Option 3 of 5 (SAMPLE DATA)
//        getDatabaseProductsOverviewsSampleDataViaLocalDataSource()
        // Option 4 of 5 (REAL DATA)
//        getProductsOverviewsRealDataViaRemoteDataSource()
        // Option 5 of 5 (REAL DATA WITH OFFLINE SUPPORT)
        loadProductsOverviews()
    }

    fun emitNoInternetConnectionError(value: String?){
        _noInternetConnectionError.postValue(value)
    }

    fun openProductDetails(productOverview: ProductOverviewUiModel){
        _openProductDetailsEvent.value = EventDataImpl(productOverview)
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

    private fun getDatabaseProductsOverviewsSampleDataWithoutLocalDataSource() = viewModelScope.launch{
        _requestStatus.value = ProductsOverviewRequestStatus.LOADING

        val entityProductsOverviews = AppRoomDatabase.getInstance(applicationContext).mProductOverviewDao.getProductsOverviews()

        val productOverviewModelMapper = ProductOverviewModelMapper()
        val productOverviewUiModelMapper = ProductOverviewUiModelMapper()

        _productsOverviews.value = entityProductsOverviews.map {
            productOverviewModelMapper.transform(it)
        }.map {
            productOverviewUiModelMapper.transform(it)
        }

        if(_productsOverviews.value?.isEmpty() == true)
            _requestStatus.value = ProductsOverviewRequestStatus.NO_DATA
        else
            _requestStatus.value = ProductsOverviewRequestStatus.DONE
    }

    private fun getDatabaseProductsOverviewsSampleDataViaLocalDataSource() = viewModelScope.launch{
        _requestStatus.value = ProductsOverviewRequestStatus.LOADING

        val localDataSourceRoom: ProductOverviewLocalDataSourceRoom = ProductOverviewLocalDataSourceRoom(
            applicationContext,
            AppRoomDatabase.getInstance(applicationContext).mProductOverviewDao
        )

        val params = None()
        val resultData: ResultData<List<ProductOverviewModel>> =
            localDataSourceRoom.getProductsOverviews(params)

        if (resultData is Error && (resultData.failure is LocalProductOverviewError ||
                    resultData.failure is ListNotAvailableLocalProductOverviewError)){
            _requestStatus.value = ProductsOverviewRequestStatus.NO_DATA
        }else if (resultData.succeeded){
            val modelProductsOverviews = (resultData as Success).data

            val productOverviewUiModelMapper = ProductOverviewUiModelMapper()

            _productsOverviews.value = modelProductsOverviews.map {
                productOverviewUiModelMapper.transform(it)
            }

            _requestStatus.value = ProductsOverviewRequestStatus.DONE
        }
    }

    private fun getProductsOverviewsRealDataViaRemoteDataSource() = viewModelScope.launch{
        _requestStatus.value = ProductsOverviewRequestStatus.LOADING

        val remoteDataSourceRoom = ProductOverviewRemoteDataSourceRetrofit(applicationContext)

        val params = None()
        val resultData: ResultData<List<ProductOverviewModel>> =
            remoteDataSourceRoom.getProductsOverviews(params)

        if (resultData is Error && (resultData.failure is RemoteProductOverviewError ||
                    resultData.failure is ListNotAvailableRemoteProductOverviewError)){
            _requestStatus.value = ProductsOverviewRequestStatus.NO_DATA
        }else if (resultData.succeeded){
            val modelProductsOverviews = (resultData as Success).data

            val productOverviewUiModelMapper = ProductOverviewUiModelMapper()

            _productsOverviews.value = modelProductsOverviews.map {
                productOverviewUiModelMapper.transform(it)
            }

            _requestStatus.value = ProductsOverviewRequestStatus.DONE
        }
    }

    private fun getDatabaseProductsOverviewsRealDataViaRepository(
        paramsLocal: Params = None(), paramsRemote: Params = None(), forceUpdate: Boolean
    ) = viewModelScope.launch{
        _requestStatus.value = ProductsOverviewRequestStatus.LOADING

        val productsOverviewsDataResultEvent =
            productsOverviewsRepository.getProductsOverviews(paramsLocal, paramsRemote, forceUpdate)

        if (productsOverviewsDataResultEvent is Success){
            val modelProductsOverviews: List<ProductOverviewModel> = productsOverviewsDataResultEvent.data

            val productOverviewUiModelMapper = ProductOverviewUiModelMapper()

            _productsOverviews.value = modelProductsOverviews.map {
                productOverviewUiModelMapper.transform(it)
            }

            _requestStatus.value = ProductsOverviewRequestStatus.DONE
        }else{
            _requestStatus.value = ProductsOverviewRequestStatus.NO_DATA

//            _productsOverviews.value = emptyList()
        }
    }

    private fun getSavedProductsOverviews(){
        getDatabaseProductsOverviewsSampleDataViaLocalDataSource()
    }

    fun loadProductsOverviews(paramsLocal: Params = None(), paramsRemote: Params = None()){
        viewModelScope.launch {
            var productsOverviewsCount by Delegates.notNull<Int>()

            val productsOverviewsCountDataResultEvent = productsOverviewsRepository.countProductsOverviews()

            productsOverviewsCount = if (productsOverviewsCountDataResultEvent.succeeded)
                (productsOverviewsCountDataResultEvent as Success).data
            else
                -1

            if (productsOverviewsCount > 0){
                if (hasInternetConnection(applicationContext)){
                    getDatabaseProductsOverviewsRealDataViaRepository(
                        paramsLocal = paramsLocal,
                        paramsRemote = paramsRemote,
                        false
                    )
                } else {
                    Toast.makeText(
                        applicationContext,
                        applicationContext.getString(R.string.message_no_internet_loading_cached_data),
                        Toast.LENGTH_LONG
                    ).show()

                    getSavedProductsOverviews()
                }
            } else {
                if (hasInternetConnection(applicationContext)){
                    getDatabaseProductsOverviewsRealDataViaRepository(
                        paramsLocal = paramsLocal,
                        paramsRemote = paramsRemote,
                        true
                    )
                } else {
                    _noInternetConnectionError.postValue(ERROR_CODE_NO_INTERNET_CONNECTION)
                }
            }
        }
    }

    private fun getProductsOverviewsRepository(application: Application): ProductsOverviewRepositoryImpl{
        val productOverviewDao = AppRoomDatabase.getInstance(application).mProductOverviewDao

        val productOverviewLocalDataSource =
            ProductOverviewLocalDataSourceRoom(context = application, productOverviewDao = productOverviewDao)

        val productOverviewRemoteDataSource =
            ProductOverviewRemoteDataSourceRetrofit(context = application)

        return ProductsOverviewRepositoryImpl(
            productOverviewLocalDataSource = productOverviewLocalDataSource,
            productOverviewRemoteDataSource = productOverviewRemoteDataSource
        )
    }

}