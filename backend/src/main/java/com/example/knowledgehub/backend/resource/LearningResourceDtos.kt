package com.example.knowledgehub.backend.resource

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull

data class LearningResourceResponse(
    val id: Long?,
    val categoryId: Long?,
    val categoryCode: String,
    val title: String,
    val shortDescription: String,
    val resourceUrl: String,
    val iconUrl: String?,
    val orderIndex: Int?,
)

data class LearningResourceCreateRequest(
    @field:NotNull
    val categoryId: Long,

    @field:NotBlank
    val title: String,

    @field:NotBlank
    val shortDescription: String,

    @field:NotBlank
    val resourceUrl: String,

    val iconUrl: String? = null,
    val orderIndex: Int? = null,
)

data class LearningResourceUpdateRequest(
    val categoryId: Long? = null,
    val title: String? = null,
    val shortDescription: String? = null,
    val resourceUrl: String? = null,
    val iconUrl: String? = null,
    val orderIndex: Int? = null,
)

fun LearningResource.toResponse() = LearningResourceResponse(
    id = id,
    categoryId = category.id,
    categoryCode = category.code,
    title = title,
    shortDescription = shortDescription,
    resourceUrl = resourceUrl,
    iconUrl = iconUrl,
    orderIndex = orderIndex,
)
