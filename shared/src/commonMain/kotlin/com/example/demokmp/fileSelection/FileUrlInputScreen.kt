package com.example.demokmp.fileSelection

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

@Composable
fun FileUrlInputScreen(
    viewModel: FileUrlInputViewModel = androidx.lifecycle.viewmodel.compose.viewModel(),
    onNavigateToRequest: (String) -> Unit
) {
    val userName by viewModel.userName.collectAsState()
    val repo by viewModel.repo.collectAsState()
    val path by viewModel.path.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val errorMessage by viewModel.errorMessage.collectAsState()

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            InputSection(
                label = "Enter user name",
                hint = "User name",
                valueState = userName,
                onValueChange = { viewModel.onUserNameChange(it) }
            )
            InputSection(
                label = "Enter repo",
                hint = "Repo name",
                valueState = repo,
                onValueChange = { viewModel.onRepoChange(it) }
            )
            InputSection(
                label = "Enter path to specific file",
                hint = "Path to file (e.g., /master/main.java)",
                valueState = path,
                onValueChange = { viewModel.onPathChange(it) }
            )

            errorMessage?.let { message ->
                Text(
                    text = message,
                    color = MaterialTheme.colorScheme.error,
                    modifier = Modifier.padding(8.dp)
                )
            }

            Button(
                onClick = {
                    viewModel.viewModelScope.launch {
                        viewModel.handleSubmit(onNavigateToRequest)
                    }
                },
                enabled = userName.isNotBlank() && repo.isNotBlank() && path.isNotBlank() && !isLoading
            ) {
                if (isLoading) {
                    CircularProgressIndicator(
                        color = MaterialTheme.colorScheme.onPrimary,
                        modifier = Modifier.size(16.dp)
                    )
                } else {
                    Text(text = "Submit")
                }
            }
        }
    }
}


