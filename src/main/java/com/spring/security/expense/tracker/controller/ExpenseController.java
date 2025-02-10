package com.spring.security.expense.tracker.controller;

import com.spring.security.expense.tracker.model.Expense;
import com.spring.security.expense.tracker.service.ExpenseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/expenses/")
public class ExpenseController {

    private final ExpenseService expenseService;

    public ExpenseController(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    @GetMapping("all")
    public List<Expense> getAllExpenses() {
        return expenseService.getAllExpenses();
    }

    @GetMapping("/one/{id}")
    public ResponseEntity<Expense> getExpenseById(@PathVariable Long id) {
        Optional<Expense> expense = expenseService.getExpenseById(id);
        if (expense.isPresent()) {
            System.out.println("Returning expense: " + expense.get());
            return ResponseEntity.ok(expense.get());
        } else {
            System.out.println("Expense not found for ID: " + id);
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping(("save"))
    public Expense createExpense(@RequestBody Expense expense) {

        return expenseService.saveExpense(expense);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Void> deleteExpense(@PathVariable Long id) {
        expenseService.deleteExpense(id);
        return ResponseEntity.noContent().build();
    }
}
