package br.com.igorf91.desafio.vo

import com.google.gson.annotations.SerializedName

class RepositoryContainerVO (
    @SerializedName("items")
    val items: List<RepositoryVO>
)