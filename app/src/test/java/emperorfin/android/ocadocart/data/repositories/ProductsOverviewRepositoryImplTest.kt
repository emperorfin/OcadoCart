package emperorfin.android.ocadocart.data.repositories

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.hamcrest.core.IsEqual
import org.junit.Before
import org.junit.Test
import org.junit.Assert.*
import emperorfin.android.ocadocart.data.datasources.local.frameworks.room.entities.ProductOverviewEntity
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
import emperorfin.android.ocadocart.data.datasources.local.frameworks.room.entitysources.FakeProductOverviewLocalDataSourceRoom
import emperorfin.android.ocadocart.data.datasources.remote.frameworks.retrofit.models.productoverview.ProductOverviewDataTransferObject
import emperorfin.android.ocadocart.data.datasources.remote.frameworks.retrofit.modelsources.FakeProductOverviewRemoteDataSourceRetrofit
import emperorfin.android.ocadocart.domain.datalayer.datasources.FakeProductOverviewDataSource
import emperorfin.android.ocadocart.domain.models.mappers.ProductOverviewModelMapper
import emperorfin.android.ocadocart.domain.uilayer.events.outputs.ResultData
import emperorfin.android.ocadocart.domain.uilayer.events.outputs.ResultData.Success
import emperorfin.android.ocadocart.domain.uilayer.events.outputs.ResultData.Error
import emperorfin.android.ocadocart.ui.events.inputs.productoverview.None


/**
 * @Author: Francis Nwokelo (emperorfin)
 * @Date: Wednesday 18th May, 2022.
 */


/**
 * Unit tests for the implementation of the in-memory repository ([ProductsOverviewRepositoryImpl])
 * with cache.
 *
 * NOTE
 * ====
 * Running tests on Android API level >= 29 using Robolectric requires Java 9. To run such
 * Robolectric tests without configuring Android Studio to use Java 9, the target and compile SDK
 * could be kept at 28.
 */
@ExperimentalCoroutinesApi
class ProductsOverviewRepositoryImplTest {

    private val dataTransferObjectProductOverview1 = ProductOverviewDataTransferObject.newInstance(
        id = ID_1,
        title = TITLE_1,
        size = SIZE_1,
        price = PRICE_1,
        imageUrl = IMAGE_URL_1,
        tag = TAG_1
    )

    private val dataTransferObjectProductOverview2 = ProductOverviewDataTransferObject.newInstance(
        id = ID_2,
        title = TITLE_2,
        size = SIZE_2,
        price = PRICE_2,
        imageUrl = IMAGE_URL_2,
        tag = TAG_2
    )

    private val dataTransferObjectProductOverview3 = ProductOverviewDataTransferObject.newInstance(
        id = ID_3,
        title = TITLE_3,
        size = SIZE_3,
        price = PRICE_3,
        imageUrl = IMAGE_URL_3,
        tag = TAG_3
    )

    private val entityProductOverview1 = ProductOverviewEntity.newInstance(
        id = ID_4,
        title = TITLE_4,
        size = SIZE_4,
        price = PRICE_4,
        imageUrl = IMAGE_URL_4,
        tag = TAG_4
    )

    private val remoteProductsOverviews = listOf(
        dataTransferObjectProductOverview1,
        dataTransferObjectProductOverview2,
        dataTransferObjectProductOverview3
    ).sortedBy { it.title }

    private val localProductsOverview = listOf(entityProductOverview1).sortedBy { it.title }

    private lateinit var productOverviewLocalDataSource: FakeProductOverviewDataSource
    private lateinit var productOverviewRemoteDataSource: FakeProductOverviewDataSource

    // Class under test
    private lateinit var productsOverviewRepository: ProductsOverviewRepositoryImpl

    @Before
    fun createRepository() {
        productOverviewLocalDataSource =
            FakeProductOverviewLocalDataSourceRoom(localProductsOverview.toMutableList())
        productOverviewRemoteDataSource =
            FakeProductOverviewRemoteDataSourceRetrofit(remoteProductsOverviews.toMutableList())

        productsOverviewRepository = ProductsOverviewRepositoryImpl(
                productOverviewLocalDataSource,
                productOverviewRemoteDataSource,
                Dispatchers.Unconfined
            )
    }

    @Test
    fun getProductsOverviews_requestsAllProductsOverviewsFromRemoteDataSource_productsOverviewsRetrieved() = runBlockingTest {
        // When productsOverviews are requested from the productsOverviews overview repository
        val productOverviewModelMapper = ProductOverviewModelMapper()
        val mappedModelProductsOverviews = remoteProductsOverviews.map {
            productOverviewModelMapper.transform(it)
        }

        val paramNothing = None()

        val modelProductsOverviewsResultData =
            productsOverviewRepository
                .getProductsOverviews(paramNothing, paramNothing,true) as Success

        // Then productsOverviews are loaded from the remote data source
        assertThat(modelProductsOverviewsResultData.data, IsEqual(mappedModelProductsOverviews))
    }

    @Test
    fun getProductsOverviews_requestsAllProductsOverviewsFromRemoteDataSource_errorEncounteredIfListEmpty() = runBlockingTest {
        // Given
        productOverviewRemoteDataSource = FakeProductOverviewRemoteDataSourceRetrofit()
        productOverviewLocalDataSource = FakeProductOverviewLocalDataSourceRoom()

        productsOverviewRepository = ProductsOverviewRepositoryImpl(
            productOverviewLocalDataSource,
            productOverviewRemoteDataSource,
            Dispatchers.Unconfined
        )

        // When productsOverviews are requested from the productsOverviews overview repository
        val paramNothing = None()

        val modelProductsOverviewsResultData = productsOverviewRepository.getProductsOverviews(
            paramNothing,
            paramNothing,
            true
        )

        // Then productsOverviews are loaded from the remote data source
        assertTrue(modelProductsOverviewsResultData is Error)
    }

    //.....

    @Test
    fun getProductsOverviews_requestsAllProductsOverviewsFromRemoteDataSource_errorEncounteredIfListIsNull() = runBlockingTest {
        // Given
        productOverviewRemoteDataSource = FakeProductOverviewRemoteDataSourceRetrofit(null)
        productOverviewLocalDataSource = FakeProductOverviewLocalDataSourceRoom()

        productsOverviewRepository = ProductsOverviewRepositoryImpl(
            productOverviewLocalDataSource,
            productOverviewRemoteDataSource,
            Dispatchers.Unconfined
        )

        // When productsOverviews are requested from the productsOverviews overview repository
        val paramNothing = None()

        val modelProductsOverviewsResultData = productsOverviewRepository.getProductsOverviews(
            paramNothing,
            paramNothing,
            true
        )

        // Then productsOverviews are loaded from the remote data source
        assertTrue(modelProductsOverviewsResultData is Error)
    }

    @Test
    fun getProductsOverviews_requestsAllProductsOverviewsFromRemoteOrLocalDataSource_errorEncounteredIfBothListsAreNull() = runBlockingTest {
        // Given
        productOverviewRemoteDataSource = FakeProductOverviewRemoteDataSourceRetrofit(null)
        productOverviewLocalDataSource = FakeProductOverviewLocalDataSourceRoom(null)

        productsOverviewRepository = ProductsOverviewRepositoryImpl(
            productOverviewLocalDataSource,
            productOverviewRemoteDataSource,
            Dispatchers.Unconfined
        )

        // When productsOverviews are requested from the productsOverviews overview repository
        val paramNothing = None()

        val modelProductsOverviewsResultData = productsOverviewRepository.getProductsOverviews(
            paramNothing,
            paramNothing,
            false
        )

        // Then productsOverviews are loaded from the remote data source
        assertTrue(modelProductsOverviewsResultData is Error)
    }

    @Test
    fun getProductsOverviews_requestsAllProductsOverviewsFromLocalDataSource_productsOverviewsRetrieved() = runBlockingTest {
        // When productsOverviews are requested from the productsOverviews overview repository
        val productOverviewModelMapper = ProductOverviewModelMapper()
        val mappedModelProductsOverviews = localProductsOverview.map { productOverviewModelMapper.transform(it) }

        val paramNothing = None()

        val modelProductsOverviewsResultData = productsOverviewRepository.productOverviewLocalDataSource.getProductsOverviews(
            paramNothing
        ) as Success

        // Then productsOverviews are loaded from the remote data source
        assertThat(modelProductsOverviewsResultData.data, IsEqual(mappedModelProductsOverviews))
    }

    @Test
    fun getProductsOverviews_requestsAllProductsOverviewsFromLocalDataSource_errorEncounteredIfListEmpty() = runBlockingTest {
        // Given
        productOverviewRemoteDataSource = FakeProductOverviewRemoteDataSourceRetrofit()
        productOverviewLocalDataSource = FakeProductOverviewLocalDataSourceRoom()

        productsOverviewRepository = ProductsOverviewRepositoryImpl(
            productOverviewLocalDataSource,
            productOverviewRemoteDataSource,
            Dispatchers.Unconfined
        )

        // When productsOverviews are requested from the productsOverviews overview repository
        val paramNothing = None()

        val modelProductsOverviewsResultData = productsOverviewRepository.productOverviewLocalDataSource.getProductsOverviews(
            paramNothing
        )

        // Then productsOverviews are loaded from the remote data source
        assertTrue(modelProductsOverviewsResultData is Error)
    }

    @Test
    fun getProductsOverviews_requestsAllProductsOverviewsFromLocalDataSource_errorEncounteredIfListIsNull() = runBlockingTest {
        // Given
        productOverviewRemoteDataSource = FakeProductOverviewRemoteDataSourceRetrofit()
        productOverviewLocalDataSource = FakeProductOverviewLocalDataSourceRoom(null)

        productsOverviewRepository = ProductsOverviewRepositoryImpl(
            productOverviewLocalDataSource,
            productOverviewRemoteDataSource,
            Dispatchers.Unconfined
        )

        // When productsOverviews are requested from the productsOverviews overview repository
        val paramNothing = None()

        val modelProductsOverviewsResultData = productsOverviewRepository.productOverviewLocalDataSource.getProductsOverviews(
            paramNothing
        )

        // Then productsOverviews are loaded from the remote data source
        assertTrue(modelProductsOverviewsResultData is Error)
    }

}