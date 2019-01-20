package com.example.github.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Repo(val id: Int,
                @SerializedName("full_name") val fullName : String,
                val description : String,
                @SerializedName("stargazers_count") val stars : Long) : Serializable