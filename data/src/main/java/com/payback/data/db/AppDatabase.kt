package com.payback.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.payback.domain.model.Hits


@Database(entities = [Hits::class], version = 1, exportSchema = false)
abstract class AppDatabase: RoomDatabase() {

    abstract fun hitDao(): HitDao

}