package com.example.github.base

import android.arch.lifecycle.ViewModel

abstract class BaseViewModel : ViewModel() {

   /* private val component: AppComponent = DaggerAppComponent
        .builder()
        .networkModule(AppModule)
        .build()

    init {
        inject()
    }

    *//**
     * Injects the required dependencies
     *//*
    private fun inject() {
        when (this) {
            is RepoListViewModel -> component.inject(this)
        }
    }*/

}