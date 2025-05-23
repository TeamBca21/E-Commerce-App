package org.example.project.model

import kotlinx.serialization.Serializable

@Serializable
data class Product(
    val id: String,
    val name: String,
    val shortDescription: String,
    val imageUrl: String,
    val price: Double
)
