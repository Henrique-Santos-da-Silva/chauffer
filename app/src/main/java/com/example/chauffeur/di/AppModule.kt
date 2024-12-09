package com.example.chauffeur.di

import com.example.chauffeur.http.HttpClient
import org.koin.dsl.module

val appModule = module {
    single { HttpClient.retrofit() }
}