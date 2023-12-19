package com.claudionetto;

import com.claudionetto.dao.ExpenseDAO;
import com.claudionetto.model.Category;
import com.claudionetto.model.Expense;

import java.time.LocalDate;

public class UpdateExpense {
    public static void main(String[] args) {
        ExpenseDAO dao = new ExpenseDAO();
        Expense expense = new Expense(14L, "Médico", LocalDate.of(2023, 12, 15), 215, Category.SAUDE);

        Expense expenseUpdated = dao.update(expense);
        System.out.println(expenseUpdated);
    }
}
