package com.example.demokmp.ui

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun FileUrlInputScreen() {
    var fileUrl by remember { mutableStateOf(TextFieldValue("")) }
    val hint = "Example: https://github.com/user/repo/blob/main/src/Main.kt"

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = "Enter GitHub File URL",
                style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold),
                color = Color.Black
            )
            Box(
                modifier = Modifier
                    .width(400.dp)
                    .height(56.dp)
                    .padding(horizontal = 8.dp)
                    .border(1.dp, Color.Gray)
                    .padding(8.dp)
            ) {
                if (fileUrl.text.isEmpty()) {
                    Text(
                        text = hint,
                        style = TextStyle(color = Color.Gray, fontSize = 14.sp),
                    )
                }
                BasicTextField(
                    value = fileUrl,
                    onValueChange = { fileUrl = it },
                    modifier = Modifier.fillMaxSize(),
                    textStyle = TextStyle(fontSize = 14.sp, color = Color.Black)
                )
            }
            Button(
                onClick = { println("Submitted URL: ${fileUrl.text}") },
                enabled = fileUrl.text.isNotEmpty()
            ) {
                Text(text = "Submit")
            }
        }
    }
}