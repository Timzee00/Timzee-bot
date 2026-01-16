package com.timzee.bot.assistant.search

import com.timzee.bot.assistant.core.SafetyPolicy

class SearchManager(
    private val client: WebSearchClient,
    private val safetyPolicy: SafetyPolicy,
) {
    fun search(query: String): WebSearchResult {
        if (!safetyPolicy.canUseOnlineSearch()) {
            return WebSearchResult(
                summary = "Online search is disabled by policy.",
                metadata = mapOf("source" to "disabled"),
            )
        }

        return client.search(query)
    }
}
