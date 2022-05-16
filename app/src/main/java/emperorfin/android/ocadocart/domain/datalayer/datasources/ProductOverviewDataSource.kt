package emperorfin.android.ocadocart.domain.datalayer.datasources

import emperorfin.android.ocadocart.domain.models.ProductOverviewModel
import emperorfin.android.ocadocart.domain.uilayer.events.inputs.productoverview.Params
import emperorfin.android.ocadocart.domain.uilayer.events.outputs.ResultData


/**
 * @Author: Francis Nwokelo (emperorfin)
 * @Date: Monday 16th May, 2022.
 */


interface ProductOverviewDataSource {

    suspend fun countProductsOverviews(): ResultData<Int>

    suspend fun getProductsOverviews(params: Params): ResultData<List<ProductOverviewModel>>

    suspend fun saveProductOverview(productOverview: ProductOverviewModel): ResultData<Long>

    suspend fun saveProductsOverviews(productsOverviews: List<ProductOverviewModel>): ResultData<List<Long>>

    suspend fun deleteAllProductsOverviews(): ResultData<Int>

}