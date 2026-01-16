package com.timzee.bot.assistant.automation

import android.accessibilityservice.AccessibilityService
import android.view.accessibility.AccessibilityEvent

class AutomationAccessibilityService : AccessibilityService() {
    override fun onAccessibilityEvent(event: AccessibilityEvent?) {
        // TODO: Implement app automation with explicit user consent.
    }

    override fun onInterrupt() {
        // Required override for AccessibilityService.
    }
}
