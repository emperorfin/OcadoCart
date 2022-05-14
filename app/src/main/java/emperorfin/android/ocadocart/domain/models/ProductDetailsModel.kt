package emperorfin.android.ocadocart.domain.models

import emperorfin.android.ocadocart.domain.uilayer.events.inputs.productdetails.ProductDetailsDataTransferObjectParams
import emperorfin.android.ocadocart.domain.uilayer.events.inputs.productdetails.ProductDetailsEntityParams
import emperorfin.android.ocadocart.domain.uilayer.events.inputs.productdetails.ProductDetailsModelParams
import emperorfin.android.ocadocart.domain.uilayer.events.inputs.productdetails.ProductDetailsUiModelParams


/**
 * @Author: Francis Nwokelo (emperorfin)
 * @Date: Saturday 14th May, 2022.
 */


data class ProductDetailsModel(
    override val id: Int,
    override val title: String,
    override val price: String,
    override val imageUrl: String,
    override val description: String,
    override val allergyInformation: String
) : ProductDetailsModelParams {

    companion object {

        fun newInstance(dataTransferObjectProductDetails: ProductDetailsDataTransferObjectParams): ProductDetailsModel {
            val id: Int = dataTransferObjectProductDetails.id as Int
            val title: String = dataTransferObjectProductDetails.title as String
            val price: String = dataTransferObjectProductDetails.price as String
            val imageUrl: String = dataTransferObjectProductDetails.imageUrl as String
            val description: String = dataTransferObjectProductDetails.description as String
            val allergyInformation: String = dataTransferObjectProductDetails.allergyInformation as String

            return ProductDetailsModel(
                id = id,
                title = title,
                price = price,
                imageUrl = imageUrl,
                description = description,
                allergyInformation = allergyInformation
            )
        }

        fun newInstance(entityProductDetails: ProductDetailsEntityParams): ProductDetailsModel {
            val id: Int = entityProductDetails.id as Int
            val title: String = entityProductDetails.title as String
            val price: String = entityProductDetails.price as String
            val imageUrl: String = entityProductDetails.imageUrl as String
            val description: String = entityProductDetails.description as String
            val allergyInformation: String = entityProductDetails.allergyInformation as String

            return ProductDetailsModel(
                id = id,
                title = title,
                price = price,
                imageUrl = imageUrl,
                description = description,
                allergyInformation = allergyInformation
            )
        }

        fun newInstance(uiModelProductDetails: ProductDetailsUiModelParams): ProductDetailsModel {
            val id: Int = uiModelProductDetails.id as Int
            val title: String = uiModelProductDetails.title as String
            val price: String = uiModelProductDetails.price as String
            val imageUrl: String = uiModelProductDetails.imageUrl as String
            val description: String = uiModelProductDetails.description as String
            val allergyInformation: String = uiModelProductDetails.allergyInformation as String

            return ProductDetailsModel(
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
        ): ProductDetailsModel {

            return ProductDetailsModel(
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
