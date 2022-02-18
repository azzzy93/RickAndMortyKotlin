package kg.geektech.rickandmortykotlin.di

import kg.geektech.rickandmortykotlin.ui.detail_character.DetailCharacterRepository
import kg.geektech.rickandmortykotlin.ui.remote.RemoteRepository
import org.koin.dsl.module

val repoModules = module {
    single { RemoteRepository(get()) }
    single { DetailCharacterRepository(get()) }
}