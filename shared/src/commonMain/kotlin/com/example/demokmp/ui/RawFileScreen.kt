package com.example.demokmp.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

sealed class MarkdownBlock
data class TextBlock(val text: String) : MarkdownBlock()
data class CodeBlock(val code: String) : MarkdownBlock()

fun parseMarkdownWithSyntaxHighlighting(markdown: String): List<MarkdownBlock> {
    val blocks = mutableListOf<MarkdownBlock>()
    val lines = markdown.lines().map { it.trim() }
    val codeBuffer = StringBuilder()
    var inCodeBlock = false

    for (line in lines) {
        if (line.startsWith("```")) {
            if (inCodeBlock) {
                blocks.add(CodeBlock(codeBuffer.toString().trimEnd()))
                codeBuffer.clear()
            }
            inCodeBlock = !inCodeBlock
        } else if (inCodeBlock) {
            codeBuffer.appendLine(line)
        }  else {
            // Handle text blocks
            val text = when {
                line.startsWith("1.") -> line.removePrefix("1. **Type of recommendation/bug**:").trim()
                line.startsWith("2.") -> null // Skip this line, as it's just a label
                line.startsWith("3.") -> line.removePrefix("3. **Explanation**:").trim()
                else -> line.takeIf { it.isNotBlank() }
            }

            if (text != null) {
                blocks.add(TextBlock(text))
            }
        }
    }

    if (inCodeBlock && codeBuffer.isNotEmpty()) {
        blocks.add(CodeBlock(codeBuffer.toString().trimEnd()))
    }

    return blocks
}

@Composable
fun EnhancedRawFileScreen(
    fileContent: String = getExampleResponse()
) {
    val parsedMarkdown = remember(fileContent) { parseMarkdownWithSyntaxHighlighting(fileContent) }

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        color = MaterialTheme.colorScheme.background
    ) {
        SelectionContainer {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
            ) {
                parsedMarkdown.chunked(3).forEach { chunk ->
                    if (chunk.size == 3) {
                        MarkdownSection(
                            title = (chunk[0] as? TextBlock)?.text.orEmpty(),
                            code = (chunk[1] as? CodeBlock)?.code.orEmpty(),
                            explanation = (chunk[2] as? TextBlock)?.text.orEmpty()
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun MarkdownSection(title: String, code: String, explanation: String) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        color = MaterialTheme.colorScheme.surfaceVariant,
        shape = MaterialTheme.shapes.medium,
        tonalElevation = 4.dp
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            // Title
            Surface(
//                modifier = Modifier
//
//                    .padding(vertical = 8.dp, horizontal = 8.dp),
                border = BorderStroke(2.dp, MaterialTheme.colorScheme.secondary),
                color = MaterialTheme.colorScheme.surfaceVariant,
                shape = MaterialTheme.shapes.small
            ) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary
                    ),
                    modifier = Modifier.padding(8.dp)
                )
            }

            // Code block
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp),
                color = MaterialTheme.colorScheme.surface,
                shape = MaterialTheme.shapes.small
            ) {
                Text(
                    text = code,
                    style = TextStyle(
                        fontFamily = FontFamily.Monospace,
                        fontSize = 14.sp,
                        lineHeight = 20.sp,
                        color = MaterialTheme.colorScheme.onSurface
                    ),
                    modifier = Modifier.padding(8.dp)
                )
            }

            // Explanation paragraph
            Text(
                text = explanation,
                style = MaterialTheme.typography.bodyMedium.copy(
                    color = MaterialTheme.colorScheme.onBackground
                ),
                modifier = Modifier.padding(top = 8.dp)
            )
        }
    }
}


//@Composable
//fun RawFileScreen(fileContent: String) {
//
//
//    Surface(
//        modifier = Modifier
//            .fillMaxSize()
//            .padding(16.dp),
//        color = MaterialTheme.colorScheme.background
//    ) {
//        SelectionContainer {
//            Column(
//                modifier = Modifier
//                    .fillMaxSize()
//                    .verticalScroll(rememberScrollState())
//            ) {
//                // Parse Markdown and render it with syntax highlighting
//                MarkdownText(
//                    markdown = fileContent,
//                    modifier = Modifier.padding(8.dp)
//                )
//            }
//        }
//    }
//}
//
//@Composable
//fun MarkdownText(markdown: String, modifier: Modifier = Modifier) {
//    val parsedMarkdown = remember(markdown) { parseMarkdownWithSyntaxHighlighting(markdown) }
//
//    Column(modifier = modifier) {
//        for (block in parsedMarkdown) {
//            when (block) {
//                is TextBlock -> Text(
//                    text = block.text,
//                    style = MaterialTheme.typography.bodyMedium.copy(
//                        color = MaterialTheme.colorScheme.onBackground
//                    )
//                )
//                is CodeBlock -> Surface(
//                    modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
//                    color = MaterialTheme.colorScheme.surfaceVariant,
//                    shape = MaterialTheme.shapes.small
//                ) {
//                    Text(
//                        text = block.code,
//                        style = TextStyle(
//                            fontFamily = FontFamily.Monospace,
//                            fontSize = 14.sp,
//                            lineHeight = 20.sp,
//                            color = MaterialTheme.colorScheme.onSurface
//                        ),
//                        modifier = Modifier.padding(8.dp)
//                    )
//                }
//            }
//        }
//    }
//}

fun getExampleResponse(): String {
    return """1. **Type of recommendation/bug**: Missed edge case
        2. **Code snippet**: 
        ```kotlin
        android {
        defaultConfig {
        minSdk = 24
        targetSdk = 34 // Add this line
        }
        }
        ```
        3. **Explanation**: It's a good practice to specify the `targetSdk` version in the `defaultConfig` block. This ensures that the app targets the correct Android version and takes advantage of the latest features and optimizations while still maintaining compatibility with the specified `minSdk`.""".trimIndent()
}
