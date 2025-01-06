package com.example.demokmp.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.example.demokmp.HandleUserRequest

@Composable
fun FileUrlInputScreen() {
    var fileUrl by remember { mutableStateOf(TextFieldValue("")) }
    var userName = remember { mutableStateOf(TextFieldValue("")) }
    var repo = remember { mutableStateOf(TextFieldValue("")) }
    var path = remember { mutableStateOf(TextFieldValue("")) }
    val hint = "Example: https://github.com/user/repo/blob/main/src/Main.kt"
    val hintUser = "User name"
    val hintRepo = "Repo name"
    val hintPath = "Path to file ex. /master/main.java"

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
                hint = hintUser,
                valueState = userName
            )
            InputSection(
                label = "Enter repo",
                hint = hintRepo,
                valueState = repo
            )
            InputSection(
                label = "Enter path to specific file",
                hint = hintPath,
                valueState = path
            )
            Button(
                onClick = { HandleUserRequest().request(userName.value.text, repo.value.text, path.value.text) },
                enabled = areInputsProvided(listOf(userName, repo, path))
            ) {
                Text(text = "Submit")
            }
        }
    }
}

fun areInputsProvided(listOfInputs: List<MutableState<TextFieldValue>>): Boolean {
    for (input in listOfInputs) {
        if (input.value.text.isEmpty()) {
            return false
        }
    }
    return true
}



