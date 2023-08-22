package com.mdlb.basicapplication.databases

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class RegisterViewModel (private val repository: RegisterRepository) : ViewModel() {
    val allRegisters: LiveData<List<Register>> = repository.allRegisters.asLiveData()
    fun insert(register: Register) = viewModelScope.launch {
        repository.insert(register)
    }
}

class WordViewModelFactory(private val repository: RegisterRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RegisterViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return RegisterViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
