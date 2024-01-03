package kh.edu.rupp.ite.trendy.UI.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.SearchView.OnCloseListener
import androidx.recyclerview.widget.RecyclerView
import kh.edu.rupp.ite.trendy.Model.Entry.ProductModel.ProductListModel
import kh.edu.rupp.ite.trendy.R
import kh.edu.rupp.ite.trendy.UI.ViewHolder.SaleProductItemViewHolder

class SaleProductItemAdapter(
    private val context: Context,
    private val productList: ProductListModel,
    private val listener: OnClickListener
) : RecyclerView.Adapter<SaleProductItemViewHolder>() {
    interface OnClickListener {
        fun onClickProduct(data: ProductListModel.ProductListModelItem, position: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SaleProductItemViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.sale_view_holder, parent, false)
        return SaleProductItemViewHolder(view, listener)
    }

    override fun getItemCount(): Int {
        return productList.size
    }

    override fun onBindViewHolder(holder: SaleProductItemViewHolder, position: Int) {
        holder.onBind(productList[position], position)
    }


}

