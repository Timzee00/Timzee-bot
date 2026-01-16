package com.timzee.bot.assistant.owner

class OwnerModeGuard(
    private val ownerPassphrase: String = "timzee-owner-override",
) {
    fun detectOwnerMode(prompt: String): OwnerState {
        val isOwner = prompt.contains(ownerPassphrase, ignoreCase = true)
        return OwnerState(isOwner)
    }

    fun stripOwnerPassphrase(prompt: String): String {
        return prompt.replace(ownerPassphrase, "", ignoreCase = true).trim()
    }
}

data class OwnerState(
    val isOwner: Boolean,
)
