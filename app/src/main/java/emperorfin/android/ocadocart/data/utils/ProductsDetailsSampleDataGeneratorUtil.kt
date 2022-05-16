package emperorfin.android.ocadocart.data.utils

import emperorfin.android.ocadocart.data.datasources.local.frameworks.room.entities.ProductDetailsEntity
import emperorfin.android.ocadocart.data.constants.ProductsDetailsSampleDataConstants.ID_1
import emperorfin.android.ocadocart.data.constants.ProductsDetailsSampleDataConstants.ID_2
import emperorfin.android.ocadocart.data.constants.ProductsDetailsSampleDataConstants.ID_3
import emperorfin.android.ocadocart.data.constants.ProductsDetailsSampleDataConstants.ID_4
import emperorfin.android.ocadocart.data.constants.ProductsDetailsSampleDataConstants.ID_5
import emperorfin.android.ocadocart.data.constants.ProductsDetailsSampleDataConstants.ID_6
import emperorfin.android.ocadocart.data.constants.ProductsDetailsSampleDataConstants.ID_7
import emperorfin.android.ocadocart.data.constants.ProductsDetailsSampleDataConstants.ID_8
import emperorfin.android.ocadocart.data.constants.ProductsDetailsSampleDataConstants.ID_9
import emperorfin.android.ocadocart.data.constants.ProductsDetailsSampleDataConstants.ID_10

import emperorfin.android.ocadocart.data.constants.ProductsDetailsSampleDataConstants.TITLE_1
import emperorfin.android.ocadocart.data.constants.ProductsDetailsSampleDataConstants.TITLE_2
import emperorfin.android.ocadocart.data.constants.ProductsDetailsSampleDataConstants.TITLE_3
import emperorfin.android.ocadocart.data.constants.ProductsDetailsSampleDataConstants.TITLE_4
import emperorfin.android.ocadocart.data.constants.ProductsDetailsSampleDataConstants.TITLE_5
import emperorfin.android.ocadocart.data.constants.ProductsDetailsSampleDataConstants.TITLE_6
import emperorfin.android.ocadocart.data.constants.ProductsDetailsSampleDataConstants.TITLE_7
import emperorfin.android.ocadocart.data.constants.ProductsDetailsSampleDataConstants.TITLE_8
import emperorfin.android.ocadocart.data.constants.ProductsDetailsSampleDataConstants.TITLE_9
import emperorfin.android.ocadocart.data.constants.ProductsDetailsSampleDataConstants.TITLE_10

import emperorfin.android.ocadocart.data.constants.ProductsDetailsSampleDataConstants.PRICE_1
import emperorfin.android.ocadocart.data.constants.ProductsDetailsSampleDataConstants.PRICE_2
import emperorfin.android.ocadocart.data.constants.ProductsDetailsSampleDataConstants.PRICE_3
import emperorfin.android.ocadocart.data.constants.ProductsDetailsSampleDataConstants.PRICE_4
import emperorfin.android.ocadocart.data.constants.ProductsDetailsSampleDataConstants.PRICE_5
import emperorfin.android.ocadocart.data.constants.ProductsDetailsSampleDataConstants.PRICE_6
import emperorfin.android.ocadocart.data.constants.ProductsDetailsSampleDataConstants.PRICE_7
import emperorfin.android.ocadocart.data.constants.ProductsDetailsSampleDataConstants.PRICE_8
import emperorfin.android.ocadocart.data.constants.ProductsDetailsSampleDataConstants.PRICE_9
import emperorfin.android.ocadocart.data.constants.ProductsDetailsSampleDataConstants.PRICE_10

import emperorfin.android.ocadocart.data.constants.ProductsDetailsSampleDataConstants.IMAGE_URL_1
import emperorfin.android.ocadocart.data.constants.ProductsDetailsSampleDataConstants.IMAGE_URL_2
import emperorfin.android.ocadocart.data.constants.ProductsDetailsSampleDataConstants.IMAGE_URL_3
import emperorfin.android.ocadocart.data.constants.ProductsDetailsSampleDataConstants.IMAGE_URL_4
import emperorfin.android.ocadocart.data.constants.ProductsDetailsSampleDataConstants.IMAGE_URL_5
import emperorfin.android.ocadocart.data.constants.ProductsDetailsSampleDataConstants.IMAGE_URL_6
import emperorfin.android.ocadocart.data.constants.ProductsDetailsSampleDataConstants.IMAGE_URL_7
import emperorfin.android.ocadocart.data.constants.ProductsDetailsSampleDataConstants.IMAGE_URL_8
import emperorfin.android.ocadocart.data.constants.ProductsDetailsSampleDataConstants.IMAGE_URL_9
import emperorfin.android.ocadocart.data.constants.ProductsDetailsSampleDataConstants.IMAGE_URL_10

import emperorfin.android.ocadocart.data.constants.ProductsDetailsSampleDataConstants.DESCRIPTION_1
import emperorfin.android.ocadocart.data.constants.ProductsDetailsSampleDataConstants.DESCRIPTION_2
import emperorfin.android.ocadocart.data.constants.ProductsDetailsSampleDataConstants.DESCRIPTION_3
import emperorfin.android.ocadocart.data.constants.ProductsDetailsSampleDataConstants.DESCRIPTION_4
import emperorfin.android.ocadocart.data.constants.ProductsDetailsSampleDataConstants.DESCRIPTION_5
import emperorfin.android.ocadocart.data.constants.ProductsDetailsSampleDataConstants.DESCRIPTION_6
import emperorfin.android.ocadocart.data.constants.ProductsDetailsSampleDataConstants.DESCRIPTION_7
import emperorfin.android.ocadocart.data.constants.ProductsDetailsSampleDataConstants.DESCRIPTION_8
import emperorfin.android.ocadocart.data.constants.ProductsDetailsSampleDataConstants.DESCRIPTION_9
import emperorfin.android.ocadocart.data.constants.ProductsDetailsSampleDataConstants.DESCRIPTION_10

import emperorfin.android.ocadocart.data.constants.ProductsDetailsSampleDataConstants.ALLERGY_INFORMATION_1
import emperorfin.android.ocadocart.data.constants.ProductsDetailsSampleDataConstants.ALLERGY_INFORMATION_2
import emperorfin.android.ocadocart.data.constants.ProductsDetailsSampleDataConstants.ALLERGY_INFORMATION_3
import emperorfin.android.ocadocart.data.constants.ProductsDetailsSampleDataConstants.ALLERGY_INFORMATION_4
import emperorfin.android.ocadocart.data.constants.ProductsDetailsSampleDataConstants.ALLERGY_INFORMATION_5
import emperorfin.android.ocadocart.data.constants.ProductsDetailsSampleDataConstants.ALLERGY_INFORMATION_6
import emperorfin.android.ocadocart.data.constants.ProductsDetailsSampleDataConstants.ALLERGY_INFORMATION_7
import emperorfin.android.ocadocart.data.constants.ProductsDetailsSampleDataConstants.ALLERGY_INFORMATION_8
import emperorfin.android.ocadocart.data.constants.ProductsDetailsSampleDataConstants.ALLERGY_INFORMATION_9
import emperorfin.android.ocadocart.data.constants.ProductsDetailsSampleDataConstants.ALLERGY_INFORMATION_10


/**
 * @Author: Francis Nwokelo (emperorfin)
 * @Date: Sunday 15th May, 2022.
 */


object ProductsDetailsSampleDataGeneratorUtil {

    fun getProductDetailsEntityList(): ArrayList<ProductDetailsEntity> {
        val productDetailsList: ArrayList<ProductDetailsEntity> = ArrayList()

        var productDetails = ProductDetailsEntity.newInstance(
            ID_1,
            TITLE_1,
            PRICE_1,
            IMAGE_URL_1,
            DESCRIPTION_1,
            ALLERGY_INFORMATION_1
        )

        productDetailsList.add(productDetails)

        productDetails = ProductDetailsEntity.newInstance(
            ID_2,
            TITLE_2,
            PRICE_2,
            IMAGE_URL_2,
            DESCRIPTION_2,
            ALLERGY_INFORMATION_2
        )

        productDetailsList.add(productDetails)

        productDetails = ProductDetailsEntity.newInstance(
            ID_3,
            TITLE_3,
            PRICE_3,
            IMAGE_URL_3,
            DESCRIPTION_3,
            ALLERGY_INFORMATION_3
        )

        productDetailsList.add(productDetails)

        productDetails = ProductDetailsEntity.newInstance(
            ID_4,
            TITLE_4,
            PRICE_4,
            IMAGE_URL_4,
            DESCRIPTION_4,
            ALLERGY_INFORMATION_4
        )

        productDetailsList.add(productDetails)

        productDetails = ProductDetailsEntity.newInstance(
            ID_5,
            TITLE_5,
            PRICE_5,
            IMAGE_URL_5,
            DESCRIPTION_5,
            ALLERGY_INFORMATION_5
        )

        productDetailsList.add(productDetails)

        productDetails = ProductDetailsEntity.newInstance(
            ID_6,
            TITLE_6,
            PRICE_6,
            IMAGE_URL_6,
            DESCRIPTION_6,
            ALLERGY_INFORMATION_6
        )

        productDetailsList.add(productDetails)

        productDetails = ProductDetailsEntity.newInstance(
            ID_7,
            TITLE_7,
            PRICE_7,
            IMAGE_URL_7,
            DESCRIPTION_7,
            ALLERGY_INFORMATION_7
        )

        productDetailsList.add(productDetails)

        productDetails = ProductDetailsEntity.newInstance(
            ID_8,
            TITLE_8,
            PRICE_8,
            IMAGE_URL_8,
            DESCRIPTION_8,
            ALLERGY_INFORMATION_8
        )

        productDetailsList.add(productDetails)

        productDetails = ProductDetailsEntity.newInstance(
            ID_9,
            TITLE_9,
            PRICE_9,
            IMAGE_URL_9,
            DESCRIPTION_9,
            ALLERGY_INFORMATION_9
        )

        productDetailsList.add(productDetails)

        productDetails = ProductDetailsEntity.newInstance(
            ID_10,
            TITLE_10,
            PRICE_10,
            IMAGE_URL_10,
            DESCRIPTION_10,
            ALLERGY_INFORMATION_10
        )

        productDetailsList.add(productDetails)

        return productDetailsList
    }

    // You may have other functions like:
    // Requires conversion from ProductDetailsDataTransferObject to the destination data model class
    // instance. E.g. if data from ProductDetailsDataTransferObject are to be used on the UI, then
    // ProductDetailsDataTransferObject objects are converted to ProductDetailsUiModel objects by
    // using the mapper class for ProductDetailsModel to convert from ProductDetailsDataTransferObject
    // objects to ProductDetailsModel objects and then mapper class for ProductDetailsUiModel to
    // convert from ProductDetailsModel objects to ProductDetailsUiModel objects.
    // getProductDetailsDataTransferObjectList()

    // If data from ProductDetailsModel objects are to be used on the UI, then only mapper class for
    // ProductDetailsUiModel is needed to convert from ProductDetailsModel objects to
    // ProductDetailsUiModel objects.
    // getProductDetailsModelList()

    // If data from ProductDetailsUiModel objects are to be used on the UI, no conversion is needed.
    // getProductDetailsUiModelList()

}