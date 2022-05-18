package emperorfin.android.ocadocart.domain.datalayer.datasources

import emperorfin.android.ocadocart.domain.models.ProductDetailsModel
import emperorfin.android.ocadocart.domain.uilayer.events.inputs.productdetails.Params
import emperorfin.android.ocadocart.domain.uilayer.events.outputs.ResultData


/**
 * @Author: Francis Nwokelo (emperorfin)
 * @Date: Wednesday 18th May, 2022.
 */


interface ProductDetailsDataSource {

    suspend fun countProductDetails(): ResultData<Int>

    suspend fun getProductDetails(params: Params): ResultData<ProductDetailsModel>

    suspend fun saveProductOverview(productOverview: ProductDetailsModel): ResultData<Long>

    suspend fun saveProductDetails(productDetails: List<ProductDetailsModel>): ResultData<List<Long>>

    suspend fun deleteAllProductDetails(): ResultData<Int>

}