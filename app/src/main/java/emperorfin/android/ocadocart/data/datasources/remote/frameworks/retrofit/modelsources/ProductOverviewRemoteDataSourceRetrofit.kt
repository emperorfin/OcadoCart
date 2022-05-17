package emperorfin.android.ocadocart.data.datasources.remote.frameworks.retrofit.modelsources

import android.content.Context
import emperorfin.android.ocadocart.R
import emperorfin.android.ocadocart.data.datasources.remote.frameworks.retrofit.models.productoverview.ProductOverviewDataTransferObject
import emperorfin.android.ocadocart.data.datasources.remote.frameworks.retrofit.webservices.ocado.datastructure.ItemsCluster
import emperorfin.android.ocadocart.data.datasources.remote.frameworks.retrofit.webservices.ocado.service.OcadoService
import emperorfin.android.ocadocart.domain.datalayer.datasources.ProductOverviewDataSource
import emperorfin.android.ocadocart.domain.models.ProductOverviewModel
import emperorfin.android.ocadocart.domain.models.mappers.ProductOverviewModelMapper
import emperorfin.android.ocadocart.domain.uilayer.events.inputs.productoverview.Params
import emperorfin.android.ocadocart.ui.events.inputs.productoverview.ProductOverviewParams
import emperorfin.android.ocadocart.domain.uilayer.events.outputs.ResultData
import emperorfin.android.ocadocart.domain.uilayer.events.outputs.ResultData.Success
import emperorfin.android.ocadocart.domain.uilayer.events.outputs.ResultData.Error
import emperorfin.android.ocadocart.domain.exceptions.ProductOverviewFailure.NonExistentDataRemoteProductOverviewError
import emperorfin.android.ocadocart.domain.exceptions.ProductOverviewFailure.ListNotAvailableRemoteProductOverviewError
import emperorfin.android.ocadocart.domain.exceptions.ProductOverviewFailure.RemoteInsertProductOverviewError
import emperorfin.android.ocadocart.domain.exceptions.ProductOverviewFailure.RemoteDeleteProductOverviewError
import emperorfin.android.ocadocart.domain.exceptions.ProductOverviewFailure.RemoteProductOverviewError
import emperorfin.android.ocadocart.ui.events.inputs.productoverview.None
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


/**
 * @Author: Francis Nwokelo (emperorfin)
 * @Date: Tuesday 17th May, 2022.
 */


class ProductOverviewRemoteDataSourceRetrofit internal constructor(
    private val context: Context,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO,
    private val productOverviewModelMapper: ProductOverviewModelMapper = ProductOverviewModelMapper()
) : ProductOverviewDataSource {
    override suspend fun countProductsOverviews(): ResultData<Int> {
        TODO("Not yet implemented")
    }

    override suspend fun getProductsOverviews(
        params: Params
    ): ResultData<List<ProductOverviewModel>> = withContext(ioDispatcher) {
        when(params){
            is None -> {

                val response = OcadoService.INSTANCE.getProductsOverviews()

                withContext(Dispatchers.Main){
                    if (response.isSuccessful){ // && response.body() != null
                        response.body()?.let {

                            it.clusters?.let {productsOverviewsClusters ->

                                return@withContext if (productsOverviewsClusters.isNotEmpty()) {
                                    val modelProductsOverviews: List<ProductOverviewModel> =
                                        buildProductOverviewModelList(productsOverviewsClusters)

                                    Success(modelProductsOverviews)
                                } else {
                                    Error(ListNotAvailableRemoteProductOverviewError())
                                }
                            }
                        }
                    }
                    return@withContext Error(
                        RemoteProductOverviewError(
                            Exception(context.
                            getString(R.string.error_unsuccesful_products_overviews_request) +
                                    response.code()
                            )
                        )
                    )
                }
            }
            is ProductOverviewParams -> {
                throw IllegalArgumentException(
                    context.getString(R.string.error_inappropriate_argument_passed)
                )
            }
            else -> throw NotImplementedError(context.getString(R.string.error_not_yet_implemented))
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

    private fun buildProductOverviewModelList(
        productsOverviewsClusters: List<ItemsCluster>
    ): List<ProductOverviewModel> {
        val dataTransferObjectProductsOverviews: ArrayList<ProductOverviewDataTransferObject> =
            ArrayList()

        for (productsOverviewsCluster in productsOverviewsClusters){

            for (productOverview in productsOverviewsCluster.items) {
                val dataTransferObjectProductOverview = ProductOverviewDataTransferObject(
                    id = productOverview.id,
                    title = productOverview.title,
                    size = productOverview.size,
                    price = productOverview.price,
                    imageUrl = productOverview.imageUrl,
                    tag = productsOverviewsCluster.tag
                )

                dataTransferObjectProductsOverviews.add(dataTransferObjectProductOverview)
            }

        }

        return dataTransferObjectProductsOverviews.map {
            productOverviewModelMapper.transform(it)
        }
    }
}