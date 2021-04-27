package gohleng.apps.github_search.data.remote

import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RepositoryService {
    @GET("/search/repositories")
    fun search(
        @Query("q") term: String
    ): Single<Response<RepositoryResponse>>
}