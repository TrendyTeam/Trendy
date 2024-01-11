package kh.edu.rupp.ite.trendy.UI.ViewHolder

import android.graphics.Color
import android.view.View
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import kh.edu.rupp.ite.trendy.Model.Entry.ProductModel.OneProductModel
import kh.edu.rupp.ite.trendy.UI.Adapter.ListColorItemAdapter
import kh.edu.rupp.ite.trendy.databinding.ListitemColorLayoutBinding

class ItemColorDialogViewHolder(
    itemView: View,
    private val mListener: ListColorItemAdapter.OnClickSIze
) : ViewHolder(itemView) {
    private val binding: ListitemColorLayoutBinding = ListitemColorLayoutBinding.bind(itemView)
    fun onBin(item: OneProductModel.Item) {
        try {
            val color = Color.parseColor(item.colorCode)
            binding.color.setBackgroundColor(color)
            binding.root.setOnClickListener {
                mListener.sizeClick(item)
            }
        } catch (_: Exception) {
        }
    }
}