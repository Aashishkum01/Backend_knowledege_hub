package com.example.knowledgehub.backend.category

import jakarta.persistence.*

@Entity
@Table(name = "categories")
class Category(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @Column(nullable = false, unique = true)
    var code: String,

    @Column(nullable = false)
    var name: String,

    @Column(columnDefinition = "TEXT")
    var description: String? = null,

    var teacherName: String? = null,
    var teacherEmail: String? = null,
    var teacherPhone: String? = null,
    var teacherLinkedInUrl: String? = null,
)
