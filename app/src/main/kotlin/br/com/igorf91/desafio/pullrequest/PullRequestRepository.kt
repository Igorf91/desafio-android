package br.com.igorf91.desafio.pullrequest

import br.com.igorf91.desafio.network.GithubApi

class PullRequestRepository(private val api: GithubApi) {

    fun loadPullRequest(nickName: String, repositoryName: String) =
        api.getPullRequests(nickName, repositoryName)
}