package com.javanotes.notes.repository;

import com.javanotes.notes.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long>
{

}
