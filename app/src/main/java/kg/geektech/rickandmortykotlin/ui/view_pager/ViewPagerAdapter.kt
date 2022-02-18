package kg.geektech.rickandmortykotlin.ui.view_pager

import android.annotation.SuppressLint
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class ViewPagerAdapter(fragment: Fragment) :
    FragmentStateAdapter(fragment) {

    private val list = arrayListOf<Fragment>()

    @SuppressLint("NotifyDataSetChanged")
    fun setList(list: List<Fragment>) {
        this.list.addAll(list)
        notifyDataSetChanged()
    }

    override fun getItemCount() = list.size

    override fun createFragment(position: Int): Fragment {
        return list[position]
    }
}