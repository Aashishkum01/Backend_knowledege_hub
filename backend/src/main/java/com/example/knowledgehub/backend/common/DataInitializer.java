package com.example.knowledgehub.backend.common;

import com.example.knowledgehub.backend.category.Category;
import com.example.knowledgehub.backend.category.CategoryRepository;
import com.example.knowledgehub.backend.resource.LearningResource;
import com.example.knowledgehub.backend.resource.LearningResourceRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DataInitializer implements CommandLineRunner {

    private final CategoryRepository categoryRepository;
    private final LearningResourceRepository resourceRepository;

    public DataInitializer(CategoryRepository categoryRepository, LearningResourceRepository resourceRepository) {
        this.categoryRepository = categoryRepository;
        this.resourceRepository = resourceRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (categoryRepository.count() == 0L) {
            var categories = List.of(
                    new Category("app-dev", "App Development", "Android, iOS, and cross-platform app development", null, null, null, null),
                    new Category("web-dev", "Web Development", "Frontend and backend web development", null, null, null, null),
                    new Category("ml-ai", "Machine Learning & AI", "ML, AI, and data science", null, null, null, null),
                    new Category("graphic-design", "Graphic Design", "UI/UX, and graphic design", null, null, null, null),
                    new Category("dsa", "Data Structures & Algorithms", "DSA and competitive programming", null, null, null, null)
            );
            categoryRepository.saveAll(categories);

            var resources = List.of(
                    new LearningResource("Android Basics", "Official Android developer documentation", "https://developer.android.com/docs", categories.get(0)),
                    new LearningResource("React Tutorial", "The official React documentation", "https://react.dev/", categories.get(1)),
                    new LearningResource("TensorFlow Guide", "Official TensorFlow developer guides", "https://www.tensorflow.org/guide", categories.get(2)),
                    new LearningResource("Figma Learn", "Learn design and Figma, from the ground up.", "https://www.figma.com/learn/", categories.get(3)),
                    new LearningResource("GeeksforGeeks DSA", "A computer science portal for geeks.", "https://www.geeksforgeeks.org/data-structures/", categories.get(4))
            );
            resourceRepository.saveAll(resources);
        }
    }
}
