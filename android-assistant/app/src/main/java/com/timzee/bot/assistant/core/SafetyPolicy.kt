package com.timzee.bot.assistant.core

class SafetyPolicy(
    private val allowOnlineSearch: Boolean = true,
) {
    fun canUseOnlineSearch(): Boolean = allowOnlineSearch

    fun redactSensitiveInput(prompt: String): String {
        return prompt.trim()
    }
}
