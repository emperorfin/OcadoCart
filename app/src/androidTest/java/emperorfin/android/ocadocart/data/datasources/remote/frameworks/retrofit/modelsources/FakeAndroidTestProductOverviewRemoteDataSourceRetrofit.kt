package emperorfin.android.ocadocart.data.remote.frameworks.retrofit.modelsources

import emperorfin.android.ocadocart.data.datasources.remote.frameworks.retrofit.models.productoverview.ProductOverviewDataTransferObject
import emperorfin.android.ocadocart.domain.datalayer.datasources.FakeAndroidTestProductOverviewDataSource
import emperorfin.android.ocadocart.domain.models.ProductOverviewModel
import emperorfin.android.ocadocart.domain.models.mappers.ProductOverviewModelMapper
import emperorfin.android.ocadocart.domain.uilayer.events.inputs.productoverview.Params
import emperorfin.android.ocadocart.domain.uilayer.events.outputs.ResultData
import emperorfin.android.ocadocart.domain.exceptions.ProductOverviewFailure.ListNotAvailableRemoteProductOverviewError
import emperorfin.android.ocadocart.domain.uilayer.events.outputs.ResultData.Success
import emperorfin.android.ocadocart.domain.uilayer.events.outputs.ResultData.Error
import emperorfin.android.ocadocart.ui.events.inputs.productoverview.None
import emperorfin.android.ocadocart.ui.events.inputs.productoverview.ProductOverviewParams


/**
 * @Author: Francis Nwokelo (emperorfin)
 * @Date: Thursday 19th May, 2022.
 */


class FakeAndroidTestProductOverviewRemoteDataSourceRetrofit internal constructor(
    private var dataTransferObjectProductsOverviews: MutableList<ProductOverviewDataTransferObject>? = mutableListOf(),
    private val productOverviewModelMapper: ProductOverviewModelMapper = ProductOverviewModelMapper()
) : FakeAndroidTestProductOverviewDataSource {
    override suspend fun countProductsOverviews(): ResultData<Int> {
        TODO("Not yet implemented")
    }

    override suspend fun getProductsOverviews(params: Params): ResultData<List<ProductOverviewModel>> {
        when(params){
            is None -> {
                dataTransferObjectProductsOverviews?.let { productsOverviews ->
                    val modelProductsOverviews = productsOverviews.map {
                        productOverviewModelMapper.transform(it)
                    }

                    return if (productsOverviews.isNotEmpty()) {
                        Success(modelProductsOverviews)
                    } else {
                        Error(ListNotAvailableRemoteProductOverviewError())
                    }
                }

                return Error(
                    ListNotAvailableRemoteProductOverviewError(
                        cause = Exception(
                            "List of products overviews not available."
                        )
                    )
                )
            }
            is ProductOverviewParams -> {
                throw IllegalArgumentException("The argument passed is inappropriate.")
            }
            else -> throw NotImplementedError("Not yet implemented.")
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
}