package br.iesb.mobile.icard.domain.dialogChat

data class Message(
    var text: String,
    val email: String,
    val sessionId: String
)
