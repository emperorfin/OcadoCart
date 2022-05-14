package emperorfin.android.ocadocart.data.utils

import emperorfin.android.ocadocart.data.datasources.local.frameworks.room.entities.ProductOverviewEntity
import emperorfin.android.ocadocart.data.constants.ProductsOverviewsSampleDataConstants.ID_1
import emperorfin.android.ocadocart.data.constants.ProductsOverviewsSampleDataConstants.ID_2
import emperorfin.android.ocadocart.data.constants.ProductsOverviewsSampleDataConstants.ID_3
import emperorfin.android.ocadocart.data.constants.ProductsOverviewsSampleDataConstants.ID_4
import emperorfin.android.ocadocart.data.constants.ProductsOverviewsSampleDataConstants.ID_5
import emperorfin.android.ocadocart.data.constants.ProductsOverviewsSampleDataConstants.ID_6
import emperorfin.android.ocadocart.data.constants.ProductsOverviewsSampleDataConstants.ID_7
import emperorfin.android.ocadocart.data.constants.ProductsOverviewsSampleDataConstants.ID_8
import emperorfin.android.ocadocart.data.constants.ProductsOverviewsSampleDataConstants.ID_9
import emperorfin.android.ocadocart.data.constants.ProductsOverviewsSampleDataConstants.ID_10

import emperorfin.android.ocadocart.data.constants.ProductsOverviewsSampleDataConstants.TITLE_1
import emperorfin.android.ocadocart.data.constants.ProductsOverviewsSampleDataConstants.TITLE_2
import emperorfin.android.ocadocart.data.constants.ProductsOverviewsSampleDataConstants.TITLE_3
import emperorfin.android.ocadocart.data.constants.ProductsOverviewsSampleDataConstants.TITLE_4
import emperorfin.android.ocadocart.data.constants.ProductsOverviewsSampleDataConstants.TITLE_5
import emperorfin.android.ocadocart.data.constants.ProductsOverviewsSampleDataConstants.TITLE_6
import emperorfin.android.ocadocart.data.constants.ProductsOverviewsSampleDataConstants.TITLE_7
import emperorfin.android.ocadocart.data.constants.ProductsOverviewsSampleDataConstants.TITLE_8
import emperorfin.android.ocadocart.data.constants.ProductsOverviewsSampleDataConstants.TITLE_9
import emperorfin.android.ocadocart.data.constants.ProductsOverviewsSampleDataConstants.TITLE_10

import emperorfin.android.ocadocart.data.constants.ProductsOverviewsSampleDataConstants.SIZE_1
import emperorfin.android.ocadocart.data.constants.ProductsOverviewsSampleDataConstants.SIZE_2
import emperorfin.android.ocadocart.data.constants.ProductsOverviewsSampleDataConstants.SIZE_3
import emperorfin.android.ocadocart.data.constants.ProductsOverviewsSampleDataConstants.SIZE_4
import emperorfin.android.ocadocart.data.constants.ProductsOverviewsSampleDataConstants.SIZE_5
import emperorfin.android.ocadocart.data.constants.ProductsOverviewsSampleDataConstants.SIZE_6
import emperorfin.android.ocadocart.data.constants.ProductsOverviewsSampleDataConstants.SIZE_7
import emperorfin.android.ocadocart.data.constants.ProductsOverviewsSampleDataConstants.SIZE_8
import emperorfin.android.ocadocart.data.constants.ProductsOverviewsSampleDataConstants.SIZE_9
import emperorfin.android.ocadocart.data.constants.ProductsOverviewsSampleDataConstants.SIZE_10

import emperorfin.android.ocadocart.data.constants.ProductsOverviewsSampleDataConstants.PRICE_1
import emperorfin.android.ocadocart.data.constants.ProductsOverviewsSampleDataConstants.PRICE_2
import emperorfin.android.ocadocart.data.constants.ProductsOverviewsSampleDataConstants.PRICE_3
import emperorfin.android.ocadocart.data.constants.ProductsOverviewsSampleDataConstants.PRICE_4
import emperorfin.android.ocadocart.data.constants.ProductsOverviewsSampleDataConstants.PRICE_5
import emperorfin.android.ocadocart.data.constants.ProductsOverviewsSampleDataConstants.PRICE_6
import emperorfin.android.ocadocart.data.constants.ProductsOverviewsSampleDataConstants.PRICE_7
import emperorfin.android.ocadocart.data.constants.ProductsOverviewsSampleDataConstants.PRICE_8
import emperorfin.android.ocadocart.data.constants.ProductsOverviewsSampleDataConstants.PRICE_9
import emperorfin.android.ocadocart.data.constants.ProductsOverviewsSampleDataConstants.PRICE_10

import emperorfin.android.ocadocart.data.constants.ProductsOverviewsSampleDataConstants.IMAGE_URL_1
import emperorfin.android.ocadocart.data.constants.ProductsOverviewsSampleDataConstants.IMAGE_URL_2
import emperorfin.android.ocadocart.data.constants.ProductsOverviewsSampleDataConstants.IMAGE_URL_3
import emperorfin.android.ocadocart.data.constants.ProductsOverviewsSampleDataConstants.IMAGE_URL_4
import emperorfin.android.ocadocart.data.constants.ProductsOverviewsSampleDataConstants.IMAGE_URL_5
import emperorfin.android.ocadocart.data.constants.ProductsOverviewsSampleDataConstants.IMAGE_URL_6
import emperorfin.android.ocadocart.data.constants.ProductsOverviewsSampleDataConstants.IMAGE_URL_7
import emperorfin.android.ocadocart.data.constants.ProductsOverviewsSampleDataConstants.IMAGE_URL_8
import emperorfin.android.ocadocart.data.constants.ProductsOverviewsSampleDataConstants.IMAGE_URL_9
import emperorfin.android.ocadocart.data.constants.ProductsOverviewsSampleDataConstants.IMAGE_URL_10

import emperorfin.android.ocadocart.data.constants.ProductsOverviewsSampleDataConstants.TAG_1
import emperorfin.android.ocadocart.data.constants.ProductsOverviewsSampleDataConstants.TAG_2
import emperorfin.android.ocadocart.data.constants.ProductsOverviewsSampleDataConstants.TAG_3
import emperorfin.android.ocadocart.data.constants.ProductsOverviewsSampleDataConstants.TAG_4
import emperorfin.android.ocadocart.data.constants.ProductsOverviewsSampleDataConstants.TAG_5
import emperorfin.android.ocadocart.data.constants.ProductsOverviewsSampleDataConstants.TAG_6
import emperorfin.android.ocadocart.data.constants.ProductsOverviewsSampleDataConstants.TAG_7
import emperorfin.android.ocadocart.data.constants.ProductsOverviewsSampleDataConstants.TAG_8
import emperorfin.android.ocadocart.data.constants.ProductsOverviewsSampleDataConstants.TAG_9
import emperorfin.android.ocadocart.data.constants.ProductsOverviewsSampleDataConstants.TAG_10


/**
 * @Author: Francis Nwokelo (emperorfin)
 * @Date: Saturday 14th May, 2022.
 */


object ProductsOverviewsSampleDataGeneratorUtil {

    fun getProductOverviewEntityList(): ArrayList<ProductOverviewEntity> {
        val productsOverviews: ArrayList<ProductOverviewEntity> = ArrayList()

        var productOverview = ProductOverviewEntity.newInstance(
            ID_1,
            TITLE_1,
            SIZE_1,
            PRICE_1,
            IMAGE_URL_1,
            TAG_1
        )

        productsOverviews.add(productOverview)

        productOverview = ProductOverviewEntity.newInstance(
            ID_2,
            TITLE_2,
            SIZE_2,
            PRICE_2,
            IMAGE_URL_2,
            TAG_2
        )

        productsOverviews.add(productOverview)

        productOverview = ProductOverviewEntity.newInstance(
            ID_3,
            TITLE_3,
            SIZE_3,
            PRICE_3,
            IMAGE_URL_3,
            TAG_3
        )

        productsOverviews.add(productOverview)

        productOverview = ProductOverviewEntity.newInstance(
            ID_4,
            TITLE_4,
            SIZE_4,
            PRICE_4,
            IMAGE_URL_4,
            TAG_4
        )

        productsOverviews.add(productOverview)

        productOverview = ProductOverviewEntity.newInstance(
            ID_5,
            TITLE_5,
            SIZE_5,
            PRICE_5,
            IMAGE_URL_5,
            TAG_5
        )

        productsOverviews.add(productOverview)

        productOverview = ProductOverviewEntity.newInstance(
            ID_6,
            TITLE_6,
            SIZE_6,
            PRICE_6,
            IMAGE_URL_6,
            TAG_6
        )

        productsOverviews.add(productOverview)

        productOverview = ProductOverviewEntity.newInstance(
            ID_7,
            TITLE_7,
            SIZE_7,
            PRICE_7,
            IMAGE_URL_7,
            TAG_7
        )

        productsOverviews.add(productOverview)

        productOverview = ProductOverviewEntity.newInstance(
            ID_8,
            TITLE_8,
            SIZE_8,
            PRICE_8,
            IMAGE_URL_8,
            TAG_8
        )

        productsOverviews.add(productOverview)

        productOverview = ProductOverviewEntity.newInstance(
            ID_9,
            TITLE_9,
            SIZE_9,
            PRICE_9,
            IMAGE_URL_9,
            TAG_9
        )

        productsOverviews.add(productOverview)

        productOverview = ProductOverviewEntity.newInstance(
            ID_10,
            TITLE_10,
            SIZE_10,
            PRICE_10,
            IMAGE_URL_10,
            TAG_10
        )

        productsOverviews.add(productOverview)

        return productsOverviews
    }

}