package emperorfin.android.ocadocart.ui.uimodels

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
    override val tag: String = UNDEFINED
) : ProductDetailsUiModelParams {

    companion object {

        const val UNDEFINED: String = "undefined"

    }

}
