package emperorfin.android.ocadocart.data.repositories

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
import emperorfin.android.ocadocart.data.datasources.remote.frameworks.retrofit.models.productoverview.ProductOverviewDataTransferObject
import emperorfin.android.ocadocart.data.local.frameworks.room.entitysources.FakeAndroidTestProductOverviewLocalDataSourceRoom
import emperorfin.android.ocadocart.data.remote.frameworks.retrofit.modelsources.FakeAndroidTestProductOverviewRemoteDataSourceRetrofit
import emperorfin.android.ocadocart.domain.datalayer.datasources.FakeAndroidTestProductOverviewDataSource
import emperorfin.android.ocadocart.domain.datalayer.repositories.FakeAndroidTestProductsOverviewRepository
import emperorfin.android.ocadocart.domain.models.ProductOverviewModel
import emperorfin.android.ocadocart.domain.uilayer.events.inputs.productoverview.Params
import emperorfin.android.ocadocart.domain.uilayer.events.outputs.ResultData
import kotlinx.coroutines.Dispatchers


/**
 * @Author: Francis Nwokelo (emperorfin)
 * @Date: Thursday 19th May, 2022.
 */


class FakeAndroidTestProductsOverviewRepositoryImpl : FakeAndroidTestProductsOverviewRepository {

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

    private val productOverviewLocalDataSource =
        FakeAndroidTestProductOverviewLocalDataSourceRoom(localProductsOverview.toMutableList())
    private val productOverviewRemoteDataSource =
        FakeAndroidTestProductOverviewRemoteDataSourceRetrofit(remoteProductsOverviews.toMutableList())

    private val productsOverviewRepository = ProductsOverviewRepositoryImpl(
        productOverviewLocalDataSource, productOverviewRemoteDataSource, Dispatchers.Unconfined
    )

    override suspend fun countProductsOverviews(): ResultData<Int> =
        productsOverviewRepository.countProductsOverviews()

    override suspend fun getProductsOverviews(
        paramsLocal: Params, paramsRemote: Params, forceUpdate: Boolean
    ): ResultData<List<ProductOverviewModel>> =
        productsOverviewRepository.getProductsOverviews(paramsLocal, paramsRemote, forceUpdate)

    override suspend fun saveProductOverview(productOverview: ProductOverviewModel): ResultData<Long> =
        productOverviewLocalDataSource.saveProductOverview(productOverview)

    override suspend fun saveProductsOverviews(
        productsOverviews: List<ProductOverviewModel>
    ): ResultData<List<Long>> = productOverviewLocalDataSource.saveProductsOverviews(productsOverviews)

    override suspend fun deleteAllProductsOverviews(): ResultData<Int> =
        productOverviewLocalDataSource.deleteAllProductsOverviews()

}