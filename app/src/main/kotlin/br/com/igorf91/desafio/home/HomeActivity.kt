package br.com.igorf91.desafio.home

import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.igorf91.desafio.R
import br.com.igorf91.desafio.adapter.RepositoryItemAdapter
import br.com.igorf91.desafio.util.EndlessRecyclerViewScrollListener
import br.com.igorf91.desafio.util.RetrofitConfig.Companion.getGithubApi
import br.com.igorf91.desafio.vo.RepositoryVO
import kotlinx.android.synthetic.main.activity_home.homeLoader
import kotlinx.android.synthetic.main.activity_home.repositoriesListRecycler

class HomeActivity : AppCompatActivity() {

    private val adapter = RepositoryItemAdapter()
    private lateinit var homeViewModel: HomeViewModel
    private lateinit var linearLayoutManager: LinearLayoutManager

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
        linearLayoutManager = LinearLayoutManager(this)
        repositoriesListRecycler.layoutManager = linearLayoutManager

        val scrollListener: EndlessRecyclerViewScrollListener =
            object : EndlessRecyclerViewScrollListener(linearLayoutManager) {
                override fun onLoadMore(
                    page: Int,
                    totalItemsCount: Int,
                    view: RecyclerView?
                ) {
                    homeViewModel.loadList((page + 1).toLong())
                }
            }
        repositoriesListRecycler.addOnScrollListener(scrollListener)
    }

    private fun setupListeners() {
        homeViewModel.repositoryList.observe(this, Observer {
            loadList(it)
        })
    }

    private fun loadList(it: List<RepositoryVO>) {
        adapter.loadItems(it)
        repositoriesListRecycler.visibility = VISIBLE
        homeLoader.visibility = GONE
    }

    private fun setupCall() {
        homeViewModel.loadList(1L)
    }
}
