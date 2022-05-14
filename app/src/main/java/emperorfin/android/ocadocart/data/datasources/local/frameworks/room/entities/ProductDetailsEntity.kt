package emperorfin.android.ocadocart.data.datasources.local.frameworks.room.entities

import emperorfin.android.ocadocart.domain.uilayer.events.inputs.productdetails.ProductDetailsEntityParams


/**
 * @Author: Francis Nwokelo (emperorfin)
 * @Date: Saturday 14th May, 2022.
 */


data class ProductDetailsEntity(
    override val id: Int,
    override val title: String,
    override val price: String,
    override val imageUrl: String,
    override val description: String,
    override val allergyInformation: String
) : ProductDetailsEntityParams
