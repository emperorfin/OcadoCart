package emperorfin.android.ocadocart.ui.bindingadapters

import android.view.View
import android.widget.TextView
import androidx.databinding.BindingAdapter
import emperorfin.android.ocadocart.R
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
                textView.text = textView.context.getString(R.string.products_overviews_results_error)
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