package com.example.knowledgehub.backend.category;

import com.example.knowledgehub.backend.common.NotFoundException;
import com.example.knowledgehub.backend.category.CategoryDtos.CategoryCreateRequest;
import com.example.knowledgehub.backend.category.CategoryDtos.CategoryResponse;
import com.example.knowledgehub.backend.category.CategoryDtos.CategoryUpdateRequest;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/categories")
@CrossOrigin(origins = "*")
public class CategoryController {

    private final CategoryRepository repository;

    public CategoryController(CategoryRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<CategoryResponse> getAll() {
        return repository.findAll().stream()
                .map(CategoryDtos::toResponse)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public CategoryResponse getOne(@PathVariable Long id) {
        Category category = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Category " + id + " not found"));
        return CategoryDtos.toResponse(category);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CategoryResponse create(@Valid @RequestBody CategoryCreateRequest request) {
        Category entity = CategoryDtos.fromCreateRequest(request);
        Category saved = repository.save(entity);
        return CategoryDtos.toResponse(saved);
    }

    @PutMapping("/{id}")
    public CategoryResponse replace(@PathVariable Long id, @Valid @RequestBody CategoryCreateRequest request) {
        Category existing = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Category " + id + " not found"));
        existing.setCode(request.code());
        existing.setName(request.name());
        existing.setDescription(request.description());
        existing.setTeacherName(request.teacherName());
        existing.setTeacherEmail(request.teacherEmail());
        existing.setTeacherPhone(request.teacherPhone());
        existing.setTeacherLinkedInUrl(request.teacherLinkedInUrl());
        Category saved = repository.save(existing);
        return CategoryDtos.toResponse(saved);
    }

    @PatchMapping("/{id}")
    public CategoryResponse update(@PathVariable Long id, @RequestBody CategoryUpdateRequest request) {
        Category existing = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Category " + id + " not found"));
        CategoryDtos.applyUpdate(existing, request);
        Category saved = repository.save(existing);
        return CategoryDtos.toResponse(saved);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        if (!repository.existsById(id)) {
            throw new NotFoundException("Category " + id + " not found");
        }
        repository.deleteById(id);
    }
}
