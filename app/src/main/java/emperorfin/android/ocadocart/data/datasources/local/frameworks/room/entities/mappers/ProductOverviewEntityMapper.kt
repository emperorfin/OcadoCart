package emperorfin.android.ocadocart.data.datasources.local.frameworks.room.entities.mappers

import emperorfin.android.ocadocart.data.datasources.local.frameworks.room.entities.ProductOverviewEntity
import emperorfin.android.ocadocart.domain.models.ProductOverviewModel


/**
 * @Author: Francis Nwokelo (emperorfin)
 * @Date: Friday 13th May, 2022.
 */


class ProductOverviewEntityMapper {

    fun transform(domainModelProductOverview: ProductOverviewModel): ProductOverviewEntity =
        ProductOverviewEntity.newInstance(domainModelProductOverview)

}