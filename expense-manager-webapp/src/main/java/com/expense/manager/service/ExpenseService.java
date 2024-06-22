package com.expense.manager.service;

import java.text.ParseException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
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
			
		List<Expense> list =  expenseRepository.findAll();
		
		List<ExpenseDTO> expenseList = list.stream().map(this :: mapToDTO).collect(Collectors.toList());
		
		return expenseList;
	}
	
	private  ExpenseDTO mapToDTO(Expense expense) {
		ExpenseDTO expenseDTO = modelMapper.map(expense, ExpenseDTO.class);
		expenseDTO.setDateString(DateTimeUtil.convertDateToString(expense.getDate()));
		return expenseDTO;
	}
	
	public ExpenseDTO saveExpenseDetails(ExpenseDTO expenseDTO) throws ParseException {
		//map dto to entity
		Expense expense =mapToEntity(expenseDTO);
		
		//save entity to db
		expense = expenseRepository.save(expense);
		
		//map entity to dto
		return mapToDTO(expense);
	}

	private Expense mapToEntity(ExpenseDTO expenseDTO) throws ParseException {
		//map dto to entity
		Expense expense = modelMapper.map(expenseDTO,Expense.class);
		//generate the expense id
		expense.setExpenseId(UUID.randomUUID().toString());
		
		//set the expense date
		expense.setDate(DateTimeUtil.convertStringToDate(expenseDTO.getDateString()));
		
		return expense;
		
	}
	
	public void deleteExpense(String id) {
		
		Expense existingExpense = expenseRepository.findByExpenseId(id).orElseThrow(()-> new RuntimeException("expense not found for id"));
		
		expenseRepository.delete(existingExpense);
		
	}
	
	
	
	
	
	
}
