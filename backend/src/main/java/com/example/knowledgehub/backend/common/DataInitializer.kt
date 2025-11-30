package com.example.knowledgehub.backend.common

import com.example.knowledgehub.backend.category.Category
import com.example.knowledgehub.backend.category.CategoryRepository
import com.example.knowledgehub.backend.resource.LearningResource
import com.example.knowledgehub.backend.resource.LearningResourceRepository
import org.slf4j.LoggerFactory
import org.springframework.boot.CommandLineRunner
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
open class DataInitializer {

    private val logger = LoggerFactory.getLogger(DataInitializer::class.java)

    @Bean
    open fun loadInitialData(
        categoryRepository: CategoryRepository,
        resourceRepository: LearningResourceRepository,
    ) = CommandLineRunner {
        if (categoryRepository.count() > 0L) {
            logger.info("Skipping data init; categories already present")
            return@CommandLineRunner
        }

        // Create main categories roughly matching your app screens
        val appDev = categoryRepository.save(
            Category(
                code = "APP_DEV",
                name = "App Development",
                description = "Learn Android app development with Kotlin, XML and more.",
                teacherName = "Akhilesh Singh",
                teacherEmail = "akhileshbltr2002@gmail.com",
            ),
        )
        val webDev = categoryRepository.save(
            Category(
                code = "WEB_DEV",
                name = "Web Development",
                description = "Frontend fundamentals: HTML, CSS, JavaScript, React.",
                teacherName = "Mohd Hadi",
            ),
        )
        val ml = categoryRepository.save(
            Category(
                code = "ML",
                name = "Machine Learning",
                description = "Python, SQL and ML foundations.",
                teacherName = "Akhilesh Singh",
                teacherEmail = "akhileshbltr2002@gmail.com",
            ),
        )
        val dsa = categoryRepository.save(
            Category(
                code = "DSA",
                name = "Data Structures & Algorithms",
                description = "Problem solving with Java/C++, LeetCode and HackerRank.",
                teacherName = "Akhilesh Singh",
                teacherEmail = "akhileshbltr2002@gmail.com",
            ),
        )
        val graphic = categoryRepository.save(
            Category(
                code = "GRAPHIC",
                name = "Graphic Designing",
                description = "Figma, Canva, Illustrator, Photoshop.",
                teacherName = "Anshu Raj",
            ),
        )

        // A few sample resources per category (URLs follow what your app opens today)
        resourceRepository.saveAll(
            listOf(
                // App Dev
                LearningResource(
                    category = appDev,
                    title = "Android Docs",
                    shortDescription = "Official Android documentation.",
                    resourceUrl = "https://developer.android.com/develop",
                    orderIndex = 1,
                ),
                LearningResource(
                    category = appDev,
                    title = "XML Basics",
                    shortDescription = "XML introduction from MDN.",
                    resourceUrl = "https://developer.mozilla.org/en-US/docs/Web/XML/XML_introduction",
                    orderIndex = 2,
                ),
                LearningResource(
                    category = appDev,
                    title = "Kotlin Docs",
                    shortDescription = "Official Kotlin documentation.",
                    resourceUrl = "https://kotlinlang.org/docs/home.html",
                    orderIndex = 3,
                ),

                // Web Dev
                LearningResource(
                    category = webDev,
                    title = "HTML",
                    shortDescription = "HTML reference from MDN.",
                    resourceUrl = "https://developer.mozilla.org/en-US/docs/Web/HTML",
                    orderIndex = 1,
                ),
                LearningResource(
                    category = webDev,
                    title = "CSS",
                    shortDescription = "CSS reference from MDN.",
                    resourceUrl = "https://developer.mozilla.org/en-US/docs/Web/CSS",
                    orderIndex = 2,
                ),
                LearningResource(
                    category = webDev,
                    title = "JavaScript",
                    shortDescription = "JavaScript guide from MDN.",
                    resourceUrl = "https://developer.mozilla.org/en-US/docs/Web/JavaScript",
                    orderIndex = 3,
                ),
                LearningResource(
                    category = webDev,
                    title = "React",
                    shortDescription = "React documentation.",
                    resourceUrl = "https://legacy.reactjs.org/docs/getting-started.html",
                    orderIndex = 4,
                ),

                // ML
                LearningResource(
                    category = ml,
                    title = "Python",
                    shortDescription = "Official Python site.",
                    resourceUrl = "https://www.python.org/",
                    orderIndex = 1,
                ),
                LearningResource(
                    category = ml,
                    title = "SQL",
                    shortDescription = "SQL tutorial from W3Schools.",
                    resourceUrl = "https://www.w3schools.com/sql/default.asp",
                    orderIndex = 2,
                ),

                // DSA
                LearningResource(
                    category = dsa,
                    title = "Java Reference",
                    shortDescription = "Java reference from W3Schools.",
                    resourceUrl = "https://www.w3schools.com/java/java_ref_reference.asp",
                    orderIndex = 1,
                ),
                LearningResource(
                    category = dsa,
                    title = "C++",
                    shortDescription = "C++ reference from W3Schools.",
                    resourceUrl = "https://www.w3schools.com/cpp/",
                    orderIndex = 2,
                ),
                LearningResource(
                    category = dsa,
                    title = "LeetCode",
                    shortDescription = "Practice coding problems on LeetCode.",
                    resourceUrl = "https://leetcode.com/",
                    orderIndex = 3,
                ),
                LearningResource(
                    category = dsa,
                    title = "HackerRank",
                    shortDescription = "Practice problems on HackerRank.",
                    resourceUrl = "https://www.hackerrank.com/",
                    orderIndex = 4,
                ),

                // Graphic Design
                LearningResource(
                    category = graphic,
                    title = "Figma Help Center",
                    shortDescription = "Figma documentation.",
                    resourceUrl = "https://help.figma.com/hc/en-us/categories/360002042553",
                    orderIndex = 1,
                ),
                LearningResource(
                    category = graphic,
                    title = "Canva Docs",
                    shortDescription = "Canva documentation.",
                    resourceUrl = "https://www.canva.com/docs/",
                    orderIndex = 2,
                ),
                LearningResource(
                    category = graphic,
                    title = "Adobe Illustrator",
                    shortDescription = "Illustrator user guide.",
                    resourceUrl = "https://helpx.adobe.com/in/illustrator/user-guide.html",
                    orderIndex = 3,
                ),
                LearningResource(
                    category = graphic,
                    title = "Adobe Photoshop",
                    shortDescription = "Photoshop learning resources.",
                    resourceUrl = "https://www.adobe.com/africa/learn/photoshop",
                    orderIndex = 4,
                ),
            ),
        )

        logger.info("Initial categories: {}", categoryRepository.count())
        logger.info("Initial resources: {}", resourceRepository.count())
    }
}
