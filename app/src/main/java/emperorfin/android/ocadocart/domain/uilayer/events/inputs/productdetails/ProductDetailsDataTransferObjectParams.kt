package emperorfin.android.ocadocart.domain.uilayer.events.inputs.productdetails


/**
 * @Author: Francis Nwokelo (emperorfin)
 * @Date: Saturday 14th May, 2022.
 */


interface ProductDetailsDataTransferObjectParams : Params {

    val id: Int?
    val title: String?
    val price: String?
    val imageUrl: String?
    val description: String?
    val allergyInformation: String?

}