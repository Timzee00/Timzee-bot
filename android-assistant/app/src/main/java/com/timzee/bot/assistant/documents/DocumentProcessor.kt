package com.timzee.bot.assistant.documents

interface DocumentProcessor {
    val supportedMimeTypes: Set<String>

    fun extractText(document: AssistantDocument): DocumentExtraction
}

data class DocumentExtraction(
    val documentId: String,
    val extractedText: String,
    val warnings: List<String> = emptyList(),
)
