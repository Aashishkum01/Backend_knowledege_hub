package com.example.knowledgehub.backend.resource

import com.example.knowledgehub.backend.category.Category
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface LearningResourceRepository : JpaRepository<LearningResource, Long> {
    fun findAllByCategory(category: Category): List<LearningResource>
    fun findAllByCategoryCode(code: String): List<LearningResource>
}
