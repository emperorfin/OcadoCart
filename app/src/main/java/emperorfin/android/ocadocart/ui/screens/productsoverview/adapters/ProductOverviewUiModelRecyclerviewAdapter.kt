package emperorfin.android.ocadocart.ui.screens.productsoverview.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import emperorfin.android.ocadocart.databinding.HeaderItemProductsClusterBinding
import emperorfin.android.ocadocart.databinding.ListItemProductOverviewBinding
import emperorfin.android.ocadocart.ui.uimodels.ProductOverviewUiModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


/**
 * @Author: Francis Nwokelo (emperorfin)
 * @Date: Saturday 14th May, 2022.
 */


class ProductOverviewUiModelRecyclerviewAdapter(
    private val onClickListener: OnClickListener
) : ListAdapter<ProductOverviewUiModelRecyclerviewAdapter.DataItem,
        RecyclerView.ViewHolder>(ProductOverviewDiffCallback()) {

    private val adapterScope = CoroutineScope(Dispatchers.Default)

    companion object {
        private const val ITEM_VIEW_TYPE_HEADER = 0
        private const val ITEM_VIEW_TYPE_ITEM = 1
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            ITEM_VIEW_TYPE_HEADER -> HeaderViewHolder.from(parent)
            ITEM_VIEW_TYPE_ITEM -> ItemViewHolder.from(parent)
            else -> throw ClassCastException("Unknown viewType $viewType")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is HeaderViewHolder -> {
                val productsOverviewHeader = getItem(position) as DataItem.ProductOverviewHeader

                holder.bind(productsOverviewHeader)
            }
            is ItemViewHolder -> {
                val productOverviewItem = getItem(position) as DataItem.ProductOverviewItem

                holder.bind(productOverviewItem.productOverview, onClickListener)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)){
            is DataItem.ProductOverviewHeader -> ITEM_VIEW_TYPE_HEADER
            is DataItem.ProductOverviewItem -> ITEM_VIEW_TYPE_ITEM
        }
    }

    fun addHeaderAndSubmitList(productsOverviews: List<ProductOverviewUiModel>?){
        adapterScope.launch {
            val sortedProductsOverviewsOrNull: List<ProductOverviewUiModel>? = when (productsOverviews) {
                null -> productsOverviews
                else -> productsOverviews.sortedBy { it.tag }
            }

            val dataItems: List<DataItem> = when (sortedProductsOverviewsOrNull) {
                null -> listOf()
                else -> {
                    var tagTemp = ""

                    val dataItemsTemp: ArrayList<DataItem> = ArrayList()

                    for ((index, productOverview) in sortedProductsOverviewsOrNull.withIndex()) {
                        val headerId: Long = Long.MIN_VALUE + index

                        if (tagTemp.isEmpty()){
                            tagTemp = productOverview.tag

                            dataItemsTemp.add(DataItem.ProductOverviewHeader(headerId, tagTemp))
                            dataItemsTemp.add(DataItem.ProductOverviewItem(productOverview))
                        } else {
                            if (tagTemp == productOverview.tag){
                                dataItemsTemp.add(DataItem.ProductOverviewItem(productOverview))
                            } else {
                                tagTemp = productOverview.tag

                                dataItemsTemp.add(DataItem.ProductOverviewHeader(headerId, tagTemp))
                                dataItemsTemp.add(DataItem.ProductOverviewItem(productOverview))
                            }
                        }
                    }

                    dataItemsTemp
                }
            }

            withContext(Dispatchers.Main) {
                submitList(dataItems)
            }
        }
    }

    private class HeaderViewHolder private constructor(
        private val binding: HeaderItemProductsClusterBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        companion object {
            fun from(parent: ViewGroup): HeaderViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding =
                    HeaderItemProductsClusterBinding.inflate(layoutInflater, parent, false)

                return HeaderViewHolder(binding)
            }
        }

        fun bind(productsOverviewHeader: DataItem.ProductOverviewHeader){
            binding.productsHeader = productsOverviewHeader
            binding.executePendingBindings()
        }

    }

    private class ItemViewHolder private constructor(
        private val binding: ListItemProductOverviewBinding
    ) : RecyclerView.ViewHolder(binding.root){

        companion object{
            fun from(parent: ViewGroup): ItemViewHolder{
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding =
                    ListItemProductOverviewBinding.inflate(layoutInflater, parent, false)

                return ItemViewHolder(binding)
            }
        }

        fun bind(productOverviewUiModel: ProductOverviewUiModel, clickListener: OnClickListener){
            binding.productOverviewUiModel = productOverviewUiModel
            binding.clickListener = clickListener
            // This is important, because it forces the data binding to execute immediately,
            // which allows the RecyclerView to make the correct view size measurements
            binding.executePendingBindings()
        }
    }

    private class ProductOverviewDiffCallback : DiffUtil.ItemCallback<DataItem>(){
        override fun areItemsTheSame(oldItem: DataItem, newItem: DataItem): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: DataItem, newItem: DataItem): Boolean {
            return oldItem.id == newItem.id
        }
    }

    class OnClickListener(private val clickListener: (productOverview: ProductOverviewUiModel) -> Unit){

        fun onProductOverviewClick(productOverview: ProductOverviewUiModel) = clickListener(productOverview)

    }

    sealed class DataItem {
        abstract val id: Long

        data class ProductOverviewHeader(
            override val id: Long,
            val productsCluster: String
            ) : DataItem()

        data class ProductOverviewItem(val productOverview: ProductOverviewUiModel) : DataItem() {
            override val id: Long = productOverview.id.toLong()
        }
    }

}