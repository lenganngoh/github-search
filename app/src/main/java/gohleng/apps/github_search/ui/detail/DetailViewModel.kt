package gohleng.apps.github_search.ui.detail

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import gohleng.apps.github_search.data.model.Repository
import gohleng.apps.github_search.data.repository.RepoRepository

class DetailViewModel @ViewModelInject constructor(private val repo: RepoRepository): ViewModel() {

    fun getRepository(id: Long): LiveData<Repository> {
        return repo.getRepository(id)
    }
}