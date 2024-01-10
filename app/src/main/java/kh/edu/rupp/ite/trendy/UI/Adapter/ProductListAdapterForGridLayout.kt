package kh.edu.rupp.ite.trendy.UI.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView.Adapter
import kh.edu.rupp.ite.trendy.Model.Entry.ProductModel.ListProductWithDetailByCategory
import kh.edu.rupp.ite.trendy.R
import kh.edu.rupp.ite.trendy.UI.Fragment.Detail.ProductListByCategory.Companion.SPAN_COUNT_ONE
import kh.edu.rupp.ite.trendy.UI.ViewHolder.GridProductListViewHolder

class ProductListAdapterForGridLayout(
    private val context: Context,
    private val mItem:ListProductWithDetailByCategory,
    private val layoutManager: GridLayoutManager,
    private val mListener:OnProductClick
):Adapter<GridProductListViewHolder>() {
    interface OnProductClick{
        fun onClickProduct(product: ListProductWithDetailByCategory.ListProductWithDetailByCategoryItem)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GridProductListViewHolder {
        val viewBinding : View = if (viewType == VIEW_TYPE_BIG){
            LayoutInflater.from(context).inflate(R.layout.item_product_single_list_layout, parent, false)
        }else{
            LayoutInflater.from(context).inflate(R.layout.product_item_viewholder_for_grid_layout, parent, false)

        }
        return GridProductListViewHolder(viewBinding, mListener,viewType)
    }


    override fun getItemCount(): Int {
        return mItem.size
    }


    override fun onBindViewHolder(holder: GridProductListViewHolder, position: Int) {
       holder.onBind(mItem[position]!!, position)
    }

    override fun getItemViewType(position: Int): Int {
        val spanCount : Int = layoutManager.spanCount
            return if (spanCount == SPAN_COUNT_ONE){
                VIEW_TYPE_BIG
            }else{
                VIEW_TYPE_SMALL
            }
    }


    companion object{
        const val VIEW_TYPE_BIG = 2
        const val VIEW_TYPE_SMALL = 1

    }
}