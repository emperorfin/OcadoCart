package emperorfin.android.ocadocart.domain.uilayer.events.inputs.productoverview


/**
 * @Author: Francis Nwokelo (emperorfin)
 * @Date: Friday 13th May, 2022.
 */


interface ProductOverviewDataTransferObjectParams : Params {
    val id: Int?
    val title: String?
    val size: String?
    val price: String?
    val imageUrl: String?
}