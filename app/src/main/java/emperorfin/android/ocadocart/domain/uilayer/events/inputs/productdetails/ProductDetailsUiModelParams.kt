package emperorfin.android.ocadocart.domain.uilayer.events.inputs.productdetails


/**
 * @Author: Francis Nwokelo (emperorfin)
 * @Date: Saturday 14th May, 2022.
 */


interface ProductDetailsUiModelParams : ProductDetailsModelParams {

    val size: String?
    val tag: String?
    val priceWithUnit: String?

}