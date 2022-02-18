package kg.geektech.rickandmortykotlin.ui.detail_character

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import kg.geektech.rickandmortykotlin.core.network.Resource
import kg.geektech.rickandmortykotlin.data.model.Result
import kg.geektech.rickandmortykotlin.data.remote.RemoteDataSource
import kotlinx.coroutines.Dispatchers

class DetailCharacterRepository(private val dataSource: RemoteDataSource) {

    fun getCharacterById(id: Int): LiveData<Resource<Result>> = liveData(Dispatchers.IO) {
        emit(Resource.loading())
        val response = dataSource.getCharacterById(id)
        emit(response)
    }

}