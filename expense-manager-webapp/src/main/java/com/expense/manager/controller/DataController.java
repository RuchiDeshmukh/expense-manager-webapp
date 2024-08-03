package com.expense.manager.controller;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.expense.manager.dto.ExpenseDTO;
import com.expense.manager.dto.ExpenseFilterDTO;
import com.expense.manager.exception.ExpenseNotFoundException;
import com.expense.manager.service.ExpenseService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class DataController {

	private final ExpenseService expenseService;

	@GetMapping("/expenseChart")
	public String getPieChart(@ModelAttribute("filter") ExpenseFilterDTO expenseFilterDTO,Model model) {
		System.out.println(expenseFilterDTO);
		List<ExpenseDTO> expenses = expenseService.getAllExpense();
		Map<String, BigDecimal> map = expenses.stream()
										  .collect(Collectors.groupingBy(ExpenseDTO::getCategory,
												   Collectors.reducing(BigDecimal.ZERO, ExpenseDTO::getAmount, BigDecimal::add)));

		model.addAttribute("chartData", map);
		model.addAttribute("filter", new ExpenseFilterDTO());
		return "charts";
	}
	
	
	@GetMapping("/expenseChartByDate")
	public String getPieChartForTimePeriod(@ModelAttribute("filter") ExpenseFilterDTO expenseFilterDTO,Model model) throws ParseException {
		System.out.println(expenseFilterDTO);
		List<ExpenseDTO> expenses = expenseService.getFilteredExpenses(expenseFilterDTO);
		
			Map<String, BigDecimal> map = expenses.stream()
												  .collect(Collectors.groupingBy(ExpenseDTO::getCategory,
												           Collectors.reducing(BigDecimal.ZERO, ExpenseDTO::getAmount, BigDecimal::add)));

			model.addAttribute("chartData", map);
		
		return "charts";
	}
	
}