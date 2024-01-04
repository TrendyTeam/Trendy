package kh.edu.rupp.ite.trendy.UI.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import kh.edu.rupp.ite.trendy.Model.Entry.ProductModel.ProductListModel
import kh.edu.rupp.ite.trendy.R
import kh.edu.rupp.ite.trendy.UI.ViewHolder.NewProductItemViewHolder
import java.text.FieldPosition

class NewProductItemAdapter(
    private val context: Context,
    private val productList: ProductListModel,
    private val listener: OnClickListener,
) : RecyclerView.Adapter<NewProductItemViewHolder>() {

    interface OnClickListener {
        fun onClickListener(data: ProductListModel.ProductListModelItem, position: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewProductItemViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.sale_view_holder, parent, false)
        return NewProductItemViewHolder(view, listener)
    }

    override fun getItemCount(): Int {
        return productList.size
    }

    override fun onBindViewHolder(holder: NewProductItemViewHolder, position: Int) {
        holder.onBind(productList[position], position)
    }
}