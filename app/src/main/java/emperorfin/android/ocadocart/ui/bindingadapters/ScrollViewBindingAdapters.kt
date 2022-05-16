package emperorfin.android.ocadocart.ui.bindingadapters

import android.view.View
import android.widget.ScrollView
import androidx.databinding.BindingAdapter
import emperorfin.android.ocadocart.ui.screens.productsoverview.enums.ProductDetailsRequestStatus


/**
 * @Author: Francis Nwokelo (emperorfin)
 * @Date: Sunday 15th May, 2022.
 */



@BindingAdapter("bindProductDetailsRequestStatusScrollView")
fun setProductDetailsRequestStatusScrollView(scrollView: ScrollView, requestStatus: ProductDetailsRequestStatus?){
    if(requestStatus == null){
        scrollView.visibility = View.GONE
    } else{
        when(requestStatus){
            ProductDetailsRequestStatus.LOADING -> {
                scrollView.visibility = View.GONE
            }
            ProductDetailsRequestStatus.ERROR -> {
                scrollView.visibility = View.GONE
            }
            ProductDetailsRequestStatus.NO_DATA -> {
                scrollView.visibility = View.GONE
            }
            ProductDetailsRequestStatus.DONE -> {
                scrollView.visibility = View.VISIBLE
            }
        }
    }
}