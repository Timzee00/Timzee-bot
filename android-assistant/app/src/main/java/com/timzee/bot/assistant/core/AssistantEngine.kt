package com.timzee.bot.assistant.core

import com.timzee.bot.assistant.documents.DocumentBundle
import com.timzee.bot.assistant.model.LocalModelRuntime
import com.timzee.bot.assistant.owner.OwnerModeGuard

class AssistantEngine(
    private val modelRuntime: LocalModelRuntime,
    private val ownerModeGuard: OwnerModeGuard,
    private val toolRouter: ToolRouter,
) {
    fun handleRequest(request: AssistantRequest): AssistantResponse {
        val ownerState = ownerModeGuard.detectOwnerMode(request.prompt)
        val sanitizedPrompt = ownerModeGuard.stripOwnerPassphrase(request.prompt)

        val toolPlan = toolRouter.planTools(
            prompt = sanitizedPrompt,
            documents = request.documents,
            ownerModeEnabled = ownerState.isOwner,
        )

        val responseText = modelRuntime.generate(
            prompt = toolPlan.augmentedPrompt,
            documents = request.documents,
            toolResults = toolPlan.toolResults,
            ownerModeEnabled = ownerState.isOwner,
        )

        return AssistantResponse(
            text = responseText,
            ownerModeEnabled = ownerState.isOwner,
            toolEvents = toolPlan.toolResults,
        )
    }
}

data class AssistantRequest(
    val prompt: String,
    val documents: DocumentBundle = DocumentBundle(),
)

data class AssistantResponse(
    val text: String,
    val ownerModeEnabled: Boolean,
    val toolEvents: List<ToolEvent> = emptyList(),
)
