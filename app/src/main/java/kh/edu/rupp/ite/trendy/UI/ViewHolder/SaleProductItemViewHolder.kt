package kh.edu.rupp.ite.trendy.UI.ViewHolder

import android.annotation.SuppressLint
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kh.edu.rupp.ite.trendy.Model.Entry.ProductModel.ProductListModel
import kh.edu.rupp.ite.trendy.UI.Adapter.SaleProductItemAdapter
import kh.edu.rupp.ite.trendy.databinding.SaleViewHolderBinding

class SaleProductItemViewHolder(
    itemView: View,
    private val listener: SaleProductItemAdapter.OnClickListener,
) : RecyclerView.ViewHolder(itemView) {
    private val binding: SaleViewHolderBinding = SaleViewHolderBinding.bind(itemView)

    @SuppressLint("SetTextI18n")
    fun onBind(item: ProductListModel.ProductListModelItem, position: Int) {

        binding.homeProductName.text = item.productName
        binding.originalPrice.text = "$ ${item.productPrice}"
//        binding.discountPrice.text = discountedPrice.toString();
        Picasso.get().load(item.image!![0]?.imageUrl).into(binding.coverImage)

        if (item.productDiscount == 0) {
            binding.percentTag.visibility = View.GONE
        } else {
            binding.percentTag.visibility = View.VISIBLE
            binding.percentTag.text = "${item.productDiscount}%"
        }
    }
}