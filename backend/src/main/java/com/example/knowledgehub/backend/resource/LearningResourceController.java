package com.example.knowledgehub.backend.resource;

import com.example.knowledgehub.backend.category.Category;
import com.example.knowledgehub.backend.category.CategoryRepository;
import com.example.knowledgehub.backend.common.NotFoundException;
import com.example.knowledgehub.backend.resource.LearningResourceDtos.LearningResourceCreateRequest;
import com.example.knowledgehub.backend.resource.LearningResourceDtos.LearningResourceResponse;
import com.example.knowledgehub.backend.resource.LearningResourceDtos.LearningResourceUpdateRequest;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/resources")
@CrossOrigin(origins = "*")
public class LearningResourceController {

    private final LearningResourceRepository resourceRepository;
    private final CategoryRepository categoryRepository;

    public LearningResourceController(LearningResourceRepository resourceRepository, CategoryRepository categoryRepository) {
        this.resourceRepository = resourceRepository;
        this.categoryRepository = categoryRepository;
    }

    @GetMapping
    public List<LearningResourceResponse> getAll() {
        return resourceRepository.findAll().stream()
                .map(LearningResourceDtos::toResponse)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public LearningResourceResponse getOne(@PathVariable Long id) {
        LearningResource resource = resourceRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Resource " + id + " not found"));
        return LearningResourceDtos.toResponse(resource);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public LearningResourceResponse create(@Valid @RequestBody LearningResourceCreateRequest request) {
        Category category = null;
        if (request.categoryId() != null) {
            category = categoryRepository.findById(request.categoryId())
                    .orElseThrow(() -> new NotFoundException("Category " + request.categoryId() + " not found"));
        }
        LearningResource entity = LearningResourceDtos.fromCreateRequest(request, category);
        LearningResource saved = resourceRepository.save(entity);
        return LearningResourceDtos.toResponse(saved);
    }

    @PutMapping("/{id}")
    public LearningResourceResponse replace(@PathVariable Long id, @Valid @RequestBody LearningResourceCreateRequest request) {
        LearningResource existing = resourceRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Resource " + id + " not found"));
        Category category = null;
        if (request.categoryId() != null) {
            category = categoryRepository.findById(request.categoryId())
                    .orElseThrow(() -> new NotFoundException("Category " + request.categoryId() + " not found"));
        }
        existing.setName(request.name());
        existing.setDescription(request.description());
        existing.setUrl(request.url());
        existing.setCategory(category);
        LearningResource saved = resourceRepository.save(existing);
        return LearningResourceDtos.toResponse(saved);
    }

    @PatchMapping("/{id}")
    public LearningResourceResponse update(@PathVariable Long id, @RequestBody LearningResourceUpdateRequest request) {
        LearningResource existing = resourceRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Resource " + id + " not found"));
        if (request.name() != null) {
            existing.setName(request.name());
        }
        if (request.description() != null) {
            existing.setDescription(request.description());
        }
        if (request.url() != null) {
            existing.setUrl(request.url());
        }
        if (request.categoryId() != null) {
            Category category = categoryRepository.findById(request.categoryId())
                    .orElseThrow(() -> new NotFoundException("Category " + request.categoryId() + " not found"));
            existing.setCategory(category);
        }
        LearningResource saved = resourceRepository.save(existing);
        return LearningResourceDtos.toResponse(saved);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        if (!resourceRepository.existsById(id)) {
            throw new NotFoundException("Resource " + id + " not found");
        }
        resourceRepository.deleteById(id);
    }
}
