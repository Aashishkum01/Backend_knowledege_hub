package com.example.knowledgehub.backend.category;

import jakarta.validation.constraints.NotBlank;

public class CategoryDtos {

    public record CategoryResponse(
            Long id,
            String code,
            String name,
            String description,
            String teacherName,
            String teacherEmail,
            String teacherPhone,
            String teacherLinkedInUrl
    ) {
    }

    public record CategoryCreateRequest(
            @NotBlank String code,
            @NotBlank String name,
            String description,
            String teacherName,
            String teacherEmail,
            String teacherPhone,
            String teacherLinkedInUrl
    ) {
    }

    public record CategoryUpdateRequest(
            String code,
            String name,
            String description,
            String teacherName,
            String teacherEmail,
            String teacherPhone,
            String teacherLinkedInUrl
    ) {
    }

    public static CategoryResponse toResponse(Category entity) {
        if (entity == null) return null;
        return new CategoryResponse(
                entity.getId(),
                entity.getCode(),
                entity.getName(),
                entity.getDescription(),
                entity.getTeacherName(),
                entity.getTeacherEmail(),
                entity.getTeacherPhone(),
                entity.getTeacherLinkedInUrl()
        );
    }

    public static Category fromCreateRequest(CategoryCreateRequest request) {
        if (request == null) return null;
        return new Category(
                request.code(),
                request.name(),
                request.description(),
                request.teacherName(),
                request.teacherEmail(),
                request.teacherPhone(),
                request.teacherLinkedInUrl()
        );
    }

    public static void applyUpdate(Category entity, CategoryUpdateRequest update) {
        if (entity == null || update == null) return;
        if (update.code() != null) entity.setCode(update.code());
        if (update.name() != null) entity.setName(update.name());
        if (update.description() != null) entity.setDescription(update.description());
        if (update.teacherName() != null) entity.setTeacherName(update.teacherName());
        if (update.teacherEmail() != null) entity.setTeacherEmail(update.teacherEmail());
        if (update.teacherPhone() != null) entity.setTeacherPhone(update.teacherPhone());
        if (update.teacherLinkedInUrl() != null) entity.setTeacherLinkedInUrl(update.teacherLinkedInUrl());
    }
}
