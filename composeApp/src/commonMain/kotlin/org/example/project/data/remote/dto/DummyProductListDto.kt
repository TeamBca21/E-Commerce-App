package org.example.project.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class DummyProductListDto(
    val products: List<DummyProductDto>,
    val total: Int,
    val skip: Int,
    val limit: Int
)
