package com.example.github.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.view.View
import com.example.github.R
import com.example.github.base.BaseViewModel
import com.example.github.model.RepoResponse
import com.example.github.network.RepoApi
import com.example.github.ui.RepoListAdapter
import io.reactivex.disposables.Disposable
import javax.inject.Inject
import io.reactivex.Scheduler
import javax.inject.Named


class RepoListViewModel
@Inject constructor(private val repoApi: RepoApi,
                    @Named("IO") val subscribeOnScheduler :Scheduler, @Named("Main") val observeOnScheduler: Scheduler)
    : BaseViewModel() {

    private lateinit var disposable : Disposable
    val repoListAdapter: RepoListAdapter = RepoListAdapter()
    val errorMessage:MutableLiveData<Int> = MutableLiveData()
    val errorClickListener = View.OnClickListener { loadRepo() }

    val loading : MutableLiveData<Boolean> = MutableLiveData()

    fun loadRepo() {
        loading.value = true
        disposable = repoApi.getRepo()
            .observeOn(observeOnScheduler)
            .subscribeOn(subscribeOnScheduler)
            .subscribe({result -> onRepoLoaded(result)},{onRepoLoadFailed()})
    }

    private fun onRepoLoadFailed() {
        errorMessage.value = R.string.error_message
        loading.value = false
    }

    private fun onRepoLoaded(result: RepoResponse?) {
        loading.value = false
        if(result != null){
            repoListAdapter.updatePostList(result.repoList)
        }

    }

    override fun onCleared() {
        super.onCleared()
        disposable.dispose()
    }

}