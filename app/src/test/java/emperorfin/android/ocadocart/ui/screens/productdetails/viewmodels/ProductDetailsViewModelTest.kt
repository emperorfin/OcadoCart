package emperorfin.android.ocadocart.ui.screens.productdetails.viewmodels


import org.hamcrest.Matchers.not
import org.hamcrest.Matchers.nullValue
import org.hamcrest.Matchers.`is`
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import android.app.Application
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import emperorfin.android.ocadocart.ui.extentions.getOrAwaitValue
import emperorfin.android.ocadocart.ui.uimodels.ProductOverviewUiModel
import org.junit.Assert.*


/**
 * @Author: Francis Nwokelo (emperorfin)
 * @Date: Wednesday 18th May, 2022.
 */


/**
 * Unit tests for [ProductDetailsViewModel]
 *
 * NOTE
 * ====
 * Running tests on Android API level >= 29 using Robolectric requires Java 9. To run such
 * Robolectric tests without configuring Android Studio to use Java 9, the target and compile SDK
 * could be kept at 28.
 */
@RunWith(AndroidJUnit4::class)
class ProductDetailsViewModelTest {

    // Executes each task synchronously using Architecture Components.
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Test
    fun emitNoInternetConnectionError_setsNoInternetConnectionError_noInternetConnectionErrorIsNotNull() {
        val applicationContext: Application = ApplicationProvider.getApplicationContext()

        val productOverview = ProductOverviewUiModel(
            id = 1,
            title = "",
            size = "",
            price = "",
            imageUrl = "",
            tag = ""
        )

        val productDetailsViewModel = ProductDetailsViewModel(applicationContext, productOverview)

        // When emitting no internet connection results error.
        val noInternetConnectionError = ProductDetailsViewModel.ERROR_CODE_NO_INTERNET_CONNECTION
        productDetailsViewModel.emitNoInternetConnectionError(noInternetConnectionError)

        // Then the no internet connection results error LiveData is triggered
        val resultData = productDetailsViewModel.noInternetConnectionError.getOrAwaitValue()

        assertThat(resultData, `is`(noInternetConnectionError))
    }

}