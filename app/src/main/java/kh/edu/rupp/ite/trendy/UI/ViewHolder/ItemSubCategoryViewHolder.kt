package kh.edu.rupp.ite.trendy.UI.ViewHolder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kh.edu.rupp.ite.trendy.Model.Entry.CategoryModel.SubCategoryModel
import kh.edu.rupp.ite.trendy.UI.Adapter.ListSubCategoryAdapter
import kh.edu.rupp.ite.trendy.databinding.ItemSubcategoryViewHolderBinding

class ItemSubCategoryViewHolder(itemView: View, private val mListener:ListSubCategoryAdapter.OnClickListenerItem):RecyclerView.ViewHolder(itemView) {
    private val binding: ItemSubcategoryViewHolderBinding = ItemSubcategoryViewHolderBinding.bind(itemView)
    fun onBind(item: SubCategoryModel.SubCategoryModelItem, position:Int){
        binding.categoryName.text = item.name



        binding.root.setOnClickListener {
            mListener.onItemClick(item)
        }
    }
}