package kh.edu.rupp.ite.trendy.UI.ViewHolder

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kh.edu.rupp.ite.trendy.Model.Entry.CartModel.CheckOutModel
import kh.edu.rupp.ite.trendy.databinding.BagItemViewHolderBinding
import kh.edu.rupp.ite.trendy.databinding.CheckOutViewHolderBinding

class CheckOutViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private  val  binding : CheckOutViewHolderBinding = CheckOutViewHolderBinding.bind(itemView)

    @SuppressLint("SetTextI18n")
    fun onBind (item: CheckOutModel.OrderDetails.Item) {
        binding.priceFromApi.text = "$ ${item.productPrice}"
        binding.productName.text = item.productName
        binding.sizeFromApi.text = item.size
        binding.colorFromApi.text = item.color

    }


}