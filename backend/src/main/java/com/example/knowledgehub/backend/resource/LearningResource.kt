package com.example.knowledgehub.backend.resource

import com.example.knowledgehub.backend.category.Category
import jakarta.persistence.*

@Entity
@Table(name = "learning_resources")
class LearningResource(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "category_id", nullable = false)
    var category: Category,

    @Column(nullable = false)
    var title: String,

    @Column(nullable = false, columnDefinition = "TEXT")
    var shortDescription: String,

    @Column(nullable = false)
    var resourceUrl: String,

    var iconUrl: String? = null,

    var orderIndex: Int? = null,
)
