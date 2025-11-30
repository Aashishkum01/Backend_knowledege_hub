package com.example.knowledgehub.backend.category

import com.example.knowledgehub.backend.common.NotFoundException
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/categories")
@CrossOrigin(origins = ["*"])
class CategoryController(
    private val repository: CategoryRepository,
) {
    //new commit comment
    @GetMapping
    fun getAll(): List<CategoryResponse> = repository.findAll().map { it.toResponse() }

    @GetMapping("/{id}")
    fun getOne(@PathVariable id: Long): CategoryResponse =
        repository.findById(id).orElseThrow { NotFoundException("Category $id not found") }.toResponse()

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@Valid @RequestBody request: CategoryCreateRequest): CategoryResponse {
        val entity = request.toEntity()
        val saved = repository.save(entity)
        return saved.toResponse()
    }

    @PutMapping("/{id}")
    fun replace(@PathVariable id: Long, @Valid @RequestBody request: CategoryCreateRequest): CategoryResponse {
        val existing = repository.findById(id).orElseThrow { NotFoundException("Category $id not found") }
        existing.code = request.code
        existing.name = request.name
        existing.description = request.description
        existing.teacherName = request.teacherName
        existing.teacherEmail = request.teacherEmail
        existing.teacherPhone = request.teacherPhone
        existing.teacherLinkedInUrl = request.teacherLinkedInUrl
        return repository.save(existing).toResponse()
    }

    @PatchMapping("/{id}")
    fun update(@PathVariable id: Long, @RequestBody request: CategoryUpdateRequest): CategoryResponse {
        val existing = repository.findById(id).orElseThrow { NotFoundException("Category $id not found") }
        existing.applyUpdate(request)
        return repository.save(existing).toResponse()
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun delete(@PathVariable id: Long) {
        if (!repository.existsById(id)) throw NotFoundException("Category $id not found")
        repository.deleteById(id)
    }
}
