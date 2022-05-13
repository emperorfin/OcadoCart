package emperorfin.android.ocadocart.ui.uimodels.mappers

import emperorfin.android.ocadocart.domain.models.ProductOverviewModel
import emperorfin.android.ocadocart.ui.uimodels.ProductOverviewUiModel


/**
 * @Author: Francis Nwokelo (emperorfin)
 * @Date: Friday 13th May, 2022.
 */


class ProductOverviewUiModelMapper {

    fun transform(domainModelProductOverview: ProductOverviewModel): ProductOverviewUiModel =
        ProductOverviewUiModel.newInstance(domainModelProductOverview)

}