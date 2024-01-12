package kh.edu.rupp.ite.trendy.UI.ViewHolder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kh.edu.rupp.ite.trendy.Model.Entry.CartModel.CartModel
import kh.edu.rupp.ite.trendy.Model.Entry.CartModel.CheckOutModel
import kh.edu.rupp.ite.trendy.databinding.ActivityCheckoutBinding

class CheckOutViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private  val  binding : ActivityCheckoutBinding = ActivityCheckoutBinding.bind(itemView)


    fun onBind (item: CheckOutModel.OrderDetails) {

    }


}