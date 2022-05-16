package emperorfin.android.ocadocart.ui.uimodels

import emperorfin.android.ocadocart.domain.models.ProductDetailsModel
import emperorfin.android.ocadocart.domain.uilayer.events.inputs.productdetails.ProductDetailsUiModelParams


/**
 * @Author: Francis Nwokelo (emperorfin)
 * @Date: Saturday 14th May, 2022.
 */


data class ProductDetailsUiModel(
    override val id: Int,
    override val title: String,
    override val price: String,
    override val imageUrl: String,
    override val description: String,
    override val allergyInformation: String,
    override val size: String = UNDEFINED,
    override val tag: String = UNDEFINED,
    override val priceWithUnit: String = "$$price"
) : ProductDetailsUiModelParams {

    companion object {

        const val UNDEFINED: String = "undefined"

        fun newInstance(domainModelProductDetails: ProductDetailsModel): ProductDetailsUiModel {
            val id: Int = domainModelProductDetails.id
            val title: String = domainModelProductDetails.title
            val price: String = domainModelProductDetails.price
            val imageUrl: String = domainModelProductDetails.imageUrl
            val description: String = domainModelProductDetails.description
            val allergyInformation: String = domainModelProductDetails.allergyInformation

            return ProductDetailsUiModel(
                id = id,
                title = title,
                price = price,
                imageUrl = imageUrl,
                description = description,
                allergyInformation = allergyInformation
            )
        }

        fun newInstance(
            id: Int,
            title: String,
            price: String,
            imageUrl: String,
            description: String,
            allergyInformation: String
        ): ProductDetailsUiModel {

            return ProductDetailsUiModel(
                id = id,
                title = title,
                price = price,
                imageUrl = imageUrl,
                description = description,
                allergyInformation = allergyInformation
            )
        }

    }

}

