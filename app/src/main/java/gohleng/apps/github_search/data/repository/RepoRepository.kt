package gohleng.apps.github_search.data.repository

import androidx.lifecycle.LiveData
import gohleng.apps.github_search.data.local.RepositoryDao
import gohleng.apps.github_search.data.model.Repository
import gohleng.apps.github_search.data.remote.RepositoryResponse
import gohleng.apps.github_search.data.remote.RepositoryService
import io.reactivex.Single
import retrofit2.Response
import javax.inject.Inject

class RepoRepository @Inject constructor(
    private val remote: RepositoryService,
    private val local: RepositoryDao
) {

    fun search(term: String): Single<Response<RepositoryResponse>> {
        return remote.search(term)
    }

    fun clear() {
        local.clear()
    }

    fun insert(repository: Repository) {
        local.insert(repository)
    }

    fun getAll(): LiveData<List<Repository>> = local.getAll()

    fun getRepository(id: Long): LiveData<Repository> = local.getRepository(id)
}