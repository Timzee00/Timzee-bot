package com.timzee.bot.assistant.documents

data class DocumentBundle(
    val images: List<AssistantDocument> = emptyList(),
    val pdfs: List<AssistantDocument> = emptyList(),
    val officeDocs: List<AssistantDocument> = emptyList(),
    val textDocs: List<AssistantDocument> = emptyList(),
)

data class AssistantDocument(
    val id: String,
    val displayName: String,
    val mimeType: String,
    val contentUri: String,
)
