package com.timzee.bot.assistant.search

import com.timzee.bot.assistant.core.AssistantTool
import com.timzee.bot.assistant.core.ToolEvent
import com.timzee.bot.assistant.documents.DocumentBundle

class WebSearchTool(
    private val searchManager: SearchManager,
) : AssistantTool {
    override fun shouldRun(prompt: String, documents: DocumentBundle, ownerModeEnabled: Boolean): Boolean {
        return prompt.contains("search", ignoreCase = true) || prompt.contains("web", ignoreCase = true)
    }

    override fun run(prompt: String, documents: DocumentBundle, ownerModeEnabled: Boolean): ToolEvent {
        val results = searchManager.search(prompt)
        return ToolEvent(
            name = "web-search",
            summary = results.summary,
            payload = results.metadata,
        )
    }
}
