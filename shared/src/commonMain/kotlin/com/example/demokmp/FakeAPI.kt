package com.example.demokmp

import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsText

class FakeAPI {
    private val client = HttpClient()

    suspend fun getFakeApiResponse(): String {
        val response: String = client.get("https://jsonplaceholder.typicode.com/posts/1").bodyAsText()
        println("fetching fake api")
        println("Response:")
        println(response)
        return response
    }
}