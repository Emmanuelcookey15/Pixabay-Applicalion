package com.payback.data.db

import androidx.room.*
import com.payback.domain.model.Hits
import io.reactivex.Observable


@Dao
interface HitDao {


    @get:Query("SELECT * FROM hit_table")
    val all: Observable<List<Hits>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(hits: List<Hits>)

    @Query("DELETE FROM hit_table WHERE id = :id")
    fun delete(id: Int?)

    @Query("DELETE FROM hit_table")
    fun deleteAll()

    @Update
    fun update(hits: Hits)

}