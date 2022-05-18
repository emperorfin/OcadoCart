package emperorfin.android.ocadocart.data.repositories

import emperorfin.android.ocadocart.domain.datalayer.datasources.ProductOverviewDataSource
import emperorfin.android.ocadocart.domain.datalayer.repositories.ProductsOverviewRepository
import emperorfin.android.ocadocart.domain.exceptions.ProductOverviewFailure
import emperorfin.android.ocadocart.domain.models.ProductOverviewModel
import emperorfin.android.ocadocart.domain.uilayer.events.inputs.productoverview.Params
import emperorfin.android.ocadocart.domain.uilayer.events.outputs.ResultData
import emperorfin.android.ocadocart.domain.uilayer.events.outputs.ResultData.Success
import emperorfin.android.ocadocart.domain.uilayer.events.outputs.ResultData.Error
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.ConcurrentMap


/**
 * @Author: Francis Nwokelo (emperorfin)
 * @Date: Tuesday 17th May, 2022.
 */


class ProductsOverviewRepositoryImpl(
    private val productOverviewLocalDataSource: ProductOverviewDataSource,
    private val productOverviewRemoteDataSource: ProductOverviewDataSource,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : ProductsOverviewRepository {

    private var cachedProductsOverviews: ConcurrentMap<String, ProductOverviewModel>? = null

    override suspend fun countProductsOverviews(): ResultData<Int> =
        productOverviewLocalDataSource.countProductsOverviews()

    override suspend fun getProductsOverviews(
        paramsLocal: Params, paramsRemote: Params, forceUpdate: Boolean
    ): ResultData<List<ProductOverviewModel>> {
        return withContext(ioDispatcher) {
            // Respond immediately with cache if available and not dirty
            if (!forceUpdate) {
                cachedProductsOverviews?.let { cachedProductsOverviews ->
                    return@withContext Success(cachedProductsOverviews.values.sortedBy { it.title })
                }
            }

            val newProductsOverviews: ResultData<List<ProductOverviewModel>> =
                fetchProductsOverviewsFromRemoteOrLocal(paramsLocal, paramsRemote, forceUpdate)

            // Refresh the cache with the new productsOverviews
            (newProductsOverviews as? Success)?.let { refreshCache(it.data) }

            cachedProductsOverviews?.values?.let { productsOverviews ->
                return@withContext Success(productsOverviews.sortedBy { it.title })
            }

            (newProductsOverviews as? Success)?.let {
                if (it.data.isEmpty()) {
                    return@withContext Success(it.data)
                }
            }

            return@withContext newProductsOverviews as Error
        }
    }

    override suspend fun saveProductOverview(productOverview: ProductOverviewModel): ResultData<Long> {
        TODO("Not yet implemented")
    }

    override suspend fun saveProductsOverviews(productsOverviews: List<ProductOverviewModel>): ResultData<List<Long>> {
        TODO("Not yet implemented")
    }

    override suspend fun deleteAllProductsOverviews(): ResultData<Int> {
        TODO("Not yet implemented")
    }

    private suspend fun fetchProductsOverviewsFromRemoteOrLocal(
        paramsLocal: Params, paramsRemote: Params, forceUpdate: Boolean
    ): ResultData<List<ProductOverviewModel>> {
        // Remote first
        val remoteProductsOverviews = productOverviewRemoteDataSource.getProductsOverviews(paramsRemote)
        when (remoteProductsOverviews) {
            // is Error -> return remoteProductsOverviews // Timber.w("Remote data source fetch failed")
            is Success -> {
                refreshLocalDataSource(remoteProductsOverviews.data)
                return remoteProductsOverviews
            }
            // else -> throw IllegalStateException()
        }

        // Don't read from local if it's forced
        if (forceUpdate) {
            return Error(
                ProductOverviewFailure.RemoteGetProductOverviewError(
                    cause = Exception("Can't force refresh: remote data source is unavailable.")
                )
            )
        }

        // Local if remote fails
        val localProductsOverviews = productOverviewLocalDataSource.getProductsOverviews(paramsLocal)
        if (localProductsOverviews is Success) return localProductsOverviews
//        return Error((localProductsOverviews as Error).failure)
        return Error(
            ProductOverviewFailure.RepositoryGetProductOverviewError(cause = Exception("Error fetching from remote and local."))
        )
    }

    private fun refreshCache(productsOverviews: List<ProductOverviewModel>) {
        cachedProductsOverviews?.clear()
        productsOverviews.sortedBy { it.title }.forEach {
            cacheAndPerform(it) {}
        }
    }

    private suspend fun refreshLocalDataSource(productsOverviews: List<ProductOverviewModel>) {
        productOverviewLocalDataSource.deleteAllProductsOverviews()
        for (productOverview in productsOverviews) {
            productOverviewLocalDataSource.saveProductOverview(productOverview)
        }
    }

    private suspend fun refreshLocalDataSource(productOverview: ProductOverviewModel) {
        productOverviewLocalDataSource.saveProductOverview(productOverview)
    }

    private fun cacheProductOverview(productOverview: ProductOverviewModel): ProductOverviewModel {
        val cachedProductOverview = ProductOverviewModel.newInstance(
            id = productOverview.id,
            title = productOverview.title,
            size = productOverview.size,
            price = productOverview.price,
            imageUrl = productOverview.imageUrl,
            tag = productOverview.tag
        )
        // Create if it doesn't exist.
        if (cachedProductsOverviews == null) {
            cachedProductsOverviews = ConcurrentHashMap()
        }
        cachedProductsOverviews?.put("${cachedProductOverview.id}", cachedProductOverview)
        return cachedProductOverview
    }

    private inline fun cacheAndPerform(productOverview: ProductOverviewModel, perform: (ProductOverviewModel) -> Unit) {
        val cachedProductOverview = cacheProductOverview(productOverview)
        perform(cachedProductOverview)
    }
}