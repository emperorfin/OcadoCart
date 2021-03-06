package emperorfin.android.ocadocart.data.datasources.remote.frameworks.retrofit.models.productoverview

import emperorfin.android.ocadocart.domain.models.ProductOverviewModel
import emperorfin.android.ocadocart.domain.uilayer.events.inputs.productoverview.ProductOverviewDataTransferObjectParams


/**
 * @Author: Francis Nwokelo (emperorfin)
 * @Date: Friday 13th May, 2022.
 */


data class ProductOverviewDataTransferObject(
    override val id: Int,
    override val title: String,
    override val size: String,
    override val price: String,
    override val imageUrl: String,
    override val tag: String
) : ProductOverviewDataTransferObjectParams {

    companion object {

        fun newInstance(domainModelProductOverview: ProductOverviewModel): ProductOverviewDataTransferObject {
            val id: Int = domainModelProductOverview.id
            val title: String = domainModelProductOverview.title
            val size: String = domainModelProductOverview.size
            val price: String = domainModelProductOverview.price
            val imageUrl: String = domainModelProductOverview.imageUrl
            val tag: String = domainModelProductOverview.tag

            return ProductOverviewDataTransferObject(
                id = id,
                title = title,
                size = size,
                price = price,
                imageUrl = imageUrl,
                tag = tag
            )
        }

        fun newInstance(
            id: Int,
            title: String,
            size: String,
            price: String,
            imageUrl: String,
            tag: String
        ): ProductOverviewDataTransferObject {

            return ProductOverviewDataTransferObject(
                id = id,
                title = title,
                size = size,
                price = price,
                imageUrl = imageUrl,
                tag = tag
            )
        }

    }

}
