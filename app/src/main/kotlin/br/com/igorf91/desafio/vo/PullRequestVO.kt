package br.com.igorf91.desafio.vo

import com.google.gson.annotations.SerializedName

class PullRequestVO (
    @SerializedName("id")
    val id: Long,
    @SerializedName("title")
    val title: String,
    @SerializedName("user")
    val user: UserVO,
    @SerializedName("body")
    val body: String,
    @SerializedName("html_url")
    val url: String,
    @SerializedName("created_at")
    val createdAt: String
)