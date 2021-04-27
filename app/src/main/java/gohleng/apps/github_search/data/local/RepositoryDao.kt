package gohleng.apps.github_search.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import gohleng.apps.github_search.data.model.Repository

@Dao
interface RepositoryDao {
    @Query("SELECT * FROM repository")
    fun getAll() : LiveData<List<Repository>>

    @Query("SELECT * FROM repository WHERE id = :id")
    fun getRepository(id: Long): LiveData<Repository>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(item: Repository)

    @Query("DELETE from repository")
    fun clear()
}