package kg.geektech.rickandmortykotlin.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kg.geektech.rickandmortykotlin.core.extensions.load
import kg.geektech.rickandmortykotlin.data.model.Result
import kg.geektech.rickandmortykotlin.databinding.ListForCharacterBinding

class CharacterAdapter : RecyclerView.Adapter<CharacterAdapter.CharactersViewHolder>() {

    private val list = arrayListOf<Result>()
    private lateinit var characterItemClick: CharacterItemClick

    fun setList(list: List<Result>) {
        this.list.addAll(list)
        this.list.forEachIndexed { index, _ ->
            notifyItemChanged(index)
        }
    }

    fun setItemClickListener(characterItemClick: CharacterItemClick) {
        this.characterItemClick = characterItemClick
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CharactersViewHolder {
        val binding =
            ListForCharacterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CharactersViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CharactersViewHolder, position: Int) {
        holder.onBind(list[position])
    }

    override fun getItemCount() = list.size

    inner class CharactersViewHolder(private val binding: ListForCharacterBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun onBind(result: Result) {
            result.image?.let { binding.ivCharacter.load(it) }
            binding.tvNameCharacter.text = result.name

            itemView.setOnClickListener {
                characterItemClick.onItemClick(result)
            }
        }
    }

    interface CharacterItemClick {
        fun onItemClick(result: Result)
    }
}