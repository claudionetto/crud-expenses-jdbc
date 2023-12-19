package com.claudionetto.dao;

import com.claudionetto.infra.ConnectionFactory;
import com.claudionetto.model.Category;
import com.claudionetto.model.Expense;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ExpenseDAO implements IExpenseDAO{
    @Override
    public Expense save(Expense expense) {

        String sql = "INSERT INTO expenses (description, date, value, category) values (?,?,?,?)";

        try(Connection connection = ConnectionFactory.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ){

            ps.setString(1, expense.getDescription());
            ps.setDate(2, Date.valueOf(expense.getDate()));
            ps.setDouble(3, expense.getValue());
            ps.setString(4, expense.getCategory().toString());

            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                rs.next();
                long id = rs.getInt("id");
                expense.setId(id);

            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return expense;
    }

    @Override
    public Expense update(Expense expense) {
        String sql = "UPDATE expenses SET description = ?, date = ?, value = ?, category = ? WHERE id = ?";

        try(Connection connection = ConnectionFactory.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ){

            ps.setString(1, expense.getDescription());
            ps.setDate(2, Date.valueOf(expense.getDate()));
            ps.setDouble(3, expense.getValue());
            ps.setString(4, expense.getCategory().toString());
            ps.setLong(5, expense.getId());

            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return expense;
    }

    @Override
    public void delete(Long id) {
        String sql = "DELETE FROM expenses WHERE id = ?";

        try(Connection connection = ConnectionFactory.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ){

            ps.setLong(1, id);
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public List<Expense> findAll() {

        List<Expense> expenses = new ArrayList<>();
        String sql = "SELECT * FROM expenses";

        try(Connection connection = ConnectionFactory.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql)) {

            try(ResultSet rs = ps.executeQuery()){
                while (rs.next()){

                    long id = rs.getLong("id");
                    String description = rs.getString("description");
                    LocalDate date = rs.getDate("date").toLocalDate();
                    double value = rs.getDouble("value");
                    Category category = Category.valueOf(rs.getString("category"));

                    Expense newExpense = new Expense(id, description, date, value, category);
                    expenses.add(newExpense);
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return expenses;
    }

    @Override
    public List<Expense> findByCategory(Category categoryToSearch) {
        List<Expense> expenses = new ArrayList<>();
        String sql = "SELECT * FROM expenses WHERE category = ?";

        try(Connection connection = ConnectionFactory.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, categoryToSearch.toString());

            try(ResultSet rs = ps.executeQuery()){
                while (rs.next()){

                    long id = rs.getLong("id");
                    String description = rs.getString("description");
                    LocalDate date = rs.getDate("date").toLocalDate();
                    double value = rs.getDouble("value");
                    Category category = Category.valueOf(rs.getString("category"));

                    Expense newExpense = new Expense(id, description, date, value, category);
                    expenses.add(newExpense);
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return expenses;
    }

    @Override
    public Optional<Expense> findById(Long idToSearch) {

        String sql = "SELECT * FROM expenses WHERE id = ?";

        try (Connection connection = ConnectionFactory.getConnection();
        PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setLong(1, idToSearch);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()){
                    long id = rs.getLong("id");
                    String description = rs.getString("description");
                    LocalDate date = rs.getDate("date").toLocalDate();
                    double value = rs.getDouble("value");
                    Category category = Category.valueOf(rs.getString("category"));

                    Expense Expense = new Expense(id, description, date, value, category);
                    return Optional.of(Expense);
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return Optional.empty();
    }
}
