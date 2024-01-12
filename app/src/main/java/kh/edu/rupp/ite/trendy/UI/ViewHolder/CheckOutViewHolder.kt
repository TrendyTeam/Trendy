package kh.edu.rupp.ite.trendy.UI.ViewHolder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kh.edu.rupp.ite.trendy.Model.Entry.CartModel.CheckOutModel
import kh.edu.rupp.ite.trendy.databinding.BagItemViewHolderBinding

class CheckOutViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private  val  binding : BagItemViewHolderBinding = BagItemViewHolderBinding.bind(itemView)


    fun onBind (item: CheckOutModel.OrderDetails) {

    }


}