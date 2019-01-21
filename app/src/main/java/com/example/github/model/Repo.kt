package com.example.github.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Repo(val id: Int,
                @SerializedName("full_name") val fullName : String,
                val description : String,
                val language : String,
                @SerializedName("stargazers_count") val stars : Long,
                val owner : Owner,
                val license : License) : Serializable

data class Owner(@SerializedName("avatar_url") val avatar : String,
                 val login : String)

data class License(val name : String)

