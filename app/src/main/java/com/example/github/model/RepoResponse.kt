package com.example.github.model

import com.google.gson.annotations.SerializedName

data class RepoResponse(@SerializedName("items") val repoList: List<Repo>)