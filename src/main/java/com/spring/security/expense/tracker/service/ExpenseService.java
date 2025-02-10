package com.spring.security.expense.tracker.service;

import com.spring.security.expense.tracker.model.Expense;

import com.spring.security.expense.tracker.repo.ExpenseRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExpenseService {

    private final ExpenseRepository expenseRepository;

    public ExpenseService(ExpenseRepository expenseRepository) {

        this.expenseRepository = expenseRepository;
    }

    public List<Expense> getAllExpenses() {
        return expenseRepository.findAll();
    }

    public Optional<Expense> getExpenseById(Long id) {
        Optional<Expense> expense = expenseRepository.findById(id);
        System.out.println("Fetched Expense: " + expense);
        return expense;
    }

    public Expense saveExpense(Expense expense) {

        return expenseRepository.save(expense);
    }

    public void deleteExpense(Long id) {
        if (this.getExpenseById(id).isPresent()) {
            expenseRepository.deleteById(id);
        } else {
            throw new RuntimeException("given expense Id not present in table");
        }
    }
}
