package kh.edu.rupp.ite.trendy.UI.ViewHolder

import android.annotation.SuppressLint
import android.graphics.Paint
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.squareup.picasso.Picasso
import kh.edu.rupp.ite.trendy.Model.Entry.ProductModel.ListProductWithDetailByCategory
import kh.edu.rupp.ite.trendy.UI.Adapter.ProductListAdapterForGridLayout
import kh.edu.rupp.ite.trendy.UI.Adapter.ProductListAdapterForGridLayout.Companion.VIEW_TYPE_SMALL
import kh.edu.rupp.ite.trendy.Util.calculateDiscount
import kh.edu.rupp.ite.trendy.Util.totalPriceFormat
import kotlinx.android.synthetic.main.item_product_single_list_layout.view.afterDiscountPriceList
import kotlinx.android.synthetic.main.item_product_single_list_layout.view.discountPerceList
import kotlinx.android.synthetic.main.item_product_single_list_layout.view.original_priceList
import kotlinx.android.synthetic.main.item_product_single_list_layout.view.productImageList
import kotlinx.android.synthetic.main.item_product_single_list_layout.view.productNameList
import kotlinx.android.synthetic.main.product_item_viewholder_for_grid_layout.view.afterDiscountPriceGrid
import kotlinx.android.synthetic.main.product_item_viewholder_for_grid_layout.view.discountPerceGrid
import kotlinx.android.synthetic.main.product_item_viewholder_for_grid_layout.view.original_priceGrid
import kotlinx.android.synthetic.main.product_item_viewholder_for_grid_layout.view.productImageGrid
import kotlinx.android.synthetic.main.product_item_viewholder_for_grid_layout.view.productNameGrid

class GridProductListViewHolder(
    itemView: View,
    private val mListener: ProductListAdapterForGridLayout.OnProductClick,
    private val viewType: Int
) : ViewHolder(itemView) {
    @SuppressLint("SetTextI18n")
    fun onBind(
        item: ListProductWithDetailByCategory.ListProductWithDetailByCategoryItem,
        position: Int
    ) {
        try {
            if (viewType == VIEW_TYPE_SMALL) {
                item.image[0].imageUrl?.let {
                    Picasso.get().load(it).into(itemView.productImageGrid)
                }
                item.productName?.let { itemView.productNameGrid.text = it }
                Log.d("DISCOUNT_PR", "discount = ${item.productDiscount} ")
                if (!item.productDiscount!!.equals(0.0)) {
                    itemView.original_priceGrid.text = "US $${item.productPrice.toString()}"
                    item.productDiscount?.let { itemView.discountPerceGrid.text = "- $it%" }
                    itemView.afterDiscountPriceGrid.text = "$ ${
                        totalPriceFormat(
                            calculateDiscount(
                                item.productDiscount!!,
                                item.productPrice!!
                            )
                        )
                    }"
                    itemView.original_priceGrid.paintFlags = itemView.original_priceGrid.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
                    itemView.discountPerceGrid.visibility = View.VISIBLE
                    itemView.afterDiscountPriceGrid.visibility = View.VISIBLE
                } else {
                    itemView.original_priceGrid.text = "US $${item.productPrice.toString()}"
                    itemView.discountPerceGrid.visibility = View.GONE
                    itemView.afterDiscountPriceGrid.visibility = View.GONE
                }

                itemView.rootView.setOnClickListener {
                    mListener.onClickProduct(item)
                }
            } else {
                item.image[0].imageUrl?.let {
                    Picasso.get().load(it).into(itemView.productImageList)
                }
                item.productName?.let { itemView.productNameList.text = it }
                Log.d("DISCOUNT_PR", "discount = ${item.productDiscount} ")
                if (!item.productDiscount!!.equals(0.0)) {
                    itemView.original_priceList.text = "US $${item.productPrice.toString()}"
                    item.productDiscount?.let { itemView.discountPerceList.text = "- $it%" }
                    itemView.afterDiscountPriceList.text = "$ ${
                        totalPriceFormat(
                            calculateDiscount(
                                item.productDiscount!!,
                                item.productPrice!!
                            )
                        )
                    }"
                    itemView.original_priceList.paintFlags = itemView.original_priceList.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
                    itemView.discountPerceList.visibility = View.VISIBLE
                    itemView.afterDiscountPriceList.visibility = View.VISIBLE
                } else {
                    itemView.original_priceList.text = "US $${item.productPrice.toString()}"
                    itemView.discountPerceList.visibility = View.GONE
                    itemView.afterDiscountPriceList.visibility = View.GONE
                }
                itemView.rootView.setOnClickListener {
                    mListener.onClickProduct(item)
                }
            }

        } catch (e: Exception) {
            Log.d("ERROR_PRODUCT", "Error = ${e.message} ")
        }
    }


}