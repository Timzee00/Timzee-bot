package com.timzee.bot.assistant.search

import java.net.HttpURLConnection
import java.net.URL
import java.net.URLEncoder

class DuckDuckGoSearchClient : WebSearchClient {
    override fun search(query: String): WebSearchResult {
        val encoded = URLEncoder.encode(query, "UTF-8")
        val url = URL("https://duckduckgo.com/html/?q=$encoded")
        val connection = url.openConnection() as HttpURLConnection
        connection.requestMethod = "GET"
        connection.connectTimeout = 5000
        connection.readTimeout = 5000

        return try {
            val response = connection.inputStream.bufferedReader().use { it.readText() }
            val snippet = response
                .replace(Regex("<[^>]*>"), " ")
                .replace(Regex("\\s+"), " ")
                .trim()
                .take(320)
            WebSearchResult(
                summary = "Web search snippet: $snippet",
                metadata = mapOf("source" to "duckduckgo-html"),
            )
        } catch (exception: Exception) {
            WebSearchResult(
                summary = "Web search failed: ${exception.message}",
                metadata = mapOf("source" to "duckduckgo-html"),
            )
        } finally {
            connection.disconnect()
        }
    }
}
