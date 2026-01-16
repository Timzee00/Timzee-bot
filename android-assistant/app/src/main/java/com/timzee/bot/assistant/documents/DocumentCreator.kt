package com.timzee.bot.assistant.documents

interface DocumentCreator {
    fun createPdf(title: String, body: String): CreatedDocument
}

data class CreatedDocument(
    val displayName: String,
    val mimeType: String,
    val contentUri: String,
)
