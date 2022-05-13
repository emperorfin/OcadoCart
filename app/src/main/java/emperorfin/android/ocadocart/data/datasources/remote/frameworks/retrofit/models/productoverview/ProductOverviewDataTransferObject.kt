package emperorfin.android.ocadocart.data.datasources.remote.frameworks.retrofit.models.productoverview

import emperorfin.android.ocadocart.domain.uilayer.events.inputs.productoverview.ProductOverviewDataTransferObjectParams


/**
 * @Author: Francis Nwokelo (emperorfin)
 * @Date: Friday 13th May, 2022.
 */


data class ProductOverviewDataTransferObject(
    override val id: Int,
    override val title: String,
    override val size: String,
    override val price: String,
    override val imageUrl: String
) : ProductOverviewDataTransferObjectParams
