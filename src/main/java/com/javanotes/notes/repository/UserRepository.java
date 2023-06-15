package com.javanotes.notes.repository;

import com.javanotes.notes.models.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long>
{
    UserEntity findByUsername(String username);

    UserEntity findFirstByUsername(String username);
}
