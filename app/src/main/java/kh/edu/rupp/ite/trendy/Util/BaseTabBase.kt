package kh.edu.rupp.ite.trendy.Util

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import kh.edu.rupp.ite.trendy.Model.Entry.CategoryModel.TopCategoryModel
import kh.edu.rupp.ite.trendy.UI.Fragment.Shop.BaseSubCategoryFragment

class BaseTabBase(
    fragmentActivity: FragmentActivity,
    private val data: TopCategoryModel
):FragmentStateAdapter(fragmentActivity) {

    override fun getItemCount(): Int {
        return data.size
    }

    override fun createFragment(position: Int): Fragment {
        return BaseSubCategoryFragment(data[position].id)
    }

}