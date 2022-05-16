package emperorfin.android.ocadocart.data.datasources.local.frameworks.room.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import emperorfin.android.ocadocart.domain.models.ProductOverviewModel
import emperorfin.android.ocadocart.domain.uilayer.events.inputs.productoverview.ProductOverviewEntityParams


/**
 * @Author: Francis Nwokelo (emperorfin)
 * @Date: Friday 13th May, 2022.
 */


@Entity(
    tableName = ProductOverviewEntity.TABLE_NAME,
    primaryKeys = [ProductOverviewEntity.COLUMN_INFO_ID]
)
data class ProductOverviewEntity(
    @ColumnInfo(name = COLUMN_INFO_ID)
    override val id: Int,
    @ColumnInfo(name = COLUMN_INFO_TITLE)
    override val title: String,
    @ColumnInfo(name = COLUMN_INFO_SIZE)
    override val size: String,
    @ColumnInfo(name = COLUMN_INFO_PRICE)
    override val price: String,
    @ColumnInfo(name = COLUMN_INFO_IMAGE_URL)
    override val imageUrl: String,
    @ColumnInfo(name = COLUMN_INFO_TAG)
    override val tag: String
) : ProductOverviewEntityParams {

    companion object {

        const val TABLE_NAME = "table_products_overviews"

        const val COLUMN_INFO_ID = "id"
        const val COLUMN_INFO_TITLE = "title"
        const val COLUMN_INFO_SIZE = "size"
        const val COLUMN_INFO_PRICE = "price"
        const val COLUMN_INFO_IMAGE_URL = "image_url"
        const val COLUMN_INFO_TAG = "tag"

        fun newInstance(domainModelProductOverview: ProductOverviewModel): ProductOverviewEntity {
            val id: Int = domainModelProductOverview.id
            val title: String = domainModelProductOverview.title
            val size: String = domainModelProductOverview.size
            val price: String = domainModelProductOverview.price
            val imageUrl: String = domainModelProductOverview.imageUrl
            val tag: String = domainModelProductOverview.tag

            return ProductOverviewEntity(
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
        ): ProductOverviewEntity {

            return ProductOverviewEntity(
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
