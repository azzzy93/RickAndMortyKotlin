package kg.geektech.rickandmortykotlin.ui.remote

import androidx.lifecycle.LiveData
import kg.geektech.rickandmortykotlin.core.network.Resource
import kg.geektech.rickandmortykotlin.core.ui.BaseViewModel
import kg.geektech.rickandmortykotlin.data.model.Character

class RemoteViewModel(private val remoteRepository: RemoteRepository) : BaseViewModel() {

    fun getCharacters(): LiveData<Resource<Character>> {
        return remoteRepository.getCharacters()
    }
}