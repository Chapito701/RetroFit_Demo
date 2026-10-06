package com.example.retrofit

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.retrofit.ui.theme.RetroFitTheme

class MainActivity : ComponentActivity() {
    // Instanciar el ViewModel
    private val viewModel: PostViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RetroFitTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    PostListScreen(
                        viewModel = viewModel,
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun PostListScreen(viewModel: PostViewModel, modifier: Modifier = Modifier) {
    val posts = viewModel.postState
    val isLoading = viewModel.isLoading

    Box(modifier = modifier.fillMaxSize()) {
        if (isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        } else {
            // LazyColumn garantiza un desplazamiento fluido cargando solo lo visible
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(posts) { post ->
                    PostItem(post = post)
                }
            }
        }
    }
}

@Composable
fun PostItem(post: DataPost) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = post.title, style = MaterialTheme.typography.titleMedium)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = post.body, style = MaterialTheme.typography.bodyMedium)
        }
    }
}