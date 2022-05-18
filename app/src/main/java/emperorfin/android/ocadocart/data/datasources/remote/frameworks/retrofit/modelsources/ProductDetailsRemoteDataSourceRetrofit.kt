package emperorfin.android.ocadocart.data.datasources.remote.frameworks.retrofit.modelsources

import android.content.Context
import emperorfin.android.ocadocart.R
import emperorfin.android.ocadocart.data.datasources.remote.frameworks.retrofit.models.productdetails.ProductDetailsDataTransferObject
import emperorfin.android.ocadocart.data.datasources.remote.frameworks.retrofit.webservices.ocado.service.OcadoService
import emperorfin.android.ocadocart.domain.datalayer.datasources.ProductDetailsDataSource
import emperorfin.android.ocadocart.domain.exceptions.ProductDetailsFailure.RemoteProductDetailsError
import emperorfin.android.ocadocart.domain.models.ProductDetailsModel
import emperorfin.android.ocadocart.domain.models.mappers.ProductDetailsModelMapper
import emperorfin.android.ocadocart.domain.uilayer.events.inputs.productdetails.Params
import emperorfin.android.ocadocart.ui.events.inputs.productdetails.None
import emperorfin.android.ocadocart.ui.events.inputs.productdetails.ProductDetailsParams
import emperorfin.android.ocadocart.domain.uilayer.events.outputs.ResultData
import emperorfin.android.ocadocart.domain.uilayer.events.outputs.ResultData.Success
import emperorfin.android.ocadocart.domain.uilayer.events.outputs.ResultData.Error
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


/**
 * @Author: Francis Nwokelo (emperorfin)
 * @Date: Wednesday 18th May, 2022.
 */


class ProductDetailsRemoteDataSourceRetrofit internal constructor(
    private val context: Context,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO,
    private val productDetailsModelMapper: ProductDetailsModelMapper = ProductDetailsModelMapper()
) : ProductDetailsDataSource {
    override suspend fun countProductDetails(): ResultData<Int> {
        TODO("Not yet implemented")
    }

    override suspend fun getProductDetails(
        params: Params
    ): ResultData<ProductDetailsModel> = withContext(ioDispatcher) {
        when(params){
            is ProductDetailsParams -> {

                val productId = params.id
                    ?: throw IllegalArgumentException(
                        context.getString(R.string.error_product_id_must_not_be_null)
                    )

                val response = OcadoService.INSTANCE.getProductDetails(productId)

                withContext(Dispatchers.Main){
                    if (response.isSuccessful){ // && response.body() != null
                        response.body()?.let {

                            val dataTransferObjectProductDetails = ProductDetailsDataTransferObject(
                                id = it.id,
                                title = it.title,
                                price = it.price,
                                imageUrl = it.imageUrl,
                                description = it.description,
                                allergyInformation = it.allergyInformation
                            )

                            val modelProductDetails: ProductDetailsModel =
                                productDetailsModelMapper.transform(dataTransferObjectProductDetails)

                            return@withContext Success(modelProductDetails)
                        }
                    }
                    return@withContext Error(
                        RemoteProductDetailsError(
                            Exception(
                                context.getString(R.string.error_unsuccesful_product_details_request) +
                                        response.code()
                            )
                        )
                    )
                }
            }
            is None -> {
                throw IllegalArgumentException(
                    context.getString(R.string.error_inappropriate_argument_passed)
                )
            }
            else -> throw NotImplementedError(context.getString(R.string.error_not_yet_implemented))
        }
    }

    override suspend fun saveProductOverview(productOverview: ProductDetailsModel): ResultData<Long> {
        TODO("Not yet implemented")
    }

    override suspend fun saveProductDetails(productDetails: List<ProductDetailsModel>): ResultData<List<Long>> {
        TODO("Not yet implemented")
    }

    override suspend fun deleteAllProductDetails(): ResultData<Int> {
        TODO("Not yet implemented")
    }
}