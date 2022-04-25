package com.service.carrent.repository;

import com.service.carrent.model.User;

import java.util.Optional;

public interface UserRepository {
    Optional<User> findById(Long id);
}
