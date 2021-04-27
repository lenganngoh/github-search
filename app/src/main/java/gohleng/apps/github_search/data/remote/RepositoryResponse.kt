package gohleng.apps.github_search.data.remote

import gohleng.apps.github_search.data.model.Repository

data class RepositoryResponse(
    val items: List<Repository>
)