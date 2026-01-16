package com.timzee.bot.assistant.model

import com.timzee.bot.assistant.core.ToolEvent
import com.timzee.bot.assistant.documents.DocumentBundle

interface LocalModelRuntime {
    fun generate(
        prompt: String,
        documents: DocumentBundle,
        toolResults: List<ToolEvent>,
        ownerModeEnabled: Boolean,
    ): String
}

class BasicReasoningRuntime : LocalModelRuntime {
    override fun generate(
        prompt: String,
        documents: DocumentBundle,
        toolResults: List<ToolEvent>,
        ownerModeEnabled: Boolean,
    ): String {
        val ownerBanner = if (ownerModeEnabled) "Owner mode enabled." else "Owner mode disabled."
        val docCount = documents.images.size + documents.pdfs.size + documents.officeDocs.size + documents.textDocs.size
        val toolSummary = if (toolResults.isEmpty()) {
            "No tools were used."
        } else {
            toolResults.joinToString(separator = "\n") { "- ${it.name}: ${it.summary}" }
        }

        val responseHeader = when {
            prompt.contains("code", ignoreCase = true) -> "Here is a structured coding response outline:"
            prompt.contains("plan", ignoreCase = true) -> "Here is a step-by-step plan:"
            else -> "Here is a helpful response:"
        }

        return """
            |$ownerBanner
            |
            |$responseHeader
            |1) Clarify the goal and constraints.
            |2) Gather inputs (documents: $docCount).
            |3) Apply offline rules or tool results.
            |4) Produce a clear, human-friendly answer.
            |
            |Tool results:
            |$toolSummary
            |
            |Prompt:
            |$prompt
            |
            |Note: This is a lightweight rule-based runtime. Replace with a full offline model for richer answers.
        """.trimMargin()
    }
}
