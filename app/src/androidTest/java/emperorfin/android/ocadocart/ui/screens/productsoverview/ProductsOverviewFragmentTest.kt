package emperorfin.android.ocadocart.ui.screens.productsoverview

import android.os.Bundle
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import emperorfin.android.ocadocart.R
import emperorfin.android.ocadocart.data.repositories.FakeAndroidTestProductsOverviewRepositoryImpl
import emperorfin.android.ocadocart.domain.datalayer.repositories.FakeAndroidTestProductsOverviewRepository
import emperorfin.android.ocadocart.domain.models.mappers.ProductOverviewModelMapper
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import emperorfin.android.ocadocart.data.constants.ProductsOverviewsSampleDataConstants.ID_1
import emperorfin.android.ocadocart.data.constants.ProductsOverviewsSampleDataConstants.ID_2
import emperorfin.android.ocadocart.data.constants.ProductsOverviewsSampleDataConstants.ID_3
import emperorfin.android.ocadocart.data.constants.ProductsOverviewsSampleDataConstants.ID_4

import emperorfin.android.ocadocart.data.constants.ProductsOverviewsSampleDataConstants.TITLE_1
import emperorfin.android.ocadocart.data.constants.ProductsOverviewsSampleDataConstants.TITLE_2
import emperorfin.android.ocadocart.data.constants.ProductsOverviewsSampleDataConstants.TITLE_3
import emperorfin.android.ocadocart.data.constants.ProductsOverviewsSampleDataConstants.TITLE_4

import emperorfin.android.ocadocart.data.constants.ProductsOverviewsSampleDataConstants.SIZE_1
import emperorfin.android.ocadocart.data.constants.ProductsOverviewsSampleDataConstants.SIZE_2
import emperorfin.android.ocadocart.data.constants.ProductsOverviewsSampleDataConstants.SIZE_3
import emperorfin.android.ocadocart.data.constants.ProductsOverviewsSampleDataConstants.SIZE_4

import emperorfin.android.ocadocart.data.constants.ProductsOverviewsSampleDataConstants.PRICE_1
import emperorfin.android.ocadocart.data.constants.ProductsOverviewsSampleDataConstants.PRICE_2
import emperorfin.android.ocadocart.data.constants.ProductsOverviewsSampleDataConstants.PRICE_3
import emperorfin.android.ocadocart.data.constants.ProductsOverviewsSampleDataConstants.PRICE_4

import emperorfin.android.ocadocart.data.constants.ProductsOverviewsSampleDataConstants.IMAGE_URL_1
import emperorfin.android.ocadocart.data.constants.ProductsOverviewsSampleDataConstants.IMAGE_URL_2
import emperorfin.android.ocadocart.data.constants.ProductsOverviewsSampleDataConstants.IMAGE_URL_3
import emperorfin.android.ocadocart.data.constants.ProductsOverviewsSampleDataConstants.IMAGE_URL_4

import emperorfin.android.ocadocart.data.constants.ProductsOverviewsSampleDataConstants.TAG_1
import emperorfin.android.ocadocart.data.constants.ProductsOverviewsSampleDataConstants.TAG_2
import emperorfin.android.ocadocart.data.constants.ProductsOverviewsSampleDataConstants.TAG_3
import emperorfin.android.ocadocart.data.constants.ProductsOverviewsSampleDataConstants.TAG_4
import emperorfin.android.ocadocart.data.datasources.local.frameworks.room.entities.ProductOverviewEntity
import emperorfin.android.ocadocart.data.datasources.remote.frameworks.retrofit.models.productoverview.ProductOverviewDataTransferObject
import emperorfin.android.ocadocart.ui.uimodels.mappers.ProductOverviewUiModelMapper
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify


/**
 * @Author: Francis Nwokelo (emperorfin)
 * @Date: Thursday 19th May, 2022.
 */


/**
 * Integration test for the Products Overview List screen.
 */
// TODO - Use FragmentScenario, see: https://github.com/android/android-test/issues/291
@RunWith(AndroidJUnit4::class)
@MediumTest
@ExperimentalCoroutinesApi
class ProductsOverviewFragmentTest {

    private var productsOverviewRepository: FakeAndroidTestProductsOverviewRepository? = null

    private lateinit var productOverviewModelMapper: ProductOverviewModelMapper
    private lateinit var productOverviewUiModelMapper: ProductOverviewUiModelMapper

    @Before
    fun initRepository() {
        productsOverviewRepository = FakeAndroidTestProductsOverviewRepositoryImpl()
    }

    @After
    fun cleanupRepositoryDataSources() = runBlockingTest {
        productsOverviewRepository?.deleteAllProductsOverviews()
        productsOverviewRepository = null
    }

    @Test
    fun clickProductOverview_navigateToProductDetailsFragment() = runBlockingTest {
        productsOverviewRepository?.deleteAllProductsOverviews()

        val entityProductOverview1 = ProductOverviewEntity.newInstance(
            id = ID_1,
            title = TITLE_1,
            size = SIZE_1,
            price = PRICE_1,
            imageUrl = IMAGE_URL_1,
            tag = TAG_1
        )

        val entityProductOverview2 = ProductOverviewEntity.newInstance(
            id = ID_2,
            title = TITLE_2,
            size = SIZE_2,
            price = PRICE_2,
            imageUrl = IMAGE_URL_2,
            tag = TAG_2
        )

        productOverviewModelMapper = ProductOverviewModelMapper()
        productOverviewUiModelMapper = ProductOverviewUiModelMapper()

        val modelProductOverview1 = productOverviewModelMapper.transform(entityProductOverview1)
        val modelProductOverview2 = productOverviewModelMapper.transform(entityProductOverview2)

        val uiModelProductOverview1 = productOverviewUiModelMapper.transform(modelProductOverview1)

        productsOverviewRepository?.saveProductOverview(modelProductOverview1)
        productsOverviewRepository?.saveProductOverview(modelProductOverview2)

        // GIVEN - On the home screen
        val scenario = launchFragmentInContainer<ProductsOverviewFragment>(Bundle(), R.style.Theme_OcadoCart)
        val navController = mock(NavController::class.java)
        scenario.onFragment {
            Navigation.setViewNavController(it.view!!, navController)
        }

        // WHEN - Click on the first list item
        onView(withId(R.id.rv_products_overviews))
            .perform(
                RecyclerViewActions.actionOnItem<RecyclerView.ViewHolder>(
                    hasDescendant(withText("$TITLE_1")), click()))


        // THEN - Verify that we navigate to the first detail screen
        verify(navController).navigate(
            ProductsOverviewFragmentDirections
                .actionProductsOverviewFragmentToProductDetailsFragment(uiModelProductOverview1)
        )
    }

    @Test
    fun clickProductOverview_navigateToProductDetailsFragment_UsingDatabase() = runBlockingTest {
        productsOverviewRepository?.deleteAllProductsOverviews()

        val entityProductOverview1 = ProductOverviewEntity.newInstance(
            id = ID_1,
            title = TITLE_1,
            size = SIZE_1,
            price = PRICE_1,
            imageUrl = IMAGE_URL_1,
            tag = TAG_1
        )

        val entityProductOverview2 = ProductOverviewEntity.newInstance(
            id = ID_2,
            title = TITLE_2,
            size = SIZE_2,
            price = PRICE_2,
            imageUrl = IMAGE_URL_2,
            tag = TAG_2
        )

        productOverviewModelMapper = ProductOverviewModelMapper()
        productOverviewUiModelMapper = ProductOverviewUiModelMapper()

        val modelProductOverview1 = productOverviewModelMapper.transform(entityProductOverview1)
        val modelProductOverview2 = productOverviewModelMapper.transform(entityProductOverview2)

        val uiModelProductOverview1 = productOverviewUiModelMapper.transform(modelProductOverview1)

        productsOverviewRepository?.saveProductOverview(modelProductOverview1)
        productsOverviewRepository?.saveProductOverview(modelProductOverview2)

        // GIVEN - On the home screen
        val scenario = launchFragmentInContainer<ProductsOverviewFragment>(Bundle(), R.style.Theme_OcadoCart)
        val navController = mock(NavController::class.java)
        scenario.onFragment {
            Navigation.setViewNavController(it.view!!, navController)
        }

        // WHEN - Click on the first list item
        onView(withId(R.id.rv_products_overviews))
            .perform(
                RecyclerViewActions.actionOnItem<RecyclerView.ViewHolder>(
                    hasDescendant(withText("$TITLE_1")), click()))


        // THEN - Verify that we navigate to the first detail screen
        verify(navController).navigate(
            ProductsOverviewFragmentDirections
                .actionProductsOverviewFragmentToProductDetailsFragment(uiModelProductOverview1)
        )
    }

}