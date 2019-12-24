package br.com.igorf91.desafio.home

import br.com.igorf91.desafio.network.GithubApi

class HomeRepository(private val api: GithubApi) {

    fun loadRepositories(page: Long) = api.getRepositories(page)
}