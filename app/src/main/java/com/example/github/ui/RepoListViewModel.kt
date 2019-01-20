package com.example.github.ui

import android.arch.lifecycle.MutableLiveData
import android.util.Log
import android.view.View
import com.example.github.R
import com.example.github.base.BaseViewModel
import com.example.github.model.RepoResponse
import com.example.github.network.RepoApi
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
import com.example.github.model.Repo


class RepoListViewModel : BaseViewModel(), RepoListAdapter.ItemClickListener {

    @Inject
    lateinit var repoApi: RepoApi
    private lateinit var disposable : Disposable
    val repoListAdapter: RepoListAdapter = RepoListAdapter(this)
    val errorMessage:MutableLiveData<Int> = MutableLiveData()
    val errorClickListener = View.OnClickListener { loadRepo() }
    val onItemClick: MutableLiveData<Repo> = MutableLiveData()

    val loading : MutableLiveData<Boolean> = MutableLiveData()
    
    init {
        loadRepo()
    }

    private fun loadRepo() {
        loading.value = true
        disposable = repoApi.getRepo()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
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

    override fun onItemClick(repo: Repo) {
        Log.e("TAG","------repo = "+repo.fullName)
        onItemClick.value = repo
    }


    override fun onCleared() {
        super.onCleared()
        disposable.dispose()
    }

}