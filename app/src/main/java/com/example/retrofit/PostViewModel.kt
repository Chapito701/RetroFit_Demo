package com.example.retrofit

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class PostViewModel : ViewModel() {

    // Cambiar de DataPost? a List<DataPost>
    var postState by mutableStateOf<List<DataPost>>(emptyList())
        private set

    var isLoading by mutableStateOf(value = false)
        private set

    init {
        fetchPosts()
    }

    private fun fetchPosts() {
        viewModelScope.launch {
            isLoading = true
            try {
                // Llamar al nuevo método getPosts()
                postState = ApiService.instance.getPosts()
            } catch (e: Exception) {
                // Manejar error (puedes agregar un Log o un estado de error)
            } finally {
                isLoading = false
            }
        }
    }
}