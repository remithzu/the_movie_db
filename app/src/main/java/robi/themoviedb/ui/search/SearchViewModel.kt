package robi.themoviedb.ui.search

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import robi.themoviedb.app.App
import robi.themoviedb.common.Utils
import robi.themoviedb.data.model.MovieResponse
import robi.themoviedb.network.NetworkState
import robi.themoviedb.repository.MovieRepository
import javax.inject.Inject

class SearchViewModel @Inject constructor(application: Application, private val repository: MovieRepository) : AndroidViewModel(application) {
    private var job: Job? = null
    private val _movieRepository = MutableLiveData<NetworkState<MovieResponse>>()
    val movieRepository: LiveData<NetworkState<MovieResponse>> = _movieRepository

    fun getSearch(q: String, page: Int) {
        val app = getApplication<App>()
        _movieRepository.postValue(NetworkState.Loading())
        viewModelScope.launch {
            try {
                if (Utils.isConnect(app)) {
                    val response = repository.getSearch(q, page)
                    if (response.isSuccessful) {
                        response.body().let {
                            _movieRepository.postValue(NetworkState.Success(it!!))
                        }
                    }
                }
            } catch (t: Throwable) {
                _movieRepository.postValue(NetworkState.Error(t))
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }
}