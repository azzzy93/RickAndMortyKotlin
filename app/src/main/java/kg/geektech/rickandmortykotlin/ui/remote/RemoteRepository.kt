package kg.geektech.rickandmortykotlin.ui.remote

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import kg.geektech.rickandmortykotlin.core.network.Resource
import kg.geektech.rickandmortykotlin.data.model.Character
import kg.geektech.rickandmortykotlin.data.remote.RemoteDataSource
import kotlinx.coroutines.Dispatchers

class RemoteRepository(private val dataSource: RemoteDataSource) {

    fun getCharacters(): LiveData<Resource<Character>> = liveData(Dispatchers.IO) {
        emit(Resource.loading())
        val response = dataSource.getCharacters()
        emit(response)
    }
}