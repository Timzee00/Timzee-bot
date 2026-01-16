package com.timzee.bot.assistant.knowledge

import android.content.Context

class UserMemoryStore(private val context: Context) {
    fun appendInteraction(prompt: String, response: String) {
        // TODO: Persist interaction summaries for on-device personalization.
    }

    fun loadInteractionSummaries(): List<String> {
        // TODO: Return a capped list of local summaries.
        return emptyList()
    }
}
