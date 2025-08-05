package edu.skku.cs.project

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface dogDAO {
    @Query("SELECT * FROM dog")
    fun getAll(): List<dogdata>

    @Insert
    fun insertdoglist(dog: dogdata)

    @Delete
    fun deletedoglist(dog: dogdata)

    @Query("DELETE  FROM dog")
    fun deleteAll()
}