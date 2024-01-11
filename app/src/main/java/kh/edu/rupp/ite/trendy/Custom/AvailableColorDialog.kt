package kh.edu.rupp.ite.trendy.Custom

import android.content.Context
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.LinearLayoutManager
import kh.edu.rupp.ite.trendy.Model.Entry.ProductModel.OneProductModel
import kh.edu.rupp.ite.trendy.R
import kh.edu.rupp.ite.trendy.UI.Adapter.ListColorItemAdapter
import kh.edu.rupp.ite.trendy.databinding.BaseItemDailogLayoutBinding

class AvailableColorDialog(
    private val context: Context,
    private val mItem: ArrayList<OneProductModel.Item>,
    private var mAction: OnCreateListener? = null
):BaseDialogBinding<BaseItemDailogLayoutBinding>(context) {
    override fun setUpViewBinding(): BaseItemDailogLayoutBinding = BaseItemDailogLayoutBinding.inflate(layoutInflater)
    override fun onViewInit() {
        super.onViewInit()
        binding.btnClose.setOnClickListener { dismiss() }
        initRec(mItem)
    }

    private fun initRec(data: ArrayList<OneProductModel.Item>){
        val animation = AnimationUtils.loadLayoutAnimation(context, R.anim.layout_slide_item_scale)
        binding.recSize.layoutAnimation = animation
        binding.recSize.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = ListColorItemAdapter(context, data, object: ListColorItemAdapter.OnClickSIze{
                override fun sizeClick(item: OneProductModel.Item) {
                    mAction?.onSuccess(item)
                    dismiss()
                }

            })
        }
    }

    interface OnCreateListener {
        fun onSuccess(item: OneProductModel.Item)
    }
}