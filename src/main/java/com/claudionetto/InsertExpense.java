package com.claudionetto;

import com.claudionetto.dao.ExpenseDAO;
import com.claudionetto.model.Category;
import com.claudionetto.model.Expense;

import java.time.LocalDate;

public class InsertExpense {

    public static void main(String[] args) {

        ExpenseDAO dao = new ExpenseDAO();
        Expense expense = new Expense("Dentista", LocalDate.of(2023, 12, 10), 255, Category.SAUDE);

        Expense expenseSaved = dao.save(expense);
        System.out.println(expenseSaved);

    }



}
