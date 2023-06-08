package com.javanotes.notes.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "category")
public class Category
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    /*
    @ManyToOne
    @JoinColumn(name="note_id")
    private Note note;
     */

    @ManyToMany(mappedBy = "categories")
    private Set<Note> notes = new HashSet<>();
}