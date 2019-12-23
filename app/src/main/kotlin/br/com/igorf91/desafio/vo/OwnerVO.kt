package br.com.igorf91.desafio.vo

import com.google.gson.annotations.SerializedName

class OwnerVO(
    @SerializedName("id")
    val id: Long,
    @SerializedName("login")
    val login: String,
    @SerializedName("avatar_url")
    val avatarUrl: String
)