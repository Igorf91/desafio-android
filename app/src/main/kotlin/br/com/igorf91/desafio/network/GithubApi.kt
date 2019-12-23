package br.com.igorf91.desafio.network

import br.com.igorf91.desafio.vo.PullRequestVO
import br.com.igorf91.desafio.vo.RepositoryContainerVO
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GithubApi {

    companion object {
        const val DEFAULT_QUERY = "language:Java"
        const val DEFAULT_SORT = "star"
    }

    @GET("search/repositories")
    fun getRepositories(
        @Query("page") page: Long,
        @Query("q") query: String = DEFAULT_QUERY,
        @Query("sort") sort: String = DEFAULT_SORT
    ): Call<RepositoryContainerVO>

    @GET("/repos/{creator}/{repository}/pulls")
    fun getPullRequests(
        @Path("creator") login: String,
        @Path("repository") repositoryName: String
    ): Call<List<PullRequestVO>>
}