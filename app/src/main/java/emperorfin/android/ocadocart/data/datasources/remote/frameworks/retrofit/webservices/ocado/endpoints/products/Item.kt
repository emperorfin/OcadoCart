package emperorfin.android.ocadocart.data.datasources.remote.frameworks.retrofit.webservices.ocado.endpoints.products


/**
 * @Author: Francis Nwokelo (emperorfin)
 * @Date: Friday 13th May, 2022.
 */


data class Item(
    val id: Int,
    val price: String,
    val title: String,
    val size: String,
    val imageUrl: String
)
