package gohleng.apps.github_search.ui.master

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import gohleng.apps.github_search.data.model.Repository
import gohleng.apps.github_search.data.repository.RepoRepository
import io.reactivex.schedulers.Schedulers

class MasterViewModel @ViewModelInject constructor(private val repo: RepoRepository) :
    ViewModel() {

    var isLoading: MutableLiveData<Boolean> = MutableLiveData(false)

    fun getAll(): LiveData<List<Repository>> {
        return repo.getAll()
    }

    fun search(term: String) {
        isLoading.value = true
        repo.search(term).subscribeOn(Schedulers.io())
            .subscribe { response, _ ->
                if (response.isSuccessful) {
                    repo.clear()
                    response.body()?.items?.forEach {
                        repo.insert(it)
                    }
                }
            }
    }
}