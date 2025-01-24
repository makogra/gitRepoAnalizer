package com.example.demokmp.fileSelection

import androidx.lifecycle.ViewModel
import com.example.demokmp.HandleUserRequest
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class FileUrlInputViewModel : ViewModel() {
    private val _userName = MutableStateFlow("")
    val userName: StateFlow<String> = _userName

    private val _repo = MutableStateFlow("")
    val repo: StateFlow<String> = _repo

    private val _path = MutableStateFlow("")
    val path: StateFlow<String> = _path

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage

    fun onUserNameChange(newValue: String) {
        _userName.value = newValue
    }

    fun onRepoChange(newValue: String) {
        _repo.value = newValue
    }

    fun onPathChange(newValue: String) {
        _path.value = newValue
    }

    suspend fun handleSubmit(onNavigateToRequest: (String) -> Unit) {
        _isLoading.value = true
        _errorMessage.value = null
        try {
            HandleUserRequest().request(
                onNavigateToRequest,
                _userName.value,
                _repo.value,
                _path.value
            )
        } catch (e: Exception) {
            _errorMessage.value = "Error: ${e.message}"
        } finally {
            _isLoading.value = false
        }
    }
}