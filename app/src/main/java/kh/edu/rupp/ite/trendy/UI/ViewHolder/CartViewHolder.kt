package kh.edu.rupp.ite.trendy.UI.ViewHolder

import android.annotation.SuppressLint
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kh.edu.rupp.ite.trendy.Model.Entry.CartModel.CartModel
import kh.edu.rupp.ite.trendy.UI.Adapter.CartAdapter
import kh.edu.rupp.ite.trendy.databinding.BagItemViewHolderBinding

class CartViewHolder (
    itemView : View,
    private val mListener: CartAdapter.ClickListener
) : RecyclerView.ViewHolder(itemView) {

    private  val binding: BagItemViewHolderBinding = BagItemViewHolderBinding.bind(itemView)

    @SuppressLint("SetTextI18n")
    fun onBind (item: CartModel.Cart) {
        item.productName?.let {   binding.productName.text = it}

//        Picasso.get().load(item.image!![0]?.imageUrl).into(binding.coverImage)
        if (!item.image.isNullOrEmpty()){
            item.image?.let { Picasso.get().load(it).into(binding.cardImage) }

        }
        item.color?.let{ binding.colorFromApi.text = it }
        item.size?.let { binding.sizeFromApi.text = it }
        item.productPrice?.let {  binding.priceFromApi.text = it.toString() }

        binding.deleteBtn.setOnClickListener {
            mListener.onDelete(item.cartId!!)
        }

    }
}