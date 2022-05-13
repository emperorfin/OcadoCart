package emperorfin.android.ocadocart.ui.uimodels

import emperorfin.android.ocadocart.domain.uilayer.events.inputs.productoverview.ProductOverviewUiModelParams


/**
 * @Author: Francis Nwokelo (emperorfin)
 * @Date: Friday 13th May, 2022.
 */


data class ProductOverviewUiModel(
    override val id: Int,
    override val title: String,
    override val size: String,
    override val price: String,
    override val imageUrl: String,
    override val tag: String,
    override val priceWithUnit: String = "$$price"
) : ProductOverviewUiModelParams
