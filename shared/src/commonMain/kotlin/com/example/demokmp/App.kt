package com.example.demokmp

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.example.demokmp.ui.FileUrlInputScreen
import com.example.demokmp.ui.RawFileScreen

//import androidx.navigation.NavHostController

sealed class Screen {
    data object Home: Screen()
    data class FileAnalyzer(val file: String) : Screen()

//    object Details : Screen()
}

@Composable
fun App() {
    var currentScreen by remember { mutableStateOf<Screen>(Screen.Home) }

    when (val screen = currentScreen) {
        is Screen.Home -> FileUrlInputScreen(onNavigateToRequest = { file -> currentScreen = Screen.FileAnalyzer(file) })
        is Screen.FileAnalyzer -> RawFileScreen(
            fileContent = screen.file
        )
//        is Screen.Details -> DetailScreen(onNavigateBack = { currentScreen = Screen.Home })
    }
}

@Composable
fun HomeScreen(onNavigateToDetails: () -> Unit) {
    Column {
        Text("Home Screen", style = MaterialTheme.typography.headlineSmall)
        Button(onClick = onNavigateToDetails) {
            Text("Go to Details")
        }
    }
}

@Composable
fun DetailScreen(onNavigateBack: () -> Unit) {
    Column {
        Text("Detail Screen", style = MaterialTheme.typography.headlineSmall)
        Button(onClick = onNavigateBack) {
            Text("Back to Home")
        }
    }
}

//@Composable
//fun GitHubFileAnalyzerApp(
//    navController: NavHostController
//) {
//
//}