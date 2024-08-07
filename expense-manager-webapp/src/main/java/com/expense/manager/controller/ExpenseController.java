package com.expense.manager.controller;

import java.text.ParseException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.expense.manager.dto.ExpenseDTO;
import com.expense.manager.dto.ExpenseFilterDTO;
import com.expense.manager.service.ExpenseService;
import com.expense.manager.util.DateTimeUtil;
import com.expense.manager.validator.ExpenseValidator;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class ExpenseController {
	
	private final ExpenseService expenseService;
	

	@GetMapping("/expenses")
	public String showExpenseList(Model model) {
		List<ExpenseDTO> list = expenseService.getAllExpense();
		model.addAttribute("expenses",list);
		model.addAttribute("filter", new ExpenseFilterDTO(DateTimeUtil.getCurrentMonthStartDate(),
														  DateTimeUtil.getCurrentMonthDate()));
		String totalExpenses =  expenseService.totalingExpenses(list);
		model.addAttribute("totalExpenses", totalExpenses);
		return "expenses-list";
	}
	
	@GetMapping("/createExpense")
	public String showExpenseForm(Model model) {
		model.addAttribute("expense", new ExpenseDTO());
		return "expense-form";
	}
	
	@PostMapping("/saveOrUpdateExpense")
	public String saveOrUpdateExpenseDetails(@Valid @ModelAttribute("expense") ExpenseDTO expenseDTO,BindingResult result) throws ParseException {
		
		new ExpenseValidator().validate(expenseDTO, result);
		
		if(result.hasErrors()) {
			return "expense-form";
		}
		expenseService.saveExpenseDetails(expenseDTO);
		return "redirect:/expenses";
	}
	
	@GetMapping("/deleteExpense")
	public String deleteExpense(@RequestParam String id) {
		expenseService.deleteExpense(id);
		return "redirect:/expenses";
	}
	
	@GetMapping("/updateExpense")
	public String updateExpense(@RequestParam String id, Model model) {
		ExpenseDTO expenseDTO = expenseService.getExpenseId(id);
		model.addAttribute("expense", expenseDTO);
		return "expense-form";
	}
}
