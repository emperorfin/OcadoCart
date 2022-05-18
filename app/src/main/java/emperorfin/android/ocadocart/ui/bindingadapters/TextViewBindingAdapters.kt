package emperorfin.android.ocadocart.ui.bindingadapters

import android.view.View
import android.widget.TextView
import androidx.databinding.BindingAdapter
import emperorfin.android.ocadocart.R
import emperorfin.android.ocadocart.ui.screens.productsoverview.enums.ProductDetailsRequestStatus
import emperorfin.android.ocadocart.ui.screens.productsoverview.enums.ProductsOverviewRequestStatus


/**
 * @Author: Francis Nwokelo (emperorfin)
 * @Date: Saturday 14th May, 2022.
 */



@BindingAdapter("bindProductsOverviewsRequestStatusText")
fun setProductsOverviewsRequestStatusText(textView: TextView, requestStatus: ProductsOverviewRequestStatus?){
    requestStatus?.let {
        when(it){
            ProductsOverviewRequestStatus.LOADING -> {
                textView.visibility = View.VISIBLE
                textView.text = textView.context.getString(R.string.loading)
            }
            ProductsOverviewRequestStatus.ERROR -> {
                textView.visibility = View.VISIBLE
                textView.text = textView.context.getString(R.string.error_products_overviews_results)
            }
            ProductsOverviewRequestStatus.NO_DATA -> {
                textView.visibility = View.VISIBLE
                textView.text = textView.context.getString(R.string.products_overviews_results_no_data)
            }
            ProductsOverviewRequestStatus.DONE -> {
                textView.visibility = View.GONE
            }
        }
    }
}

@BindingAdapter("bindProductDetailsRequestStatusText")
fun setProductDetailsRequestStatusText(textView: TextView, requestStatus: ProductDetailsRequestStatus?){
    requestStatus?.let {
        when(it){
            ProductDetailsRequestStatus.LOADING -> {
                textView.visibility = View.VISIBLE
                textView.text = textView.context.getString(R.string.loading)
            }
            ProductDetailsRequestStatus.ERROR -> {
                textView.visibility = View.VISIBLE
                textView.text = textView.context.getString(R.string.error_product_details)
            }
            ProductDetailsRequestStatus.NO_DATA -> {
                textView.visibility = View.VISIBLE
                textView.text = textView.context.getString(R.string.products_overviews_results_no_data)
            }
            ProductDetailsRequestStatus.DONE -> {
                textView.visibility = View.GONE
            }
        }
    }
}