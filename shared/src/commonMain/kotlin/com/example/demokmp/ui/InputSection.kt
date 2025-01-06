package com.example.demokmp.ui

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun InputSection(
    label: String = "Input",
    hint: String = "",
    valueState: MutableState<TextFieldValue>
) {
    Text(
        text = label,
        style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold),
        color = MaterialTheme.colorScheme.secondary
    )
    Box(
        modifier = Modifier
            .width(400.dp)
            .height(56.dp)
            .padding(horizontal = 8.dp)
            .border(1.dp, MaterialTheme.colorScheme.tertiary)
            .padding(8.dp)
    ) {
        if (valueState.value.text.isEmpty()) {
            Text(
                text = hint,
                style = TextStyle(color = MaterialTheme.colorScheme.tertiary, fontSize = 14.sp),
            )
        }
        BasicTextField(
            value = valueState.value,
            onValueChange = { valueState.value = it },
            modifier = Modifier.fillMaxSize(),
            textStyle = TextStyle(fontSize = 14.sp, color = MaterialTheme.colorScheme.secondary)
        )
    }
}