package com.incident.management.repository;

import com.incident.management.entity.User;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {
	User findByUserId(Integer userId);
	User findByUsername(String username);
}

