package com.example.demokmp.openAi

import kotlinx.serialization.Serializable

@Serializable
data class OpenAiMessage(
    val role: String,
    val content: String
)

@Serializable
data class OpenAiRequest(
    val model: String,
    val messages: List<OpenAiMessage>,
    val temperature: Double
)

@Serializable
data class OpenAiResponse(
    val id: String,
    val `object`: String,
    val created: Long,
    val model: String,
    val usage: UsageDetails,
    val choices: List<Choice>
) {
    @Serializable
    data class UsageDetails(
        val promptTokens: Int,
        val completionTokens: Int,
        val totalTokens: Int,
        val completionTokensDetails: CompletionTokensDetails
    )

    @Serializable
    data class CompletionTokensDetails(
        val reasoningTokens: Int,
        val acceptedPredictionTokens: Int,
        val rejectedPredictionTokens: Int
    )

    @Serializable
    data class Choice(
        val message: Message,
        val logprobs: String?,
        val finishReason: String,
        val index: Int
    ) {
        @Serializable
        data class Message(
            val role: String,
            val content: String
        )
    }
}