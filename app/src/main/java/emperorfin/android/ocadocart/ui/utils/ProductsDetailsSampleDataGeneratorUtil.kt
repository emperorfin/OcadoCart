package emperorfin.android.ocadocart.ui.utils

import emperorfin.android.ocadocart.data.datasources.local.frameworks.room.entities.ProductDetailsEntity
import emperorfin.android.ocadocart.ui.uimodels.ProductDetailsUiModel
import emperorfin.android.ocadocart.domain.models.mappers.ProductDetailsModelMapper
import emperorfin.android.ocadocart.data.utils.ProductsDetailsSampleDataGeneratorUtil as ProductsDetailsSampleDataGeneratorUtil_FromDataLayer
import emperorfin.android.ocadocart.ui.uimodels.mappers.ProductDetailsUiModelMapper


/**
 * @Author: Francis Nwokelo (emperorfin)
 * @Date: Sunday 15th May, 2022.
 */


object ProductsDetailsSampleDataGeneratorUtil {

    fun getTransformedEntityProductsDetails(): ArrayList<ProductDetailsUiModel> {
        val productDetailsModelMapper = ProductDetailsModelMapper()
        val productDetailsUiModelMapper = ProductDetailsUiModelMapper()

        val productsDetails: List<ProductDetailsEntity> =
            ProductsDetailsSampleDataGeneratorUtil_FromDataLayer.getProductDetailsEntityList()

        return productsDetails.map {
            productDetailsModelMapper.transform(it)
        }.map {
            productDetailsUiModelMapper.transform(it)
        } as ArrayList<ProductDetailsUiModel>
    }

}