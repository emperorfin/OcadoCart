package emperorfin.android.ocadocart.data.datasources.local.frameworks.room.entitysources

import emperorfin.android.ocadocart.data.datasources.local.frameworks.room.entities.ProductOverviewEntity
import emperorfin.android.ocadocart.data.datasources.local.frameworks.room.entities.mappers.ProductOverviewEntityMapper
import emperorfin.android.ocadocart.domain.datalayer.datasources.FakeProductOverviewDataSource
import emperorfin.android.ocadocart.domain.exceptions.ProductOverviewFailure.LocalProductOverviewError
import emperorfin.android.ocadocart.domain.exceptions.ProductOverviewFailure.ListNotAvailableLocalProductOverviewError
import emperorfin.android.ocadocart.domain.exceptions.ProductOverviewFailure.LocalInsertProductOverviewError
import emperorfin.android.ocadocart.domain.models.ProductOverviewModel
import emperorfin.android.ocadocart.domain.models.mappers.ProductOverviewModelMapper
import emperorfin.android.ocadocart.domain.uilayer.events.inputs.productoverview.Params
import emperorfin.android.ocadocart.domain.uilayer.events.outputs.ResultData
import emperorfin.android.ocadocart.domain.uilayer.events.outputs.ResultData.Success
import emperorfin.android.ocadocart.domain.uilayer.events.outputs.ResultData.Error
import emperorfin.android.ocadocart.ui.events.inputs.productoverview.None
import emperorfin.android.ocadocart.ui.events.inputs.productoverview.ProductOverviewParams


/**
 * @Author: Francis Nwokelo (emperorfin)
 * @Date: Wednesday 18th May, 2022.
 */


class FakeProductOverviewLocalDataSourceRoom internal constructor(
    private var entityProductsOverviews: MutableList<ProductOverviewEntity>? = mutableListOf(),
    private val productOverviewEntityMapper: ProductOverviewEntityMapper = ProductOverviewEntityMapper(),
    private val productOverviewModelMapper: ProductOverviewModelMapper = ProductOverviewModelMapper()
) : FakeProductOverviewDataSource {
    override suspend fun countProductsOverviews(): ResultData<Int> {
        val countProductsOverviews: Int = entityProductsOverviews?.size ?: return Error(
            LocalProductOverviewError()
        )

        return Success(countProductsOverviews)
    }

    override suspend fun getProductsOverviews(params: Params): ResultData<List<ProductOverviewModel>> {
        when(params){
            is ProductOverviewParams -> {
                throw IllegalArgumentException("The argument passed is inappropriate.")
            }
            is None -> {
                entityProductsOverviews?.let { productsOverviews ->
                    val modelProductsOverviews = productsOverviews.map {
                        productOverviewModelMapper.transform(it)
                    }

                    return if (productsOverviews.isNotEmpty()) {
                        Success(modelProductsOverviews)
                    } else {
                        Error(ListNotAvailableLocalProductOverviewError())
                    }
                }

                return Error(
                    ListNotAvailableLocalProductOverviewError(
                        cause = Exception(
                            "List of products overviews not available."
                        )
                    )
                )
            }
            else -> throw NotImplementedError("Not yet implemented.")
        }
    }

    override suspend fun saveProductOverview(productOverview: ProductOverviewModel): ResultData<Long> {
        val entityProductOverview = productOverviewEntityMapper.transform(productOverview)

        val hasInserted: Boolean? = entityProductsOverviews?.add(entityProductOverview)

        if (hasInserted != null && hasInserted) {
            val arrayElementIndex: Int? = entityProductsOverviews?.size?.minus(1)

            arrayElementIndex?.let { return@let Success(it) }
        }

        return Error(LocalInsertProductOverviewError())
    }

    override suspend fun saveProductsOverviews(productsOverviews: List<ProductOverviewModel>): ResultData<List<Long>> {
        val initialArraySize: Int = entityProductsOverviews?.size ?: return Error(LocalProductOverviewError())

        if (productsOverviews.isEmpty())
            return Error(LocalInsertProductOverviewError(cause = Exception("An empty list cannot be saved.")))

        val entityProductOverviewList = productsOverviews.map {
            productOverviewEntityMapper.transform(it)
        }

        entityProductsOverviews?.addAll(entityProductOverviewList)

        val currentArraySize: Int? = entityProductsOverviews?.size

        currentArraySize?.let {
            return@let if (it > initialArraySize) {
                val arrayInsertionSize: Int = it - initialArraySize

                Success(arrayInsertionSize)
            } else {
                Error(LocalProductOverviewError())
            }
        }

        return Error(LocalProductOverviewError())
    }

    override suspend fun deleteAllProductsOverviews(): ResultData<Int> {
        val deletedArrayElementSize: Int = entityProductsOverviews?.size ?: return Error(LocalProductOverviewError())

        entityProductsOverviews?.clear() ?: return Error(LocalProductOverviewError())

        return Success(deletedArrayElementSize)
    }
}