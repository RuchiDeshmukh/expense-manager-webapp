package com.expense.manager.service;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.expense.manager.dto.ExpenseDTO;
import com.expense.manager.entity.Expense;
import com.expense.manager.repository.ExpenseRepository;
import com.expense.manager.util.DateTimeUtil;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ExpenseService {
	
	private final ExpenseRepository expenseRepository;
	private final ModelMapper modelMapper;
	
	public List<ExpenseDTO> getAllExpense(){
		
		Expense expense = new Expense();
		expense.setName("bill");
		expense.setDescription("water bill");
		expense.setAmount(new BigDecimal(700.00));
		expense.setDate(new Date(System.currentTimeMillis()));
		expenseRepository.save(expense);
		expense = new Expense();
		expense.setName("bill");
		expense.setDescription("eletricty bill");
		expense.setAmount(new BigDecimal(200.00));
		expense.setDate(new Date(System.currentTimeMillis()));
		expenseRepository.save(expense);
		
		
		List<Expense> list =  expenseRepository.findAll();
		
		List<ExpenseDTO> expenseList = list.stream().map(this :: mapToDTO).collect(Collectors.toList());
		
		return expenseList;
	}
	
	private  ExpenseDTO mapToDTO(Expense expense) {
		ExpenseDTO expenseDTO = modelMapper.map(expense, ExpenseDTO.class);
		expenseDTO.setDateString(DateTimeUtil.convertDateToString(expense.getDate()));
		return expenseDTO;
	}

}
