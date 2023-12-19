package com.claudionetto;

import com.claudionetto.dao.ExpenseDAO;

public class DeleteExpense {
    public static void main(String[] args) {

        ExpenseDAO expenseDAO = new ExpenseDAO();

        expenseDAO.delete(14L);

    }
}
