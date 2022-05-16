package emperorfin.android.ocadocart.ui.events.inputs.productoverview

import emperorfin.android.ocadocart.domain.uilayer.events.inputs.productoverview.ProductOverviewDataTransferObjectParams


/**
 * @Author: Francis Nwokelo (emperorfin)
 * @Date: Monday 16th May, 2022.
 */


data class ProductOverviewParams(
    override val id: Int? = null,
    override val title: String? = null,
    override val size: String? = null,
    override val price: String? = null,
    override val imageUrl: String? = null,
    override val tag: String? = null
) : ProductOverviewDataTransferObjectParams
