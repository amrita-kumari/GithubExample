package com.example.github.di.module.component

import com.example.github.di.module.NetworkModule
import com.example.github.ui.RepoListViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [(NetworkModule::class)])
interface ViewModelComponent {

    fun inject(repoListViewModel: RepoListViewModel)

}