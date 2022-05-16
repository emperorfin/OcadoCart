package emperorfin.android.ocadocart.ui.bindingadapters

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.request.RequestOptions
import emperorfin.android.ocadocart.R
import emperorfin.android.ocadocart.ui.screens.productsoverview.enums.ProductDetailsRequestStatus
import emperorfin.android.ocadocart.ui.screens.productsoverview.enums.ProductsOverviewRequestStatus


/**
 * @Author: Francis Nwokelo (emperorfin)
 * @Date: Saturday 14th May, 2022.
 */



@BindingAdapter(value = ["bindProductImageUrl", "bindImageWidth", "bindImageHeight"], requireAll = false)
fun setProductImageUrl(imageView: ImageView, imageUrl: String?, iconWidth: Int?, iconHeight: Int?){
    imageUrl?.let {
//        val imgUri = imageUrl.toUri().buildUpon().scheme("https").build()

        val glideUrl = GlideUrl(it){
            mapOf(Pair("Authorization", "Bearer YOUR_API_KEY")) // ${BuildConfig.API_KEY}
        }

        Glide.with(imageView.context)
            .load(if(it.contains("http")) it else R.mipmap.ic_launcher)
            // This is not necessary since the Ocado mocked webservice doesn't require authorization
            // to display images.
            //.load(glideUrl)
            .apply(
                RequestOptions()
                    .placeholder(R.drawable.loading_animation)
                    .error(R.drawable.ic_broken_image)
                    .apply {
                        if(iconWidth != null && iconHeight != null)
                            override(iconWidth, iconHeight)
                        else if(iconWidth == null && iconHeight == null)
                        //Do not set override(..., ...)
                        else if(iconWidth == null || iconHeight == null)
                            throw IllegalArgumentException(
                                imageView
                                    .context
                                    .getString(R.string.error_either_width_or_height_must_not_be_null)
                            )
                    })
            .into(imageView)
    }
}

@BindingAdapter("bindProductsOverviewsRequestStatusImage")
fun setProductsOverviewsRequestStatusImage(imageView: ImageView, requestStatus: ProductsOverviewRequestStatus?){
    requestStatus?.let {
        when(it){
            ProductsOverviewRequestStatus.LOADING -> {
                imageView.visibility = View.VISIBLE
                imageView.setImageResource(R.drawable.loading_animation)
            }
            ProductsOverviewRequestStatus.ERROR -> {
                imageView.visibility = View.VISIBLE
                imageView.setImageResource(R.drawable.ic_connection_error_no_data_black)
            }
            ProductsOverviewRequestStatus.NO_DATA -> {
                imageView.visibility = View.VISIBLE
                imageView.setImageResource(R.drawable.ic_search_glyph)
            }
            ProductsOverviewRequestStatus.DONE -> {
                imageView.visibility = View.GONE
            }
        }
    }
}

@BindingAdapter("bindProductDetailsRequestStatusImage")
fun setProductDetailsRequestStatusImage(imageView: ImageView, requestStatus: ProductDetailsRequestStatus?){
    requestStatus?.let {
        when(it){
            ProductDetailsRequestStatus.LOADING -> {
                imageView.visibility = View.VISIBLE
                imageView.setImageResource(R.drawable.loading_animation)
            }
            ProductDetailsRequestStatus.ERROR -> {
                imageView.visibility = View.VISIBLE
                imageView.setImageResource(R.drawable.ic_connection_error_no_data_black)
            }
            ProductDetailsRequestStatus.NO_DATA -> {
                imageView.visibility = View.VISIBLE
                imageView.setImageResource(R.drawable.ic_search_glyph)
            }
            ProductDetailsRequestStatus.DONE -> {
                imageView.visibility = View.GONE
            }
        }
    }
}
