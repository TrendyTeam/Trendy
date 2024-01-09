package kh.edu.rupp.ite.trendy.UI.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import kh.edu.rupp.ite.trendy.Model.Entry.ProductModel.ListProductWithDetailByCategory
import kh.edu.rupp.ite.trendy.R
import kh.edu.rupp.ite.trendy.UI.ViewHolder.GridProductListViewHolder

class ProductListAdapterForGridLayout(
    private val context: Context,
    private val mItem:ListProductWithDetailByCategory,
    private val mListener:OnProductClick
):Adapter<GridProductListViewHolder>() {
    interface OnProductClick{
        fun onClickProduct(product: ListProductWithDetailByCategory.ListProductWithDetailByCategoryItem)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GridProductListViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.product_item_viewholder_for_grid_layout, parent, false)
        return GridProductListViewHolder(view, mListener)
    }


    override fun getItemCount(): Int {
        return mItem.size
    }


    override fun onBindViewHolder(holder: GridProductListViewHolder, position: Int) {
       holder.onBind(mItem[position]!!, position)
    }
}