package kh.edu.rupp.ite.trendy.UI.ViewHolder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kh.edu.rupp.ite.trendy.Model.Entry.CategoryModel.SubCategoryModel
import kh.edu.rupp.ite.trendy.UI.Adapter.SubCategoryListHorizontalAdapter
import kh.edu.rupp.ite.trendy.databinding.ItemCategoryListHorizotalSubLayoutBinding

class ItemSubLayoutCategoryViewHolder(itemView:View, private val mListener:SubCategoryListHorizontalAdapter.OnSubCategoryClick):RecyclerView.ViewHolder(itemView) {
    private val binding : ItemCategoryListHorizotalSubLayoutBinding = ItemCategoryListHorizotalSubLayoutBinding.bind(itemView)
    fun onBind(item: SubCategoryModel.SubCategoryModelItem, position:Int){
        binding.title.text = item.name
        binding.root.setOnClickListener {
            mListener.onItemClick(item)
        }
    }
}