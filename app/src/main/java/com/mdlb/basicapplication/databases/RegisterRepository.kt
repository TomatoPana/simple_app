package com.mdlb.basicapplication.databases

import androidx.annotation.WorkerThread
import kotlinx.coroutines.flow.Flow

class RegisterRepository(private val registerDao: RegisterDao) {
    val allRegisters: Flow<List<Register>> = registerDao.getAllRegisters()
    @WorkerThread
    suspend fun insert(register: Register) {
        registerDao.insert(register)
    }

}