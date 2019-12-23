package br.com.igorf91.desafio.pullrequest

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import br.com.igorf91.desafio.util.callback
import br.com.igorf91.desafio.vo.PullRequestVO
import br.com.igorf91.desafio.vo.RepositoryVO

class PullRequestViewModel (private val repository: PullRequestRepository) : ViewModel() {

    companion object {
        private const val LOCAL_TAG = "PullRequestViewModel"
    }

    private var _pullRequestsList = MutableLiveData<List<PullRequestVO>>()
    val pullRequestsList: LiveData<List<PullRequestVO>> = _pullRequestsList

    fun loadPullRequest(repositoryVO: RepositoryVO) {
        repository.loadPullRequest(repositoryVO.owner.login, repositoryVO.name).enqueue(callback { response, throwable ->
            response?.let {
                response.body()?.let { _pullRequestsList.postValue(it) }
            }
            throwable?.let {
                Log.e(LOCAL_TAG, it.message?:"EMPTY RESPONSE")
            }
        })
    }
}

class PullRequestViewModelFactory(private val repository: PullRequestRepository)
    : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return PullRequestViewModel(repository) as T
    }
}