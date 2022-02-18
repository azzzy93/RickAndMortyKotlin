package kg.geektech.rickandmortykotlin.di

import kg.geektech.rickandmortykotlin.ui.detail_character.DetailCharacterViewModel
import kg.geektech.rickandmortykotlin.ui.remote.RemoteViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModules = module {
    viewModel { RemoteViewModel(get()) }
    viewModel { DetailCharacterViewModel(get()) }
}