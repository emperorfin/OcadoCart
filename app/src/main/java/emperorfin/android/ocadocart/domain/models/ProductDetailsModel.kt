package emperorfin.android.ocadocart.domain.models

import emperorfin.android.ocadocart.domain.uilayer.events.inputs.productdetails.ProductDetailsModelParams


/**
 * @Author: Francis Nwokelo (emperorfin)
 * @Date: Saturday 14th May, 2022.
 */


data class ProductDetailsModel(
    override val id: Int,
    override val title: String,
    override val price: String,
    override val imageUrl: String,
    override val description: String,
    override val allergyInformation: String
) : ProductDetailsModelParams
