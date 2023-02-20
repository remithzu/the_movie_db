package robi.themoviedb.ui.detail

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import robi.themoviedb.app.App
import robi.themoviedb.common.Utils
import robi.themoviedb.data.model.ActorResponse
import robi.themoviedb.data.model.MovieDetailResponse
import robi.themoviedb.network.NetworkState
import robi.themoviedb.repository.MovieRepository
import javax.inject.Inject

class DetailViewModel @Inject constructor(application: Application, private val repository: MovieRepository) : AndroidViewModel(application) {
    private var job: Job? = null
    private val _movieDetailRepository = MutableLiveData<NetworkState<MovieDetailResponse>>()
    val movieDetailRepository: LiveData<NetworkState<MovieDetailResponse>> = _movieDetailRepository

    private val _movieActorRepository = MutableLiveData<NetworkState<ActorResponse>>()
    val movieActorRepository: LiveData<NetworkState<ActorResponse>> = _movieActorRepository

    private val _movieTrailerRepository = MutableLiveData<NetworkState<String?>>()
    val movieTrailerRepository: LiveData<NetworkState<String?>> = _movieTrailerRepository

    fun getDetailMovie(id: Int) {
        val app = getApplication<App>()
        _movieDetailRepository.postValue(NetworkState.Loading())
        viewModelScope.launch {
            try {
                if (Utils.isConnect(app)) {
                    val response = repository.movieDetail(id)
                    if (response.isSuccessful) {
                        response.body().let {
                            _movieDetailRepository.postValue(NetworkState.Success(it!!))
                        }
                    }
                }
            } catch (t: Throwable) {
                _movieDetailRepository.postValue(NetworkState.Error(t))
            }
        }
    }

    fun getActor(id: Int) {
        val app = getApplication<App>()
        _movieActorRepository.postValue(NetworkState.Loading())
        viewModelScope.launch {
            try {
                if (Utils.isConnect(app)) {
                    val response = repository.getActors(id)
                    if (response.isSuccessful) {
                        response.body().let {
                            _movieActorRepository.postValue(NetworkState.Success(it!!))
                        }
                    }
                }
            } catch (t: Throwable) {
                _movieActorRepository.postValue(NetworkState.Error(t))
            }
        }
    }

    fun getTrailer(id: Int) {
        val app = getApplication<App>()
        _movieTrailerRepository.postValue(NetworkState.Loading())
        viewModelScope.launch {
            try {
                if (Utils.isConnect(app)) {
                    val response = repository.getTrailer(id)
                    if (response.isSuccessful) {
                        response.body().let {
                            var result:String? = null
                            if (it?.results!=null) {
                                it.results.forEach {t ->
                                    if (t.type=="Trailer") {
                                        result = "https://www.youtube.com/watch?v=${t.key}"
                                    }
                                }
                            }
                            _movieTrailerRepository.postValue(NetworkState.Success(result))
                        }
                    }
                }
            } catch (t: Throwable) {
                _movieTrailerRepository.postValue(NetworkState.Error(t))
            }
        }
    }
    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }
}