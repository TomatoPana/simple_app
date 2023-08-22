package com.mdlb.basicapplication.databases

import androidx.lifecycle.LiveData
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

interface RegisterDao {
    @Query("SELECT * FROM registers ORDER BY id DESC")
    fun getAllRegisters(): Flow<List<Register>>

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insert(register: Register)

    @Query("DELETE FROM registers")
    suspend fun deleteAll()
}