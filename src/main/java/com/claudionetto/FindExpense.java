package com.claudionetto;

import com.claudionetto.dao.ExpenseDAO;
import com.claudionetto.model.Category;
import com.claudionetto.model.Expense;

import java.util.List;
import java.util.Optional;

public class FindExpense {
    public static void main(String[] args) {
        ExpenseDAO dao = new ExpenseDAO();

//        List<Expense> expenses = dao.findAll();
//        expenses.forEach(System.out::println);

//        List<Expense> expensesByCategory = dao.findByCategory(Category.LAZER);
//        expensesByCategory.forEach(System.out::println);

        Optional<Expense> optionalExpense = dao.findById(5L);
        if (optionalExpense.isPresent()) {
            Expense expense = optionalExpense.get();
            System.out.println(expense);
        }


    }
}
