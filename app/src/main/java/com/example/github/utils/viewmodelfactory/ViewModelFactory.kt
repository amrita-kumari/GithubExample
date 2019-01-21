package com.example.github.utils.viewmodelfactory

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.example.github.network.RepoApi
import com.example.github.viewmodel.RepoListViewModel
import io.reactivex.Scheduler
import javax.inject.Inject
import javax.inject.Named

class ViewModelFactory
    @Inject constructor(private val repoApi: RepoApi,
                       @Named("IO") val subscribeOnScheduler :Scheduler,
                        @Named("Main") val observeOnScheduler: Scheduler) :
    ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(RepoListViewModel::class.java)) {
            RepoListViewModel(
                this.repoApi,
                this.subscribeOnScheduler,
                this.observeOnScheduler
            ) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}