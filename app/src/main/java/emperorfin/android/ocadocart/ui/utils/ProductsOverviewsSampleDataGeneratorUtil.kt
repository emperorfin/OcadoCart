package emperorfin.android.ocadocart.ui.utils

import emperorfin.android.ocadocart.data.datasources.local.frameworks.room.entities.ProductOverviewEntity
import emperorfin.android.ocadocart.data.utils.ProductsOverviewsSampleDataGeneratorUtil as ProductsOverviewsSampleDataGeneratorUtil_FromDataLayer
import emperorfin.android.ocadocart.domain.models.mappers.ProductOverviewModelMapper
import emperorfin.android.ocadocart.ui.uimodels.ProductOverviewUiModel
import emperorfin.android.ocadocart.ui.uimodels.mappers.ProductOverviewUiModelMapper


/**
 * @Author: Francis Nwokelo (emperorfin)
 * @Date: Friday 13th May, 2022.
 */


object ProductsOverviewsSampleDataGeneratorUtil {

    fun getTransformedEntityProductsOverviews(): ArrayList<ProductOverviewUiModel> {
        val productOverviewModelMapper = ProductOverviewModelMapper()
        val productOverviewUiModelMapper = ProductOverviewUiModelMapper()

        val productsOverviews: List<ProductOverviewEntity> =
            ProductsOverviewsSampleDataGeneratorUtil_FromDataLayer.getProductOverviewEntityList()

        return productsOverviews.map {
            productOverviewModelMapper.transform(it)
        }.map {
            productOverviewUiModelMapper.transform(it)
        } as ArrayList<ProductOverviewUiModel>
    }

}