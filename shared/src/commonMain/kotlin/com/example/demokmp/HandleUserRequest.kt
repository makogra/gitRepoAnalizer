package com.example.demokmp

import com.example.demokmp.keys.getGithubToken
import com.example.demokmp.keys.getOpenAiApiKey
import com.example.demokmp.keys.getOpenAiOrganizationID
import com.example.demokmp.keys.getOpenAiProjectID
import com.example.demokmp.openAi.OpenAiMessage
import com.example.demokmp.openAi.OpenAiRequest
import com.example.demokmp.openAi.OpenAiResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.cio.CIO
import io.ktor.client.request.get
import io.ktor.client.request.headers
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.bodyAsText
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.contentType
import io.ktor.http.isSuccess
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

class HandleUserRequest {

    suspend fun request(onNavigateToRequest: (String) -> Unit, userName: String, repo: String, path: String) {
        println("Submitted name: ${userName}, repo ${repo}, path: ${path}")
        val url: String = getUrl(userName, repo, path)
        val file:String = getRawFile(url)
        try {
            val response = sendToGPT(file = file)
            println("Response: ${response.choices.firstOrNull()?.message?.content}")
            response.choices.firstOrNull()?.message?.content?.let { onNavigateToRequest(it) }
        } catch (e: Exception) {
            println("Error: ${e.message}")
            onNavigateToRequest(file)
        }

//        onNavigateToRequest(getExampleResponse())
    }

    private suspend fun getRawFile(url: String): String {
        val client = HttpClient(CIO)
        val githubToken = getGithubToken()

        try {
            val response = client.get(url) {
                headers{
                    append("Authorization", "Bearer $githubToken")
                    append("X-GitHub-Api-Version", "2022-11-28")
                    append("Accept", "application/vnd.github.raw+json")
                    append("User-Agent", "fileAnalyzer")
                }
            }

            val body = response.bodyAsText()
            if (response.status.isSuccess()) {
                return body
            } else {
                throw Exception("GitHub API Error: ${response.status} - $body")
            }
        } catch (e: Exception) {
            throw Exception("Failed to call GitHub API: ${e.message}")
        } finally {
            client.close()
        }
    }

    private fun getUrl(userName: String, repo: String, path: String): String {
//        val baseURL: String = "https://raw.githubusercontent.com"
        val baseURL: String = "https://api.github.com/repos"
        val result: String = "$baseURL/$userName/$repo/contents/$path"
        println("result from getUrl= ${result}")
        return result
    }

    private suspend fun sendToGPT(
        command: String = """
            You are a helpful assistant reviewing code and providing feedback. When identifying issues or making recommendations, respond in the following format:
            1. **Type of recommendation/bug**: (e.g., rename, race condition, missed edge case)
            2. **Code snippet**: Provide the code fragment with the implementation of the solution, focusing on only the changed part to minimize token usage.
            3. **Explanation**: (optional, depending on the issue, e.g., skip for renaming) Provide a brief explanation of why this change is necessary.
        """.trimIndent(),
        file: String,
    ): OpenAiResponse {
        val client = HttpClient(CIO) {
            install(io.ktor.client.plugins.contentnegotiation.ContentNegotiation) {
                json(Json {
                    ignoreUnknownKeys = true
                    prettyPrint = true
                    isLenient = true
                })
            }
        }

        try {
            val apiKey = getOpenAiApiKey()
            val orgId = getOpenAiOrganizationID()
            val projectId = getOpenAiProjectID()

            val request = OpenAiRequest(
                model = "gpt-4o-mini",
                messages = listOf(
                    OpenAiMessage(role = "developer", content = command),
                    OpenAiMessage(role = "user", content = "Here is the code to review:\n$file")
                ),
                temperature = 0.7
            )

            val response: HttpResponse = client.post("https://api.openai.com/v1/chat/completions") {
                contentType(ContentType.Application.Json)
                headers {
                    append(HttpHeaders.Authorization, "Bearer $apiKey")
                    append("OpenAI-Organization", orgId)
                    append("OpenAI-Project", projectId)
                }
                setBody(request)
            }

            if (response.status.isSuccess()) {
                return response.body<OpenAiResponse>()
            } else {
                val errorBody: String = response.bodyAsText()
                throw Exception("API Error: ${response.status} - $errorBody")
            }
        } catch (e: Exception) {
            throw Exception("Failed to call OpenAI API: ${e.message}")
        } finally {
            client.close()
        }
    }
}