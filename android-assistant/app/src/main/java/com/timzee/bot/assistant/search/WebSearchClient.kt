package com.timzee.bot.assistant.search

data class WebSearchResult(
    val summary: String,
    val metadata: Map<String, String> = emptyMap(),
)

interface WebSearchClient {
    fun search(query: String): WebSearchResult
}
