package kh.edu.rupp.ite.trendy.UI.ViewHolder

import android.annotation.SuppressLint
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.squareup.picasso.Picasso
import kh.edu.rupp.ite.trendy.Model.Entry.ProductModel.ListProductWithDetailByCategory
import kh.edu.rupp.ite.trendy.UI.Adapter.ProductListAdapterForListLayout
import kh.edu.rupp.ite.trendy.Util.calculateDiscount
import kh.edu.rupp.ite.trendy.Util.totalPriceFormat
import kh.edu.rupp.ite.trendy.databinding.ItemProductSingleListLayoutBinding

class ListProductViewHolderForListLayout(itemView:View, private val mListener: ProductListAdapterForListLayout.OnProductClick):ViewHolder(itemView) {
    private val binding: ItemProductSingleListLayoutBinding = ItemProductSingleListLayoutBinding.bind(itemView)

    @SuppressLint("SetTextI18n")
    fun onBind(item: ListProductWithDetailByCategory.ListProductWithDetailByCategoryItem, position: Int){
        try {

            item.image[0].imageUrl?.let { Picasso.get().load(it).into(binding.productImage) }
            item.productName?.let { binding.productName.text = it }

            if (!item.productDiscount!!.equals(0.0)){
                binding.originalPrice.text = "US $${ item.productPrice.toString()}"
                item.productDiscount?.let { binding.discountPerce.text = "- $it%" }
                binding.afterDiscountPrice.text = "US $${totalPriceFormat(calculateDiscount(item.productDiscount!!, item.productPrice!!))}"
                binding.originalPrice.paintFlags
                binding.discountPerce.visibility = View.VISIBLE
                binding.afterDiscountPrice.visibility = View.VISIBLE
            }else{
                binding.originalPrice.text = "US $${ item.productPrice.toString()}"
                binding.discountPerce.visibility = View.GONE
                binding.afterDiscountPrice.visibility = View.GONE
            }
//            binding.discountPerce.visibility = View.GONE
//            binding.afterDiscountPrice.visibility = View.GONE

//            if (item.productDiscount !== null){
//                item.productDiscount?.let { binding.discountPerce.text = "- $it%" }
//                binding.afterDiscountPrice.text = "$ ${calculateDiscount(item.productDiscount!!,
//                    item.productPrice!!)}"
//                binding.originalPrice.paintFlags
//            }

            binding.root.setOnClickListener {
                mListener.onClickProduct(item)
            }

        }catch (e:Exception){
            Log.d("ERROR_PRODUCT", "Error = ${e.message} ")
        }
    }

}