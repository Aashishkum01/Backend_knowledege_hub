package com.example.knowledgehub.backend.category

import jakarta.validation.constraints.NotBlank

data class CategoryResponse(
    val id: Long?,
    val code: String,
    val name: String,
    val description: String?,
    val teacherName: String?,
    val teacherEmail: String?,
    val teacherPhone: String?,
    val teacherLinkedInUrl: String?,
)

data class CategoryCreateRequest(
    @field:NotBlank
    val code: String,

    @field:NotBlank
    val name: String,
    val description: String? = null,
    val teacherName: String? = null,
    val teacherEmail: String? = null,
    val teacherPhone: String? = null,
    val teacherLinkedInUrl: String? = null,
)

data class CategoryUpdateRequest(
    val code: String? = null,
    val name: String? = null,
    val description: String? = null,
    val teacherName: String? = null,
    val teacherEmail: String? = null,
    val teacherPhone: String? = null,
    val teacherLinkedInUrl: String? = null,
)

fun Category.toResponse() = CategoryResponse(
    id = id,
    code = code,
    name = name,
    description = description,
    teacherName = teacherName,
    teacherEmail = teacherEmail,
    teacherPhone = teacherPhone,
    teacherLinkedInUrl = teacherLinkedInUrl,
)

fun CategoryCreateRequest.toEntity() = Category(
    code = code,
    name = name,
    description = description,
    teacherName = teacherName,
    teacherEmail = teacherEmail,
    teacherPhone = teacherPhone,
    teacherLinkedInUrl = teacherLinkedInUrl,
)

fun Category.applyUpdate(update: CategoryUpdateRequest) {
    update.code?.let { this.code = it }
    update.name?.let { this.name = it }
    if (update.description != null) this.description = update.description
    if (update.teacherName != null) this.teacherName = update.teacherName
    if (update.teacherEmail != null) this.teacherEmail = update.teacherEmail
    if (update.teacherPhone != null) this.teacherPhone = update.teacherPhone
    if (update.teacherLinkedInUrl != null) this.teacherLinkedInUrl = update.teacherLinkedInUrl
}
