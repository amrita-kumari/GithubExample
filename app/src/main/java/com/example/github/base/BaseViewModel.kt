package com.example.github.base

import android.arch.lifecycle.ViewModel
import com.example.github.di.module.NetworkModule
import com.example.github.di.module.component.DaggerViewModelComponent
import com.example.github.di.module.component.ViewModelComponent
import com.example.github.ui.RepoListViewModel

abstract class BaseViewModel : ViewModel() {

    private val component: ViewModelComponent = DaggerViewModelComponent
        .builder()
        .networkModule(NetworkModule)
        .build()

    init {
        inject()
    }

    /**
     * Injects the required dependencies
     */
    private fun inject() {
        when (this) {
            is RepoListViewModel -> component.inject(this)
        }
    }

}