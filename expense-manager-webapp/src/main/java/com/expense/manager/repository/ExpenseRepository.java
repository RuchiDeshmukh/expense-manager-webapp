package com.expense.manager.repository;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.expense.manager.entity.Expense;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {

	Optional<Expense> findByExpenseId(String id);
	
	List<Expense> findByNameContainingAndDateBetween(String keyword, Date startDate, Date endDate);
}
