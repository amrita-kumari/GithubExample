package com.example.github.ui

import android.arch.lifecycle.MutableLiveData
import com.example.github.base.BaseViewModel
import com.example.github.model.Repo

class RepoDetailViewModel : BaseViewModel() {
    private val repoTitle = MutableLiveData<String>()
    private val repoDescription = MutableLiveData<String>()

    fun bind(repo: Repo) {
        repoTitle.value = repo.fullName
        repoDescription.value = repo.description
    }

    fun getRepoTitle(): MutableLiveData<String> {
        return repoTitle
    }

    fun getRepoDescription(): MutableLiveData<String> {
        return repoDescription
    }
}