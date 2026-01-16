package com.timzee.bot.assistant.ui

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.timzee.bot.assistant.R
import com.timzee.bot.assistant.app.AssistantApplication
import com.timzee.bot.assistant.core.AssistantRequest
import com.timzee.bot.assistant.documents.DocumentBundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val promptInput = findViewById<EditText>(R.id.promptInput)
        val sendButton = findViewById<Button>(R.id.sendButton)
        val responseView = findViewById<TextView>(R.id.responseView)

        val engine = (application as AssistantApplication).assistantEngine

        sendButton.setOnClickListener {
            val prompt = promptInput.text?.toString().orEmpty()
            val response = engine.handleRequest(
                AssistantRequest(
                    prompt = prompt,
                    documents = DocumentBundle(),
                ),
            )
            responseView.text = response.text
        }
    }
}
