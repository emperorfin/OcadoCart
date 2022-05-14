package emperorfin.android.ocadocart.domain.models.mappers

import emperorfin.android.ocadocart.domain.models.ProductDetailsModel
import emperorfin.android.ocadocart.domain.uilayer.events.inputs.productdetails.ProductDetailsDataTransferObjectParams
import emperorfin.android.ocadocart.domain.uilayer.events.inputs.productdetails.ProductDetailsEntityParams
import emperorfin.android.ocadocart.domain.uilayer.events.inputs.productdetails.ProductDetailsUiModelParams


/**
 * @Author: Francis Nwokelo (emperorfin)
 * @Date: Saturday 14th May, 2022.
 */


class ProductDetailsModelMapper {

    fun transform(dataTransferObjectProductDetails: ProductDetailsDataTransferObjectParams): ProductDetailsModel =
        ProductDetailsModel.newInstance(dataTransferObjectProductDetails)

    fun transform(entityProductDetails: ProductDetailsEntityParams): ProductDetailsModel =
        ProductDetailsModel.newInstance(entityProductDetails)

    fun transform(uiModelProductDetails: ProductDetailsUiModelParams): ProductDetailsModel =
        ProductDetailsModel.newInstance(uiModelProductDetails)

}