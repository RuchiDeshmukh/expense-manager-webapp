package com.expense.manager.service;

import java.sql.Date;
import java.text.ParseException;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.expense.manager.dto.ExpenseDTO;
import com.expense.manager.dto.ExpenseFilterDTO;
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
		if(expense.getId() == null) {
			expense.setExpenseId(UUID.randomUUID().toString());	
		}
		
		//set the expense date
		expense.setDate(DateTimeUtil.convertStringToDate(expenseDTO.getDateString()));
		
		return expense;
		
	}
	
	public void deleteExpense(String id) {
		
		Expense existingExpense = getExpense(id);
		
		expenseRepository.delete(existingExpense);
		
	}
	
	public ExpenseDTO getExpenseId(String id) {
		Expense existingExpense = getExpense(id);
		
		return mapToDTO(existingExpense);
	}
	
	private Expense getExpense(String id) {
		Expense existingExpense = expenseRepository.findByExpenseId(id).orElseThrow(()-> new RuntimeException("expense not found for id"));
		return existingExpense;
	}
	
	
	public List<ExpenseDTO> getFilteredExpenses(ExpenseFilterDTO expenseFilterDTO) throws ParseException{
		String keyword = expenseFilterDTO.getKeyword();
		String sortBy = expenseFilterDTO.getSortBy();
		String startDateString =expenseFilterDTO.getStartDate();
		String endDateString = expenseFilterDTO.getStartDate();
		
		Date startDate = !startDateString.isEmpty() ? DateTimeUtil.convertStringToDate(expenseFilterDTO.getStartDate()) : new Date(0);
		Date endDate = !endDateString.isEmpty() ? DateTimeUtil.convertStringToDate(expenseFilterDTO.getStartDate()) : new Date(System.currentTimeMillis());
		
		List<Expense> list = expenseRepository.findByNameContainingAndDateBetween(keyword,startDate,endDate);
		List<ExpenseDTO> filteredList = list.stream().map(this::mapToDTO).collect(Collectors.toList());
		if(sortBy.equals("date")) {
			filteredList.sort(Comparator.comparing(ExpenseDTO::getDate));
		}else {
			filteredList.sort(Comparator.comparing(ExpenseDTO::getAmount).reversed());
		}
		return filteredList;
	}

	
	
}
