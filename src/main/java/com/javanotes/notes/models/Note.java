package com.javanotes.notes.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "note")
public class Note
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String content;
    private String url;
    @CreationTimestamp
    private LocalDateTime createTime;

    @OneToMany(mappedBy = "note", cascade = CascadeType.PERSIST)
    private Set<Category> categories = new HashSet<>();
}
