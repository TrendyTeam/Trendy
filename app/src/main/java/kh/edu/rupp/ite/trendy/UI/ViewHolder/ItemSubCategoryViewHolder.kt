package kh.edu.rupp.ite.trendy.UI.ViewHolder

import android.util.Log
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kh.edu.rupp.ite.trendy.Model.Entry.CategoryModel.SubCategoryModel
import kh.edu.rupp.ite.trendy.UI.Adapter.ListSubCategoryAdapter
import kh.edu.rupp.ite.trendy.databinding.ItemSubcategoryViewHolderBinding

class ItemSubCategoryViewHolder(itemView: View, private val mListener:ListSubCategoryAdapter.OnClickListenerItem):RecyclerView.ViewHolder(itemView) {
    private val binding: ItemSubcategoryViewHolderBinding = ItemSubcategoryViewHolderBinding.bind(itemView)
    fun onBind(item: SubCategoryModel.SubCategoryModelItem, position:Int){
        binding.categoryName.text = item.name
        if (!item.imageUrl.isNullOrEmpty()){
            Log.d("IMAGE_URL", "item = ${item.imageUrl}")
            Picasso.get().load(item.imageUrl).into(binding.imageSubCategory)
        }

        binding.root.setOnClickListener {
            mListener.onItemClick(item)
        }
    }
}