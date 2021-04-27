package gohleng.apps.github_search.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "repository")
data class Repository(
    @PrimaryKey val id: Long = 0,
    val name: String = "",
    val owner: Owner? = null,
    val html_url: String = "" ,
    val description: String? = ""
)