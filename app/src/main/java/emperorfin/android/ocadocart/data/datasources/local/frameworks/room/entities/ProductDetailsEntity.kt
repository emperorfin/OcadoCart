package emperorfin.android.ocadocart.data.datasources.local.frameworks.room.entities

import emperorfin.android.ocadocart.domain.models.ProductDetailsModel
import emperorfin.android.ocadocart.domain.uilayer.events.inputs.productdetails.ProductDetailsEntityParams


/**
 * @Author: Francis Nwokelo (emperorfin)
 * @Date: Saturday 14th May, 2022.
 */


data class ProductDetailsEntity(
    override val id: Int,
    override val title: String,
    override val price: String,
    override val imageUrl: String,
    override val description: String,
    override val allergyInformation: String
) : ProductDetailsEntityParams {

    companion object {

        fun newInstance(domainModelProductDetails: ProductDetailsModel): ProductDetailsEntity {
            val id: Int = domainModelProductDetails.id
            val title: String = domainModelProductDetails.title
            val price: String = domainModelProductDetails.price
            val imageUrl: String = domainModelProductDetails.imageUrl
            val description: String = domainModelProductDetails.description
            val allergyInformation: String = domainModelProductDetails.allergyInformation

            return ProductDetailsEntity(
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
        ): ProductDetailsEntity {

            return ProductDetailsEntity(
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

