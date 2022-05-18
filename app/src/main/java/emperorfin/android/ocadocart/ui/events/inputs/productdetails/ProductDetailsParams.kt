package emperorfin.android.ocadocart.ui.events.inputs.productdetails

import emperorfin.android.ocadocart.domain.uilayer.events.inputs.productdetails.ProductDetailsDataTransferObjectParams


/**
 * @Author: Francis Nwokelo (emperorfin)
 * @Date: Wednesday 18th May, 2022.
 */


data class ProductDetailsParams(
    override val id: Int? = null,
    override val title: String? = null,
    override val price: String? = null,
    override val imageUrl: String? = null,
    override val description: String? = null,
    override val allergyInformation: String? = null
) : ProductDetailsDataTransferObjectParams
