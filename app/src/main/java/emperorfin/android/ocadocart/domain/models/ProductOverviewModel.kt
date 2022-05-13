package emperorfin.android.ocadocart.domain.models

import emperorfin.android.ocadocart.domain.uilayer.events.inputs.productoverview.ProductOverviewModelParams


/**
 * @Author: Francis Nwokelo (emperorfin)
 * @Date: Friday 13th May, 2022.
 */


data class ProductOverviewModel(
    override val id: Int,
    override val title: String,
    override val size: String,
    override val price: String,
    override val imageUrl: String,
    override val tag: String
) : ProductOverviewModelParams
