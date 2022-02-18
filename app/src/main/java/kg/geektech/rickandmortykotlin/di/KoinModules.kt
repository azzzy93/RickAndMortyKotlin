package kg.geektech.rickandmortykotlin.di

import kg.geektech.rickandmortykotlin.core.network.networkModules

val koinModules = listOf(
    networkModules,
    repoModules,
    viewModules
)