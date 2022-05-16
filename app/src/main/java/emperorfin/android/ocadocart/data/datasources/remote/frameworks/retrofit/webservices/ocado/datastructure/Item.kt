package emperorfin.android.ocadocart.data.datasources.remote.frameworks.retrofit.webservices.ocado.datastructure


/**
 * @Author: Francis Nwokelo (emperorfin)
 * @Date: Friday 13th May, 2022.
 */


data class Item(
    val id: Int,
    val title: String,
    val size: String,
    val price: String,
    val imageUrl: String
)
