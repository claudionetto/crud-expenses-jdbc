package com.claudionetto.dao;

import com.claudionetto.model.Category;
import com.claudionetto.model.Expense;

import java.util.List;
import java.util.Optional;

public interface IExpenseDAO {

    Expense save(Expense expense);
    Expense update(Expense expense);
    void delete(Long id);
    List<Expense> findAll();
    List<Expense> findByCategory(Category categoryToSearch);
    Optional<Expense> findById(Long idToSearch);

}
