package kg.geektech.rickandmortykotlin.ui.view_pager

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayoutMediator
import kg.geektech.rickandmortykotlin.core.ui.BaseFragment
import kg.geektech.rickandmortykotlin.core.ui.BaseViewModel
import kg.geektech.rickandmortykotlin.databinding.FragmentViewPagerBinding
import kg.geektech.rickandmortykotlin.ui.local.LocalFragment
import kg.geektech.rickandmortykotlin.ui.remote.RemoteFragment

class ViewPagerFragment : BaseFragment<BaseViewModel, FragmentViewPagerBinding>() {

    override val viewModel: BaseViewModel = BaseViewModel()
    private lateinit var adapter: ViewPagerAdapter

    override fun inflateViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FragmentViewPagerBinding {
        return FragmentViewPagerBinding.inflate(inflater, container, false)
    }

    override fun bindViewBinding(view: View): FragmentViewPagerBinding {
        return FragmentViewPagerBinding.bind(view)
    }

    override fun initView() {
        initViewPager()
    }

    private fun initViewPager() {
        binding.viewPager.isSaveEnabled = false
        adapter = ViewPagerAdapter(this)
        adapter.setList(fillList())
        binding.viewPager.adapter = adapter
        val nameList = listOf("Remote Fragment", "Local Fragment")
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            tab.text = nameList[position]
        }.attach()
    }

    private fun fillList(): List<Fragment> {
        val list = ArrayList<Fragment>()
        list.add(RemoteFragment())
        list.add(LocalFragment())
        return list
    }
}