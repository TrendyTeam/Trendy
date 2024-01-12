package kh.edu.rupp.ite.trendy.UI.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kh.edu.rupp.ite.trendy.Model.Entry.CartModel.CheckOutModel
import kh.edu.rupp.ite.trendy.R
import kh.edu.rupp.ite.trendy.UI.ViewHolder.CheckOutViewHolder

class CheckOutAdapter (
    private val context: Context,
    private val checkOutData: List<CheckOutModel.OrderDetails>
) : RecyclerView.Adapter<CheckOutViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CheckOutViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.activity_checkout, parent, false)
        return  CheckOutViewHolder((view))
    }

    override fun getItemCount(): Int {
        return checkOutData.size
    }

    override fun onBindViewHolder(holder: CheckOutViewHolder, position: Int) {
        holder.onBind(checkOutData[position])
    }

}