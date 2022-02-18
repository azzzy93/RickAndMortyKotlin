package kg.geektech.rickandmortykotlin.ui.local

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import kg.geektech.rickandmortykotlin.R
import kg.geektech.rickandmortykotlin.core.ui.BaseFragment
import kg.geektech.rickandmortykotlin.databinding.FragmentLocalAndRemoteBinding

class LocalFragment : BaseFragment<LocalViewModel, FragmentLocalAndRemoteBinding>() {

    override val viewModel: LocalViewModel by lazy {
        ViewModelProvider(this)[LocalViewModel::class.java]
    }

    override fun inflateViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FragmentLocalAndRemoteBinding {
        return FragmentLocalAndRemoteBinding.inflate(inflater, container, false)
    }

    override fun bindViewBinding(view: View): FragmentLocalAndRemoteBinding {
        return FragmentLocalAndRemoteBinding.bind(view)
    }

    override fun initView() {
        binding.root.setBackgroundResource(R.color.orange_for_local_fragment_back)
    }
}