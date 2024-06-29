package com.expense.manager.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.expense.manager.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

	
}
