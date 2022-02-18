package kg.geektech.rickandmortykotlin.ui.detail_character

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.switchMap
import kg.geektech.rickandmortykotlin.core.ui.BaseViewModel

class DetailCharacterViewModel(private val detailCharacterRepository: DetailCharacterRepository) :
    BaseViewModel() {

    private val _id = MutableLiveData<Int>()

    val getCharacterById = _id.switchMap {
        detailCharacterRepository.getCharacterById(it)
    }

    fun setId(id: Int) {
        _id.postValue(id)
    }
}