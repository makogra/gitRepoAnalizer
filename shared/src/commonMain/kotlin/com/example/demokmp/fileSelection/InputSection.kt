package com.example.demokmp.fileSelection

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

//@Composable
//fun InputSection(
//    label: String = "Input",
//    hint: String = "",
//    valueState: MutableState<TextFieldValue>
//) {
//    Text(
//        text = label,
//        style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold),
//        color = MaterialTheme.colorScheme.secondary
//    )
//    Box(
//        modifier = Modifier
//            .width(400.dp)
//            .height(56.dp)
//            .padding(horizontal = 8.dp)
//            .border(1.dp, MaterialTheme.colorScheme.tertiary)
//            .padding(8.dp)
//    ) {
//        if (valueState.value.text.isEmpty()) {
//            Text(
//                text = hint,
//                style = TextStyle(color = MaterialTheme.colorScheme.tertiary, fontSize = 14.sp),
//            )
//        }
//        BasicTextField(
//            value = valueState.value,
//            onValueChange = { valueState.value = it },
//            modifier = Modifier.fillMaxSize(),
//            textStyle = TextStyle(fontSize = 14.sp, color = MaterialTheme.colorScheme.secondary)
//        )
//    }
//}

@Composable
fun InputSection(
    label: String,
    hint: String,
    valueState: MutableState<TextFieldValue>,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold),
            color = MaterialTheme.colorScheme.primary
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .background(
                    color = MaterialTheme.colorScheme.secondaryContainer,
                    shape = RoundedCornerShape(8.dp)
                )
                .padding(horizontal = 12.dp),
            contentAlignment = Alignment.CenterStart // Ensures that all content is vertically centered
        ) {
            // Stack hint and text inside the box
            if (valueState.value.text.isEmpty()) {
                Text(
                    text = hint,
                    style = MaterialTheme.typography.bodyMedium.copy(
                        color = MaterialTheme.colorScheme.onSecondaryContainer
                    )
                )
            }
            BasicTextField(
                value = valueState.value,
                onValueChange = { valueState.value = it },
                modifier = Modifier.fillMaxWidth(), // Covers the entire width of the box
                textStyle = MaterialTheme.typography.bodyMedium.copy(
                    color = MaterialTheme.colorScheme.onSecondaryContainer,
                    lineHeight = 20.sp // Ensures vertical alignment matches hint
                ),
                singleLine = true
            )
        }
    }
}