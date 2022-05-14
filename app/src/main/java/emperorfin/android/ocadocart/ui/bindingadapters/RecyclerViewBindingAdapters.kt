package emperorfin.android.ocadocart.ui.bindingadapters

import android.view.View
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import emperorfin.android.ocadocart.ui.screens.productsoverview.adapters.ProductOverviewUiModelRecyclerviewAdapter
import emperorfin.android.ocadocart.ui.uimodels.ProductOverviewUiModel


/**
 * @Author: Francis Nwokelo (emperorfin)
 * @Date: Saturday 14th May, 2022.
 */



/**
 * When there is no product overview item data (i.e. data is null), hide the [RecyclerView], otherwise show it.
 */
@BindingAdapter("bindProductsOverviews")
fun setProductsOverviews(recyclerView: RecyclerView, productsOverviews: List<ProductOverviewUiModel>?){
    if(productsOverviews == null || productsOverviews.isEmpty())
        recyclerView.visibility = View.GONE
    else
        recyclerView.visibility = View.VISIBLE

    val productsOverviewsAdapter = recyclerView.adapter as ProductOverviewUiModelRecyclerviewAdapter
    productsOverviewsAdapter.submitList(productsOverviews)
}