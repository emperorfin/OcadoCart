package emperorfin.android.ocadocart.data.datasources.local.frameworks.room.entities.mappers

import emperorfin.android.ocadocart.data.datasources.local.frameworks.room.entities.ProductDetailsEntity
import emperorfin.android.ocadocart.domain.models.ProductDetailsModel


/**
 * @Author: Francis Nwokelo (emperorfin)
 * @Date: Saturday 14th May, 2022.
 */


class ProductDetailsEntityMapper {

    fun transform(domainModelProductDetails: ProductDetailsModel): ProductDetailsEntity =
        ProductDetailsEntity.newInstance(domainModelProductDetails)

}