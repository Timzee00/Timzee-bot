package com.timzee.bot.assistant.voice

import android.app.Service
import android.content.Intent
import android.os.IBinder

class VoiceWakeService : Service() {
    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        // TODO: Wire in on-device hotword detection + TTS response.
        return START_STICKY
    }
}
