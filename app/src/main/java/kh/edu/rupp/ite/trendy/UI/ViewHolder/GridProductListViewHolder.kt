package kh.edu.rupp.ite.trendy.UI.ViewHolder

import android.annotation.SuppressLint
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.squareup.picasso.Picasso
import kh.edu.rupp.ite.trendy.Model.Entry.ProductModel.ListProductWithDetailByCategory
import kh.edu.rupp.ite.trendy.UI.Adapter.ProductListAdapterForGridLayout
import kh.edu.rupp.ite.trendy.databinding.ProductItemViewholderForGridLayoutBinding

class GridProductListViewHolder(itemView: View, private val mListener: ProductListAdapterForGridLayout.OnProductClick):ViewHolder(itemView) {
    private val binding : ProductItemViewholderForGridLayoutBinding = ProductItemViewholderForGridLayoutBinding.bind(itemView)

    @SuppressLint("SetTextI18n")
    fun onBind(item: ListProductWithDetailByCategory.ListProductWithDetailByCategoryItem, position: Int){
        try {

            item.image[0].imageUrl?.let { Picasso.get().load(it).into(binding.productImage) }
            item.productName?.let { binding.productName.text = it }

            binding.originalPrice.text = "$ ${ item.productPrice.toString()}"
            binding.discountPerce.visibility = View.GONE
            binding.afterDiscountPrice.visibility = View.GONE

           if (item.productDiscount !== null){
               item.productDiscount?.let { binding.discountPerce.text = "- $it%" }
               binding.afterDiscountPrice.text = "$ ${calculateDiscount(item.productDiscount!!,
                   item.productPrice!!)}"
               binding.originalPrice.paintFlags
           }

            binding.root.setOnClickListener {
                mListener.onClickProduct(item)
            }

        }catch (e:Exception){
            Log.d("ERROR_PRODUCT", "Error = ${e.message} ")
        }
    }


    private fun calculateDiscount(dis: Double, unitPrice: Double): Double {
        return unitPrice * (100 - dis) / 100
    }
}