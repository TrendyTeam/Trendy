package kh.edu.rupp.ite.trendy.UI.ViewHolder

import android.view.View
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import kh.edu.rupp.ite.trendy.Model.Entry.ProductModel.OneProductModel
import kh.edu.rupp.ite.trendy.UI.Adapter.ListSizeItemAdapter
import kh.edu.rupp.ite.trendy.databinding.ListitemSizeLayoutBinding

class ItemSizeDialogViewHolder(
    itemView: View,
    private val mListener: ListSizeItemAdapter.OnClickSIze
) : ViewHolder(itemView) {
    private val binding: ListitemSizeLayoutBinding = ListitemSizeLayoutBinding.bind(itemView)
    fun onBin(item: OneProductModel.Item) {
        try {
            binding.sizeTxt.text = item.size?.uppercase()
            binding.root.setOnClickListener {
                mListener.sizeClick(item)
            }
        } catch (_: Exception) {
        }
    }
}