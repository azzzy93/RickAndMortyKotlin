package kg.geektech.rickandmortykotlin.ui.detail_character

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import kg.geektech.rickandmortykotlin.core.extensions.load
import kg.geektech.rickandmortykotlin.core.extensions.showToast
import kg.geektech.rickandmortykotlin.core.network.Status
import kg.geektech.rickandmortykotlin.core.ui.BaseFragment
import kg.geektech.rickandmortykotlin.databinding.FragmentDetailCharacterBinding
import kg.geektech.rickandmortykotlin.utils.Constants.Companion.ID_CHARACTER
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.text.SimpleDateFormat
import java.util.*

class DetailCharacterFragment :
    BaseFragment<DetailCharacterViewModel, FragmentDetailCharacterBinding>() {

    override val viewModel: DetailCharacterViewModel by viewModel()

    override fun inflateViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FragmentDetailCharacterBinding {
        return FragmentDetailCharacterBinding.inflate(inflater, container, false)
    }

    override fun bindViewBinding(view: View): FragmentDetailCharacterBinding {
        return FragmentDetailCharacterBinding.bind(view)
    }

    override fun initView() {
        arguments?.getInt(ID_CHARACTER)?.let {
            viewModel.setId(it)
        }
    }

    override fun initViewModel() {
        viewModel.loading.observe(this) {
            binding.progressBar.isVisible = it
            binding.scrollView.isVisible = !it
            binding.tvDate.isVisible = !it
        }
        viewModel.getCharacterById.observe(this) {
            when (it.status) {
                Status.LOADING -> {
                    viewModel.loading.postValue(true)
                }
                Status.SUCCESS -> {
                    viewModel.loading.postValue(false)
                    binding.tvNameCharacter.text = it.data?.name
                    it.data?.image?.let { it1 -> binding.ivCharacter.load(it1) }
                    val info = "Id: ${it.data?.id}\n" +
                            "Status: ${it.data?.status}\n" +
                            "Species: ${it.data?.species}\n" +
                            "Gender: ${it.data?.gender}\n" +
                            "Origin: ${it.data?.origin?.name}\n" +
                            "Location: ${it.data?.location?.name}"
                    binding.tvInfo.text = info
                    binding.tvDate.text = formatDate(it.data?.created)
                }
                Status.ERROR -> {
                    viewModel.loading.postValue(false)
                    requireContext().showToast(it.message.toString())
                }
            }
        }
    }

    private fun formatDate(dateString: String?): String? {
        return if (dateString == null) {
            null
        } else {
            val format = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault())
            val date: Date = format.parse(dateString) ?: Calendar.getInstance().time
            val newFormat = SimpleDateFormat("dd MMMM yyyy HH:mm", Locale.getDefault())
            newFormat.format(date)
        }
    }
}