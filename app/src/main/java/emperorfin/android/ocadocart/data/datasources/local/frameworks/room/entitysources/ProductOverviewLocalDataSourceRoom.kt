package emperorfin.android.ocadocart.data.datasources.local.frameworks.room.entitysources

import android.content.Context
import emperorfin.android.ocadocart.R
import emperorfin.android.ocadocart.data.datasources.local.frameworks.room.dao.ProductOverviewDao
import emperorfin.android.ocadocart.data.datasources.local.frameworks.room.entities.mappers.ProductOverviewEntityMapper
import emperorfin.android.ocadocart.domain.datalayer.datasources.ProductOverviewDataSource
import emperorfin.android.ocadocart.domain.models.ProductOverviewModel
import emperorfin.android.ocadocart.domain.models.mappers.ProductOverviewModelMapper
import emperorfin.android.ocadocart.domain.uilayer.events.inputs.productoverview.Params
import emperorfin.android.ocadocart.ui.events.inputs.productoverview.None
import emperorfin.android.ocadocart.ui.events.inputs.productoverview.ProductOverviewParams
import emperorfin.android.ocadocart.domain.uilayer.events.outputs.ResultData
import emperorfin.android.ocadocart.domain.uilayer.events.outputs.ResultData.Success
import emperorfin.android.ocadocart.domain.uilayer.events.outputs.ResultData.Error
import emperorfin.android.ocadocart.domain.exceptions.ProductOverviewFailure.NonExistentDataLocalProductOverviewError
import emperorfin.android.ocadocart.domain.exceptions.ProductOverviewFailure.ListNotAvailableLocalProductOverviewError
import emperorfin.android.ocadocart.domain.exceptions.ProductOverviewFailure.LocalInsertProductOverviewError
import emperorfin.android.ocadocart.domain.exceptions.ProductOverviewFailure.LocalDeleteProductOverviewError
import emperorfin.android.ocadocart.domain.exceptions.ProductOverviewFailure.LocalProductOverviewError
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


/**
 * @Author: Francis Nwokelo (emperorfin)
 * @Date: Monday 16th May, 2022.
 */


class ProductOverviewLocalDataSourceRoom internal constructor(
    private val context: Context,
    private val productOverviewDao: ProductOverviewDao,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO,
    private val productOverviewEntityMapper: ProductOverviewEntityMapper = ProductOverviewEntityMapper(),
    private val productOverviewModelMapper: ProductOverviewModelMapper = ProductOverviewModelMapper()
) : ProductOverviewDataSource {

    companion object {

        const val POSSIBLE_TABLE_ROW_ID = -1L

    }

    override suspend fun countProductsOverviews(): ResultData<Int>  = withContext(ioDispatcher) {
        val numOfProductsOverviews = productOverviewDao.countProductsOverviews()

        if (numOfProductsOverviews > 0)
            return@withContext Success(numOfProductsOverviews)
        else if (numOfProductsOverviews == 0)
            return@withContext Error(
                NonExistentDataLocalProductOverviewError(
                    Exception(context.getString(R.string.error_product_overview_data_unavailable))
                )
            )

        return@withContext Error(LocalProductOverviewError())
    }

    override suspend fun getProductsOverviews(
        params: Params
    ): ResultData<List<ProductOverviewModel>>  = withContext(ioDispatcher) {
        when(params){
            is ProductOverviewParams -> {
                throw IllegalArgumentException(
                    context.getString(R.string.error_inappropriate_argument_passed)
                )
            }
            is None -> {
                return@withContext try {
                    val entityProductOverviews = productOverviewDao.getProductsOverviews()

                    if (entityProductOverviews == null)
                        Error(LocalProductOverviewError())
                    else if (entityProductOverviews.isEmpty())
                        Error(
                            ListNotAvailableLocalProductOverviewError(
                                cause = Exception(
                                    context.getString(R.string.error_products_overviews_unavailable)
                                )
                            )
                        )

                    val modelProductOverviews = entityProductOverviews.map {
                        productOverviewModelMapper.transform(it)
                    }

                    Success(modelProductOverviews)

                }catch (e: Exception){
                    Error(LocalProductOverviewError(cause = e))
                }
            }
            else -> throw NotImplementedError(context.getString(R.string.error_not_yet_implemented))
        }
    }

    override suspend fun saveProductOverview(
        productOverview: ProductOverviewModel
    ): ResultData<Long> = withContext(ioDispatcher){
        val entityProductOverview = productOverviewEntityMapper.transform(productOverview)

        val tableRowId: Long = productOverviewDao.insertProductOverview(entityProductOverview)

        if (tableRowId == POSSIBLE_TABLE_ROW_ID)
            return@withContext Error(LocalInsertProductOverviewError())

        return@withContext Success(tableRowId)
    }

    override suspend fun saveProductsOverviews(
        productsOverviews: List<ProductOverviewModel>
    ): ResultData<List<Long>> = withContext(ioDispatcher){
        if (productsOverviews.isEmpty())
            return@withContext Error(
                LocalInsertProductOverviewError(
                    cause = Exception(context.getString(R.string.error_cant_save_empty_list))
                )
            )

        val entityProductsOverviews = productsOverviews.map {
            productOverviewEntityMapper.transform(it)
        }

        val tableRowIds: List<Long> = productOverviewDao.insertProductsOverviews(entityProductsOverviews)

        if (tableRowIds.size != entityProductsOverviews.size)
            return@withContext Error(
                LocalInsertProductOverviewError(
                    cause = Exception(context.getString(R.string.error_all_products_overviews_not_saved))
                )
            )

        return@withContext Success(tableRowIds)
    }

    override suspend fun deleteAllProductsOverviews(): ResultData<Int> = withContext(ioDispatcher){
        val resultData: ResultData<Int> = countProductsOverviews()

        val numOfProductsOverviews: Int = if(resultData is Error && resultData.failure is LocalProductOverviewError)
            return@withContext Error(
                LocalDeleteProductOverviewError(
                    Exception(context.getString(R.string.error_deleting_all_products_overviews))
                )
            )
        else if(resultData is Error &&
            resultData.failure is NonExistentDataLocalProductOverviewError) {
            0
        } else {
            (resultData as Success).data
        }

        val numOfProductsOverviewsDeleted: Int = productOverviewDao.deleteProductsOverviews()

        if (numOfProductsOverviewsDeleted != numOfProductsOverviews)
            return@withContext Error(
                LocalDeleteProductOverviewError(
                    Exception(context.getString(R.string.error_deleting_all_products_overviews))
                )
            )

        return@withContext Success(numOfProductsOverviewsDeleted)
    }
}
