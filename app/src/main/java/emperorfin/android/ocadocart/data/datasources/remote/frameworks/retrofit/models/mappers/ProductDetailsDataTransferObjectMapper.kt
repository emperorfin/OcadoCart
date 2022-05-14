package emperorfin.android.ocadocart.data.datasources.remote.frameworks.retrofit.models.mappers

import emperorfin.android.ocadocart.data.datasources.remote.frameworks.retrofit.models.productdetails.ProductDetailsDataTransferObject
import emperorfin.android.ocadocart.domain.models.ProductDetailsModel


/**
 * @Author: Francis Nwokelo (emperorfin)
 * @Date: Saturday 14th May, 2022.
 */


class ProductDetailsDataTransferObjectMapper {

    fun transform(domainModelProductDetails: ProductDetailsModel): ProductDetailsDataTransferObject =
        ProductDetailsDataTransferObject.newInstance(domainModelProductDetails)

}