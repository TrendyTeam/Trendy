package kh.edu.rupp.ite.trendy.UI.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import kh.edu.rupp.ite.trendy.Model.Entry.ProductModel.OneProductModel
import kh.edu.rupp.ite.trendy.R
import kh.edu.rupp.ite.trendy.UI.ViewHolder.ItemColorDialogViewHolder

class ListColorItemAdapter(
    private val context: Context,
    private val mItem: ArrayList<OneProductModel.Item>,
    private val onCLick: OnClickSIze
):Adapter<ItemColorDialogViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemColorDialogViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.listitem_color_layout, parent, false)
        return ItemColorDialogViewHolder(view, onCLick)
    }
    override fun getItemCount(): Int {
        return mItem.size
    }
    override fun onBindViewHolder(holder: ItemColorDialogViewHolder, position: Int) {
        holder.onBin(mItem[position])
    }
    interface OnClickSIze{
        fun sizeClick(item: OneProductModel.Item)
    }
}