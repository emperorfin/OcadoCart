package emperorfin.android.ocadocart.data.datasources.remote.frameworks.retrofit.models.mappers

import emperorfin.android.ocadocart.data.datasources.remote.frameworks.retrofit.models.productoverview.ProductOverviewDataTransferObject
import emperorfin.android.ocadocart.domain.models.ProductOverviewModel


/**
 * @Author: Francis Nwokelo (emperorfin)
 * @Date: Friday 13th May, 2022.
 */


class ProductOverviewDataTransferObjectMapper {

    fun transform(domainModelProductOverview: ProductOverviewModel): ProductOverviewDataTransferObject =
        ProductOverviewDataTransferObject.newInstance(domainModelProductOverview)

}