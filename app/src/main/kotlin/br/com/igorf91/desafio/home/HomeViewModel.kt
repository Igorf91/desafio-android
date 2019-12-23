package br.com.igorf91.desafio.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import br.com.igorf91.desafio.util.callback
import br.com.igorf91.desafio.vo.RepositoryVO

class HomeViewModel(private val homeRepository: HomeRepository) : ViewModel() {

    companion object {
        private const val LOCAL_TAG = "HomeViewModel"
    }

    private var _repositoryList = MutableLiveData<List<RepositoryVO>>()
    val repositoryList: LiveData<List<RepositoryVO>> = _repositoryList

    fun loadList(page: Long) {
        homeRepository.loadRepositories(page).enqueue(callback{ response, throwable ->
            response?.let {
                response.body()?.let { _repositoryList.postValue(it.items) }
            }
            throwable?.let {
                Log.e(LOCAL_TAG, it.message?:"EMPTY RESPONSE")
            }
        })
    }
}

class HomeViewModelFactory(private val homeRepository: HomeRepository)
    : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return HomeViewModel(homeRepository) as T
    }
}