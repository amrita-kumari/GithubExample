package com.example.github.network

import com.example.github.model.RepoResponse
import io.reactivex.Observable
import retrofit2.http.GET

interface RepoApi {

    @GET("repositories?q=android%20language:java&sort=stars&order=desc&per_page=30&page=1")
    fun getRepo() : Observable<RepoResponse>
}