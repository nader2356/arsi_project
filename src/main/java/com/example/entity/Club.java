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
@Table(name = "club")
@SQLDelete(sql = "UPDATE club SET deleted = true WHERE id=?")
@Where(clause = "deleted = false ")
public class Club {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String logo;
    private String location;
    @Column(length = 1000)
    private String description;
    private String contact;
    private boolean status;
    @CreationTimestamp
    private Instant createdAt;
    @CreationTimestamp
    private Instant updatedAt;
    private boolean deleted = Boolean.FALSE;
}