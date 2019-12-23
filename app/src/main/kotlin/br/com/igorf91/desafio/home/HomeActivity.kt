package br.com.igorf91.desafio.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.igorf91.desafio.R
import br.com.igorf91.desafio.adapter.RepositoryItemAdapter
import br.com.igorf91.desafio.util.RetrofitConfig.Companion.getGithubApi
import kotlinx.android.synthetic.main.activity_home.repositoriesListRecycler

class HomeActivity : AppCompatActivity() {

    private val adapter = RepositoryItemAdapter()
    private lateinit var homeViewModel: HomeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        homeViewModel = ViewModelProviders
            .of(this, HomeViewModelFactory(HomeRepository(getGithubApi())))
            .get(HomeViewModel::class.java)

        setupRecyclerView()
        setupListeners()
        setupCall()
    }

    private fun setupRecyclerView() {
        repositoriesListRecycler.adapter = adapter
        repositoriesListRecycler.layoutManager = LinearLayoutManager(this)
    }

    private fun setupListeners() {
        homeViewModel.repositoryList.observe(this, Observer {
            adapter.loadItems(it)
        })
    }

    private fun setupCall() {
        homeViewModel.loadList(1L)
    }
}
