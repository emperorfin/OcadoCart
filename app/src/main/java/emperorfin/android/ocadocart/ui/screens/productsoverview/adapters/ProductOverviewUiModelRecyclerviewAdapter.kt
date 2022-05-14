package emperorfin.android.ocadocart.ui.screens.productsoverview.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import emperorfin.android.ocadocart.databinding.ListItemProductOverviewBinding
import emperorfin.android.ocadocart.ui.uimodels.ProductOverviewUiModel


/**
 * @Author: Francis Nwokelo (emperorfin)
 * @Date: Saturday 14th May, 2022.
 */


class ProductOverviewUiModelRecyclerviewAdapter(
    private val onClickListener: OnClickListener
) : ListAdapter<ProductOverviewUiModel,
        ProductOverviewUiModelRecyclerviewAdapter.ViewHolder>(ProductOverviewUiModelDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val productOverview = getItem(position)

        holder.bind(productOverview, onClickListener)
    }

    class ViewHolder private constructor(
        private val binding: ListItemProductOverviewBinding
    ) : RecyclerView.ViewHolder(binding.root){

        companion object{
            fun from(parent: ViewGroup): ViewHolder{
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding =
                    ListItemProductOverviewBinding.inflate(layoutInflater, parent, false)

                return ViewHolder(binding)
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

    companion object ProductOverviewUiModelDiffCallback : DiffUtil.ItemCallback<ProductOverviewUiModel>(){
        override fun areItemsTheSame(oldItem: ProductOverviewUiModel, newItem: ProductOverviewUiModel): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: ProductOverviewUiModel, newItem: ProductOverviewUiModel): Boolean {
            return oldItem.id == newItem.id
        }
    }

    class OnClickListener(private val clickListener: (productOverview: ProductOverviewUiModel) -> Unit){

        fun onProductOverviewClick(productOverview: ProductOverviewUiModel) = clickListener(productOverview)

    }

}