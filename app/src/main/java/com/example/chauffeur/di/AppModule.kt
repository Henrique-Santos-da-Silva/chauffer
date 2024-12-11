package com.example.chauffeur.di

import com.example.chauffeur.http.HttpClient
import com.example.chauffeur.repositories.RideRepository
import com.example.chauffeur.ui.MainFragment
import com.example.chauffeur.viewmodel.RideViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single { HttpClient.retrofit() }
    single { RideRepository(api = get()) }
    viewModel { RideViewModel(repository = get()) }
//    factory { (view: RideContract.View) -> RidePresenter(repository = get(), contractView = view) }
    single { MainFragment() }
}