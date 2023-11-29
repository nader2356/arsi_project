package com.example.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import jakarta.persistence.*;
import java.time.Instant;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Document")
@SQLDelete(sql = "UPDATE document SET deleted = true WHERE id=?")
@Where(clause = "deleted = false ")
public class Document {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String title;
    @Column(length = 1000)
    private String description;
    @Column(name = "image", unique = false, nullable = false, length = 5000000)
    private String image;
    private String URLDocument;
    private String typedoc;
    @CreationTimestamp
    private Instant createdAt;
    @CreationTimestamp
    private Instant updatedAt;
    private boolean deleted = Boolean.FALSE;
}