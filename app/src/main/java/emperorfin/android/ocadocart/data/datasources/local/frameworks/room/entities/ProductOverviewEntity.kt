package emperorfin.android.ocadocart.data.datasources.local.frameworks.room.entities

import emperorfin.android.ocadocart.domain.uilayer.events.inputs.productoverview.ProductOverviewEntityParams


/**
 * @Author: Francis Nwokelo (emperorfin)
 * @Date: Friday 13th May, 2022.
 */


data class ProductOverviewEntity(
    override val id: Int,
    override val title: String,
    override val size: String,
    override val price: String,
    override val imageUrl: String,
    override val tag: String
) : ProductOverviewEntityParams
