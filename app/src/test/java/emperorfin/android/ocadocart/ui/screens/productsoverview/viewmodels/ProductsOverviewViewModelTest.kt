package emperorfin.android.ocadocart.ui.screens.productsoverview.viewmodels

import android.app.Application
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import emperorfin.android.ocadocart.ui.extentions.getOrAwaitValue
import emperorfin.android.ocadocart.ui.screens.productdetails.viewmodels.ProductDetailsViewModel
import emperorfin.android.ocadocart.ui.uimodels.ProductOverviewUiModel
import org.hamcrest.Matchers.not
import org.hamcrest.Matchers.nullValue
import org.hamcrest.Matchers.`is`
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.Assert.*
import org.junit.Before


/**
 * @Author: Francis Nwokelo (emperorfin)
 * @Date: Wednesday 18th May, 2022.
 */


/**
 * Unit tests for [ProductsOverviewViewModel]
 *
 * NOTE
 * ====
 * Running tests on Android API level >= 29 using Robolectric requires Java 9. To run such
 * Robolectric tests without configuring Android Studio to use Java 9, the target and compile SDK
 * could be kept at 28.
 */
@RunWith(AndroidJUnit4::class)
class ProductsOverviewViewModelTest {

    // Subject under test
    private lateinit var mProductOverviewViewModel: ProductsOverviewViewModel

    private lateinit var mApplicationContext: Application

    // Executes each task synchronously using Architecture Components.
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setupViewModel(){
        mApplicationContext = ApplicationProvider.getApplicationContext()

        mProductOverviewViewModel = ProductsOverviewViewModel(mApplicationContext)
    }

    @Test
    fun emitNoInternetConnectionError_setsNoInternetConnectionError_noInternetConnectionErrorIsNotNull() {
        // When emitting no internet connection results error.
        val noInternetConnectionError = ProductsOverviewViewModel.ERROR_CODE_NO_INTERNET_CONNECTION
        mProductOverviewViewModel.emitNoInternetConnectionError(noInternetConnectionError)

        // Then the no internet connection results error LiveData is triggered
        val resultData = mProductOverviewViewModel.noInternetConnectionError.getOrAwaitValue()

        assertThat(resultData, `is`(noInternetConnectionError))
    }

    @Test
    fun openProductDetailsScreen_setsOpenProductDetailsScreenEvent() {

        val productOverview = ProductOverviewUiModel(
            id = 1,
            title = "",
            size = "",
            price = "",
            imageUrl = "",
            tag = ""
        )

        // When opening product details screen
        mProductOverviewViewModel.openProductDetails(productOverview)

        // Then the open product details screen event is triggered
        val eventData = mProductOverviewViewModel.openProductDetailsEvent.getOrAwaitValue()

        assertThat(eventData.getContentIfNotHandled(), not(nullValue()))
    }
}