package com.example.github.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.example.github.model.Repo

class RepoViewModel: ViewModel() {
    val repoTitle = MutableLiveData<String>()
    val repoDescription = MutableLiveData<String>()
    val stars = MutableLiveData<String>()
    val language = MutableLiveData<String>()
    val avatar = MutableLiveData<String>()

    fun bind(repo: Repo){
        repoTitle.value = repo.fullName
        repoDescription.value = repo.description
        stars.value = repo.stars.toString()
        language.value = repo.language
        avatar.value = repo.owner.avatar
    }
}