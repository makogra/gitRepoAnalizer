package com.example.demokmp.ui;

import org.junit.Assert.assertEquals
import org.junit.Test;

class RawFileScreenAndroidTest {

    @Test
    fun parseMarkdownWithSyntaxHighlighting() {
        val input = getExampleResponse()
        val parsed = parseMarkdownWithSyntaxHighlighting(input)

        val expectedBlocks = listOf(
                TextBlock("Missed edge case"),
                CodeBlock("""
                android {
                defaultConfig {
                minSdk = 24
                targetSdk = 34 // Add this line
                }
                }
            """.trimIndent()),
                TextBlock("It's a good practice to specify the `targetSdk` version in the `defaultConfig` block. This ensures that the app targets the correct Android version and takes advantage of the latest features and optimizations while still maintaining compatibility with the specified `minSdk`.")
        )

        assertEquals(expectedBlocks, parsed)
    }

    private fun getExampleResponse(): String {
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
}