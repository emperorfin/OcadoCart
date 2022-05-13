package emperorfin.android.ocadocart.data.datasources.remote.frameworks.retrofit.models.productoverview


/**
 * @Author: Francis Nwokelo (emperorfin)
 * @Date: Friday 13th May, 2022.
 */


data class ProductsCluster(
    val tag: String,
    val items: List<ProductOverviewDataTransferObject>
)
