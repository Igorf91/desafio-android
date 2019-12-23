package br.com.igorf91.desafio.pullrequest

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.igorf91.desafio.R
import br.com.igorf91.desafio.adapter.PullRequestItemAdapter
import br.com.igorf91.desafio.util.RetrofitConfig.Companion.getGithubApi
import br.com.igorf91.desafio.vo.PullRequestVO
import br.com.igorf91.desafio.vo.RepositoryVO
import kotlinx.android.synthetic.main.activity_pull_request.pullRequestEmptyText
import kotlinx.android.synthetic.main.activity_pull_request.pullRequestListRecycler
import kotlinx.android.synthetic.main.activity_pull_request.pullRequestLoader

class PullRequestActivity : AppCompatActivity() {

    private val adapter = PullRequestItemAdapter()
    private lateinit var viewModel: PullRequestViewModel

    companion object {
        private const val EXTRA_DATA = "extra-repository"

        operator fun invoke(context: Context, repositoryVO: RepositoryVO): Intent {
            return Intent(context, PullRequestActivity::class.java).also {
                it.putExtra(EXTRA_DATA, repositoryVO)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pull_request)

        viewModel = ViewModelProviders
            .of(this, PullRequestViewModelFactory(PullRequestRepository(getGithubApi())))
            .get(PullRequestViewModel::class.java)

        setupRecyclerView()
        setupListeners()
        setupCall()
    }

    private fun setupRecyclerView() {
        pullRequestListRecycler.adapter = adapter
        pullRequestListRecycler.layoutManager = LinearLayoutManager(this)
    }

    private fun setupListeners() {
        viewModel.pullRequestsList.observe(this, Observer {
            if(it.isNotEmpty())
                loadList(it)
            else
                showEmptyState()
        })
    }

    private fun loadList(it: List<PullRequestVO>) {
        adapter.loadItems(it)
        pullRequestListRecycler.visibility = VISIBLE
        pullRequestEmptyText.visibility = GONE
        pullRequestLoader.visibility = GONE
    }

    private fun showEmptyState() {
        pullRequestListRecycler.visibility = GONE
        pullRequestEmptyText.visibility = VISIBLE
        pullRequestLoader.visibility = GONE
    }

    private fun setupCall() {
        viewModel.loadPullRequest(getRepository())
    }

    private fun getRepository() = intent.getParcelableExtra<RepositoryVO>(EXTRA_DATA)
}
