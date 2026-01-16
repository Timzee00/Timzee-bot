package com.timzee.bot.assistant.core

import com.timzee.bot.assistant.documents.DocumentBundle

class ToolRouter(
    private val tools: List<AssistantTool>,
) {
    fun planTools(
        prompt: String,
        documents: DocumentBundle,
        ownerModeEnabled: Boolean,
    ): ToolPlan {
        val toolResults = tools.flatMap { tool ->
            if (tool.shouldRun(prompt, documents, ownerModeEnabled)) {
                listOf(tool.run(prompt, documents, ownerModeEnabled))
            } else {
                emptyList()
            }
        }

        val augmentedPrompt = buildString {
            append(prompt)
            if (toolResults.isNotEmpty()) {
                append("\n\nTool results:\n")
                toolResults.forEach { result ->
                    append("- ")
                    append(result.summary)
                    append('\n')
                }
            }
        }

        return ToolPlan(
            augmentedPrompt = augmentedPrompt,
            toolResults = toolResults,
        )
    }
}

data class ToolPlan(
    val augmentedPrompt: String,
    val toolResults: List<ToolEvent>,
)

interface AssistantTool {
    fun shouldRun(prompt: String, documents: DocumentBundle, ownerModeEnabled: Boolean): Boolean
    fun run(prompt: String, documents: DocumentBundle, ownerModeEnabled: Boolean): ToolEvent
}

data class ToolEvent(
    val name: String,
    val summary: String,
    val payload: Map<String, String> = emptyMap(),
)
