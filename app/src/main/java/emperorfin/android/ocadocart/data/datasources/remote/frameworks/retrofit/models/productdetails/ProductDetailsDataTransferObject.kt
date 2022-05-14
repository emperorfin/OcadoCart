package emperorfin.android.ocadocart.data.datasources.remote.frameworks.retrofit.models.productdetails

import emperorfin.android.ocadocart.domain.uilayer.events.inputs.productdetails.ProductDetailsDataTransferObjectParams


/**
 * @Author: Francis Nwokelo (emperorfin)
 * @Date: Saturday 14th May, 2022.
 */


data class ProductDetailsDataTransferObject(
    override val id: Int,
    override val title: String,
    override val price: String,
    override val imageUrl: String,
    override val description: String,
    override val allergyInformation: String
) : ProductDetailsDataTransferObjectParams
