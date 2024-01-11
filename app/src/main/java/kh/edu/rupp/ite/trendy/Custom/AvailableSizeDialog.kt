package kh.edu.rupp.ite.trendy.Custom

import android.content.Context
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.LinearLayoutManager
import kh.edu.rupp.ite.trendy.Model.Entry.ProductModel.OneProductModel
import kh.edu.rupp.ite.trendy.R
import kh.edu.rupp.ite.trendy.UI.Adapter.ListSizeItemAdapter
import kh.edu.rupp.ite.trendy.databinding.BaseItemDailogLayoutBinding

class AvailableSizeDialog(
    private val context: Context,
    private val mItem: ArrayList<OneProductModel.Item>,
    private var mAction: OnCreateListener? = null
) : BaseDialogBinding<BaseItemDailogLayoutBinding>(context) {


    override fun setUpViewBinding(): BaseItemDailogLayoutBinding =
        BaseItemDailogLayoutBinding.inflate(layoutInflater)

    override fun onViewInit() {
        super.onViewInit()
        binding.btnClose.setOnClickListener { dismiss() }
        val itemSize = ArrayList<OneProductModel.Item>()
            for (i in mItem) {
                val newSize = i.size
                // Check if the size already exists in itemSize
                if (!itemSize.any { it.size == newSize }) {
                    itemSize.add(i)
                }
            }
        initRec(itemSize)
    }

    private fun initRec(data: ArrayList<OneProductModel.Item>) {
        val animation = AnimationUtils.loadLayoutAnimation(context, R.anim.layout_slide_item_scale)
        binding.recSize.layoutAnimation = animation
        binding.recSize.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = ListSizeItemAdapter(context, data, object : ListSizeItemAdapter.OnClickSIze {
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