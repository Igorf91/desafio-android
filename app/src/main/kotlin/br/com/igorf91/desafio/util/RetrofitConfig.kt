package br.com.igorf91.desafio.util

import br.com.igorf91.desafio.network.GithubApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitConfig {

    companion object {
        private const val BASE_URL = "https://api.github.com/"

        fun getGithubApi() =
            Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(GithubApi::class.java)
    }
}