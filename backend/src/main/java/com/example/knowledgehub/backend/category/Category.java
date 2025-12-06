package com.example.knowledgehub.backend.category;

import jakarta.persistence.*;

@Entity
@Table(name = "categories")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String code;

    @Column(nullable = false)
    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;

    private String teacherName;
    private String teacherEmail;
    private String teacherPhone;
    private String teacherLinkedInUrl;

    public Category() {
    }

    public Category(String code, String name, String description,
                    String teacherName, String teacherEmail,
                    String teacherPhone, String teacherLinkedInUrl) {
        this.code = code;
        this.name = name;
        this.description = description;
        this.teacherName = teacherName;
        this.teacherEmail = teacherEmail;
        this.teacherPhone = teacherPhone;
        this.teacherLinkedInUrl = teacherLinkedInUrl;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getTeacherEmail() {
        return teacherEmail;
    }

    public void setTeacherEmail(String teacherEmail) {
        this.teacherEmail = teacherEmail;
    }

    public String getTeacherPhone() {
        return teacherPhone;
    }

    public void setTeacherPhone(String teacherPhone) {
        this.teacherPhone = teacherPhone;
    }

    public String getTeacherLinkedInUrl() {
        return teacherLinkedInUrl;
    }

    public void setTeacherLinkedInUrl(String teacherLinkedInUrl) {
        this.teacherLinkedInUrl = teacherLinkedInUrl;
    }
}
