package emperorfin.android.ocadocart.domain.models.mappers

import emperorfin.android.ocadocart.domain.models.ProductOverviewModel
import emperorfin.android.ocadocart.domain.uilayer.events.inputs.productoverview.ProductOverviewDataTransferObjectParams
import emperorfin.android.ocadocart.domain.uilayer.events.inputs.productoverview.ProductOverviewEntityParams
import emperorfin.android.ocadocart.domain.uilayer.events.inputs.productoverview.ProductOverviewUiModelParams


/**
 * @Author: Francis Nwokelo (emperorfin)
 * @Date: Friday 13th May, 2022.
 */


class ProductOverviewModelMapper {

    fun transform(dataTransferObjectProductOverview: ProductOverviewDataTransferObjectParams): ProductOverviewModel =
        ProductOverviewModel.newInstance(dataTransferObjectProductOverview)

    fun transform(entityProductOverview: ProductOverviewEntityParams): ProductOverviewModel =
        ProductOverviewModel.newInstance(entityProductOverview)

    fun transform(uiModelProductOverview: ProductOverviewUiModelParams): ProductOverviewModel =
        ProductOverviewModel.newInstance(uiModelProductOverview)

}