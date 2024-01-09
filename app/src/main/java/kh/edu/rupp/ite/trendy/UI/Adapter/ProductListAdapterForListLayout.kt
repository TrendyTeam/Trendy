package kh.edu.rupp.ite.trendy.UI.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import kh.edu.rupp.ite.trendy.Model.Entry.ProductModel.ListProductWithDetailByCategory
import kh.edu.rupp.ite.trendy.R
import kh.edu.rupp.ite.trendy.UI.ViewHolder.ListProductViewHolderForListLayout

class ProductListAdapterForListLayout(
    private val context: Context,
    private val mItem:ListProductWithDetailByCategory,
    private val mListener:OnProductClick
):Adapter<ListProductViewHolderForListLayout>() {
    interface OnProductClick{
        fun onClickProduct(product: ListProductWithDetailByCategory.ListProductWithDetailByCategoryItem)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListProductViewHolderForListLayout {
        val view = LayoutInflater.from(context).inflate(R.layout.item_product_single_list_layout, parent, false)
        return ListProductViewHolderForListLayout(view, mListener)
    }


    override fun getItemCount(): Int {
        return mItem.size
    }


    override fun onBindViewHolder(holder: ListProductViewHolderForListLayout, position: Int) {
       holder.onBind(mItem[position]!!, position)
    }
}