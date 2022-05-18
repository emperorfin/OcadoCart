package emperorfin.android.ocadocart.data.datasources.remote.frameworks.retrofit.webservices.ocado.endpoints.product.id


/**
 * @Author: Francis Nwokelo (emperorfin)
 * @Date: Wednesday 18th May, 2022.
 */


data class ProductDetail(
    val id: Int, // Use Long next time.
    val price: String,
    val title: String,
    val imageUrl: String,
    val description: String,
    val allergyInformation: String
)
