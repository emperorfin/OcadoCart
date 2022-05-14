package emperorfin.android.ocadocart.ui.uimodels.mappers

import emperorfin.android.ocadocart.domain.models.ProductDetailsModel
import emperorfin.android.ocadocart.ui.uimodels.ProductDetailsUiModel


/**
 * @Author: Francis Nwokelo (emperorfin)
 * @Date: Saturday 14th May, 2022.
 */


class ProductDetailsUiModelMapper {

    fun transform(domainModelProductDetails: ProductDetailsModel): ProductDetailsUiModel =
        ProductDetailsUiModel.newInstance(domainModelProductDetails)

}