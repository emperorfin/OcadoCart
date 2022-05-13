package emperorfin.android.ocadocart.domain.models

import emperorfin.android.ocadocart.domain.uilayer.events.inputs.productoverview.ProductOverviewDataTransferObjectParams
import emperorfin.android.ocadocart.domain.uilayer.events.inputs.productoverview.ProductOverviewEntityParams
import emperorfin.android.ocadocart.domain.uilayer.events.inputs.productoverview.ProductOverviewModelParams
import emperorfin.android.ocadocart.domain.uilayer.events.inputs.productoverview.ProductOverviewUiModelParams


/**
 * @Author: Francis Nwokelo (emperorfin)
 * @Date: Friday 13th May, 2022.
 */


data class ProductOverviewModel(
    override val id: Int,
    override val title: String,
    override val size: String,
    override val price: String,
    override val imageUrl: String,
    override val tag: String
) : ProductOverviewModelParams {

    companion object {

        fun newInstance(dataTransferObjectProductOverview: ProductOverviewDataTransferObjectParams): ProductOverviewModel {
            val id: Int = dataTransferObjectProductOverview.id as Int
            val title: String = dataTransferObjectProductOverview.title as String
            val size: String = dataTransferObjectProductOverview.size as String
            val price: String = dataTransferObjectProductOverview.price as String
            val imageUrl: String = dataTransferObjectProductOverview.imageUrl as String
            val tag: String = dataTransferObjectProductOverview.tag as String

            return ProductOverviewModel(
                id = id,
                title = title,
                size = size,
                price = price,
                imageUrl = imageUrl,
                tag = tag
            )
        }

        fun newInstance(entityProductOverview: ProductOverviewEntityParams): ProductOverviewModel {
            val id: Int = entityProductOverview.id as Int
            val title: String = entityProductOverview.title as String
            val size: String = entityProductOverview.size as String
            val price: String = entityProductOverview.price as String
            val imageUrl: String = entityProductOverview.imageUrl as String
            val tag: String = entityProductOverview.tag as String

            return ProductOverviewModel(
                id = id,
                title = title,
                size = size,
                price = price,
                imageUrl = imageUrl,
                tag = tag
            )
        }

        fun newInstance(uiModelProductOverview: ProductOverviewUiModelParams): ProductOverviewModel {
            val id: Int = uiModelProductOverview.id as Int
            val title: String = uiModelProductOverview.title as String
            val size: String = uiModelProductOverview.size as String
            val price: String = uiModelProductOverview.price as String
            val imageUrl: String = uiModelProductOverview.imageUrl as String
            val tag: String = uiModelProductOverview.tag as String

            return ProductOverviewModel(
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
        ): ProductOverviewModel {

            return ProductOverviewModel(
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
