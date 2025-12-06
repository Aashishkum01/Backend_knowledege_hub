package com.example.knowledgehub.backend.resource;

import com.example.knowledgehub.backend.category.Category;
import jakarta.validation.constraints.NotBlank;

public class LearningResourceDtos {

    public record LearningResourceResponse(
            Long id,
            String name,
            String description,
            String url,
            Long categoryId
    ) {
    }

    public record LearningResourceCreateRequest(
            @NotBlank String name,
            String description,
            @NotBlank String url,
            Long categoryId
    ) {
    }

    public record LearningResourceUpdateRequest(
            String name,
            String description,
            String url,
            Long categoryId
    ) {
    }

    public static LearningResourceResponse toResponse(LearningResource entity) {
        if (entity == null) return null;
        return new LearningResourceResponse(
                entity.getId(),
                entity.getName(),
                entity.getDescription(),
                entity.getUrl(),
                entity.getCategory() != null ? entity.getCategory().getId() : null
        );
    }

    public static LearningResource fromCreateRequest(LearningResourceCreateRequest request, Category category) {
        if (request == null) return null;
        return new LearningResource(
                request.name(),
                request.description(),
                request.url(),
                category
        );
    }

    public static void applyUpdate(LearningResource entity, LearningResourceUpdateRequest update, Category category) {
        if (entity == null || update == null) return;
        if (update.name() != null) entity.setName(update.name());
        if (update.description() != null) entity.setDescription(update.description());
        if (update.url() != null) entity.setUrl(update.url());
        if (update.categoryId() != null) entity.setCategory(category);
    }
}
