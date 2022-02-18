package kg.geektech.rickandmortykotlin.ui.remote

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import kg.geektech.rickandmortykotlin.R
import kg.geektech.rickandmortykotlin.core.extensions.showToast
import kg.geektech.rickandmortykotlin.core.network.Status
import kg.geektech.rickandmortykotlin.core.ui.BaseFragment
import kg.geektech.rickandmortykotlin.data.model.Result
import kg.geektech.rickandmortykotlin.databinding.FragmentLocalAndRemoteBinding
import kg.geektech.rickandmortykotlin.ui.adapters.CharacterAdapter
import kg.geektech.rickandmortykotlin.ui.detail_character.DetailCharacterFragment
import kg.geektech.rickandmortykotlin.utils.Constants.Companion.ID_CHARACTER
import org.koin.androidx.viewmodel.ext.android.viewModel

class RemoteFragment :
    BaseFragment<RemoteViewModel, FragmentLocalAndRemoteBinding>(),
    CharacterAdapter.CharacterItemClick {

    override val viewModel: RemoteViewModel by viewModel()
    private val adapter: CharacterAdapter by lazy {
        CharacterAdapter()
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

    override fun initAdapter() {
        adapter.setItemClickListener(this)
        binding.rvListOfCharacters.layoutManager = GridLayoutManager(requireContext(), 2)
        binding.rvListOfCharacters.adapter = adapter
    }

    override fun initViewModel() {
        viewModel.loading.observe(this) {
            binding.progressBar.isVisible = it
        }
        viewModel.getCharacters().observe(this) {
            when (it.status) {
                Status.LOADING -> {
                    viewModel.loading.postValue(true)
                }
                Status.SUCCESS -> {
                    viewModel.loading.postValue(false)
                    it.data?.results?.let { list -> adapter.setList(list) }
                }
                Status.ERROR -> {
                    viewModel.loading.postValue(false)
                    requireContext().showToast(it.message.toString())
                }
            }
        }
    }

    override fun initView() {
        binding.root.setBackgroundResource(R.color.green_for_remote_fragment_back)
    }

    override fun connectedToTheInternet() {
        binding.include.root.isVisible = false
    }

    override fun noInternetConnection() {
        binding.include.root.isVisible = true
    }

    override fun onItemClick(result: Result) {
        result.id?.let { openFragment(it) }
    }

    private fun openFragment(id: Int) {
        val args = Bundle()
        args.putInt(ID_CHARACTER, id)
        navigateFragment(R.id.detailCharacterFragment, args)
    }
}