package edu.skku.cs.project

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [dogdata::class], version = 1,exportSchema = true)
abstract class dogdatabase : RoomDatabase() {
    abstract fun dogDao() : dogDAO
}
