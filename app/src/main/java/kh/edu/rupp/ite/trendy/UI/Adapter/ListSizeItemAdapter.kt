package kh.edu.rupp.ite.trendy.UI.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import kh.edu.rupp.ite.trendy.Model.Entry.ProductModel.OneProductModel
import kh.edu.rupp.ite.trendy.R
import kh.edu.rupp.ite.trendy.UI.ViewHolder.ItemSizeDialogViewHolder

class ListSizeItemAdapter(
    private val context: Context,
    private val mItem: ArrayList<OneProductModel.Item>,
    private val onCLick: OnClickSIze
):Adapter<ItemSizeDialogViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemSizeDialogViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.listitem_size_layout, parent, false)
        return ItemSizeDialogViewHolder(view, onCLick)
    }


    override fun getItemCount(): Int {
        return mItem.size
    }


    override fun onBindViewHolder(holder: ItemSizeDialogViewHolder, position: Int) {
        holder.onBin(mItem[position])
    }
    interface OnClickSIze{
        fun sizeClick(item: OneProductModel.Item)
    }
}