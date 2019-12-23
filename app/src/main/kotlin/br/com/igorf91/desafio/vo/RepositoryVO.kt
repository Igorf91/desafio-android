package br.com.igorf91.desafio.vo

import com.google.gson.annotations.SerializedName

class RepositoryVO(
    @SerializedName("id")
    val id: Long,
    @SerializedName("name")
    val name: String,
    @SerializedName("full_name")
    val fullName: String,
    @SerializedName("owner")
    val owner: OwnerVO,
    @SerializedName("description")
    val description: String,
    @SerializedName("stargazers_count")
    val stargazersCount: Long,
    @SerializedName("forks_count")
    val forksCount: Long
)