package kh.edu.rupp.ite.trendy.UI.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kh.edu.rupp.ite.trendy.Model.Entry.CategoryModel.SubCategoryModel
import kh.edu.rupp.ite.trendy.R
import kh.edu.rupp.ite.trendy.UI.ViewHolder.ItemSubCategoryViewHolder

class ListSubCategoryAdapter(
    private val context: Context,
    private val mItem: SubCategoryModel,
    private val onClickResponse:OnClickListenerItem
):RecyclerView.Adapter<ItemSubCategoryViewHolder>() {

    interface OnClickListenerItem{
        fun onItemClick (data: SubCategoryModel.SubCategoryModelItem)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemSubCategoryViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_subcategory_view_holder, parent, false)
        return ItemSubCategoryViewHolder(view, onClickResponse)
    }

    override fun onBindViewHolder(holder: ItemSubCategoryViewHolder, position: Int) {
        holder.onBind(mItem[position],position)
    }

    override fun getItemCount(): Int {
        return mItem.size
    }
}