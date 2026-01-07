package com.vadejurisbr

data class LawModel(
    val id: String,
    val title: String,
    val fileName: String,
    val category: String // "Códigos", "Estatutos", "Leis Especiais", "Jurisprudência"
)
