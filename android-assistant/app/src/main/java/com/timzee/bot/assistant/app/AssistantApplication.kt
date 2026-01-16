package com.timzee.bot.assistant.app

import android.app.Application
import com.timzee.bot.assistant.core.AssistantEngine
import com.timzee.bot.assistant.core.SafetyPolicy
import com.timzee.bot.assistant.core.ToolRouter
import com.timzee.bot.assistant.model.BasicReasoningRuntime
import com.timzee.bot.assistant.owner.OwnerModeGuard
import com.timzee.bot.assistant.search.DuckDuckGoSearchClient
import com.timzee.bot.assistant.search.SearchManager
import com.timzee.bot.assistant.search.WebSearchTool

class AssistantApplication : Application() {
    lateinit var assistantEngine: AssistantEngine
        private set

    override fun onCreate() {
        super.onCreate()
        val safetyPolicy = SafetyPolicy(allowOnlineSearch = true)
        val searchManager = SearchManager(DuckDuckGoSearchClient(), safetyPolicy)
        val webSearchTool = WebSearchTool(searchManager)
        assistantEngine = AssistantEngine(
            modelRuntime = BasicReasoningRuntime(),
            ownerModeGuard = OwnerModeGuard(),
            toolRouter = ToolRouter(listOf(webSearchTool)),
        )
    }
}
