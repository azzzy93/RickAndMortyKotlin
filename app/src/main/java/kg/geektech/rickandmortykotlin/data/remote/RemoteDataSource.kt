package kg.geektech.rickandmortykotlin.data.remote

import kg.geektech.rickandmortykotlin.core.network.BaseDataSource
import kg.geektech.rickandmortykotlin.core.network.Resource
import kg.geektech.rickandmortykotlin.data.model.Character
import kg.geektech.rickandmortykotlin.data.model.Result

class RemoteDataSource(private val apiService: ApiService) : BaseDataSource() {

    suspend fun getCharacters(): Resource<Character> {
        return getResult {
            apiService.getCharacters()
        }
    }

    suspend fun getCharacterById(id: Int): Resource<Result> {
        return getResult {
            apiService.getCharacterById(id)
        }
    }
}