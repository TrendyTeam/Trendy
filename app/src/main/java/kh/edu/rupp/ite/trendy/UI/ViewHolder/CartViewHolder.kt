package kh.edu.rupp.ite.trendy.UI.ViewHolder

import android.annotation.SuppressLint
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kh.edu.rupp.ite.trendy.Model.Entry.CartModel.CartModel
import kh.edu.rupp.ite.trendy.databinding.BagItemViewHolderBinding

class CartViewHolder (
    itemView : View,
) : RecyclerView.ViewHolder(itemView) {

    private  val binding: BagItemViewHolderBinding = BagItemViewHolderBinding.bind(itemView)

    @SuppressLint("SetTextI18n")
    fun onBind (item: CartModel.Cart) {
        binding.productName.text = item.productName
//        Picasso.get().load(item.image!![0]?.imageUrl).into(binding.coverImage)
        Picasso.get().load(item.image).into(binding.cardImage)
        binding.colorFromApi.text = item.color
        binding.sizeFromApi.text = item.size
        binding.priceFromApi.text = "$ ${item.productPrice}"

    }
}