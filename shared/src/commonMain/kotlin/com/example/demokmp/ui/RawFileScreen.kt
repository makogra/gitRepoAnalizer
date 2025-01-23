package com.example.demokmp.ui

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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

sealed class MarkdownBlock
data class TextBlock(val text: String) : MarkdownBlock()
data class CodeBlock(val code: String) : MarkdownBlock()

fun parseMarkdownWithSyntaxHighlighting(markdown: String): List<MarkdownBlock> {
    val blocks = mutableListOf<MarkdownBlock>()
    val lines = markdown.lines()
    val codeBuffer = StringBuilder()
    var inCodeBlock = false

    for (line in lines) {
        if (line.startsWith("```")) {
            if (inCodeBlock) {
                blocks.add(CodeBlock(codeBuffer.toString()))
                codeBuffer.clear()
            }
            inCodeBlock = !inCodeBlock
        } else if (inCodeBlock) {
            codeBuffer.appendLine(line)
        } else {
            blocks.add(TextBlock(line))
        }
    }
    return blocks
}

@Composable
fun RawFileScreen(fileContent: String) {
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
                // Parse Markdown and render it with syntax highlighting
                MarkdownText(
                    markdown = fileContent,
                    modifier = Modifier.padding(8.dp)
                )
            }
        }
    }
}

@Composable
fun MarkdownText(markdown: String, modifier: Modifier = Modifier) {
    val parsedMarkdown = remember(markdown) { parseMarkdownWithSyntaxHighlighting(markdown) }

    Column(modifier = modifier) {
        for (block in parsedMarkdown) {
            when (block) {
                is TextBlock -> Text(
                    text = block.text,
                    style = MaterialTheme.typography.bodyMedium.copy(
                        color = MaterialTheme.colorScheme.onBackground
                    )
                )
                is CodeBlock -> Surface(
                    modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
                    color = MaterialTheme.colorScheme.surfaceVariant,
                    shape = MaterialTheme.shapes.small
                ) {
                    Text(
                        text = block.code,
                        style = TextStyle(
                            fontFamily = FontFamily.Monospace,
                            fontSize = 14.sp,
                            lineHeight = 20.sp,
                            color = MaterialTheme.colorScheme.onSurface
                        ),
                        modifier = Modifier.padding(8.dp)
                    )
                }
            }
        }
    }
}
