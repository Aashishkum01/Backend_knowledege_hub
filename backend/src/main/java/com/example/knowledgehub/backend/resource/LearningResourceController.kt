package com.example.knowledgehub.backend.resource

import com.example.knowledgehub.backend.category.CategoryRepository
import com.example.knowledgehub.backend.common.NotFoundException
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/resources")
@CrossOrigin(origins = ["*"])
class LearningResourceController(
    private val resourceRepository: LearningResourceRepository,
    private val categoryRepository: CategoryRepository,
) {

    @GetMapping
    fun getAll(
        @RequestParam(required = false) categoryId: Long?,
        @RequestParam(required = false) categoryCode: String?,
    ): List<LearningResourceResponse> {
        val resources = when {
            categoryId != null -> {
                val cat = categoryRepository.findById(categoryId)
                    .orElseThrow { NotFoundException("Category $categoryId not found") }
                resourceRepository.findAllByCategory(cat)
            }
            !categoryCode.isNullOrBlank() -> resourceRepository.findAllByCategoryCode(categoryCode)
            else -> resourceRepository.findAll()
        }
        return resources.map { it.toResponse() }
    }

    @GetMapping("/{id}")
    fun getOne(@PathVariable id: Long): LearningResourceResponse =
        resourceRepository.findById(id).orElseThrow { NotFoundException("Resource $id not found") }.toResponse()

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@Valid @RequestBody request: LearningResourceCreateRequest): LearningResourceResponse {
        val category = categoryRepository.findById(request.categoryId)
            .orElseThrow { NotFoundException("Category ${'$'}{request.categoryId} not found") }
        val entity = LearningResource(
            category = category,
            title = request.title,
            shortDescription = request.shortDescription,
            resourceUrl = request.resourceUrl,
            iconUrl = request.iconUrl,
            orderIndex = request.orderIndex,
        )
        return resourceRepository.save(entity).toResponse()
    }

    @PutMapping("/{id}")
    fun replace(@PathVariable id: Long, @Valid @RequestBody request: LearningResourceCreateRequest): LearningResourceResponse {
        val existing = resourceRepository.findById(id).orElseThrow { NotFoundException("Resource $id not found") }
        val category = categoryRepository.findById(request.categoryId)
            .orElseThrow { NotFoundException("Category ${'$'}{request.categoryId} not found") }
        existing.category = category
        existing.title = request.title
        existing.shortDescription = request.shortDescription
        existing.resourceUrl = request.resourceUrl
        existing.iconUrl = request.iconUrl
        existing.orderIndex = request.orderIndex
        return resourceRepository.save(existing).toResponse()
    }

    @PatchMapping("/{id}")
    fun update(@PathVariable id: Long, @RequestBody request: LearningResourceUpdateRequest): LearningResourceResponse {
        val existing = resourceRepository.findById(id).orElseThrow { NotFoundException("Resource $id not found") }
        if (request.categoryId != null) {
            val category = categoryRepository.findById(request.categoryId)
                .orElseThrow { NotFoundException("Category ${'$'}{request.categoryId} not found") }
            existing.category = category
        }
        if (request.title != null) existing.title = request.title
        if (request.shortDescription != null) existing.shortDescription = request.shortDescription
        if (request.resourceUrl != null) existing.resourceUrl = request.resourceUrl
        if (request.iconUrl != null) existing.iconUrl = request.iconUrl
        if (request.orderIndex != null) existing.orderIndex = request.orderIndex
        return resourceRepository.save(existing).toResponse()
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun delete(@PathVariable id: Long) {
        if (!resourceRepository.existsById(id)) throw NotFoundException("Resource $id not found")
        resourceRepository.deleteById(id)
    }
}
