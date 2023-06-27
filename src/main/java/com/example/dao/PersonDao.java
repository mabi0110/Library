package com.example.dao;

import com.example.models.Person;


import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PersonDao {

    private final DataSource dataSource;

    public PersonDao() {
        try {
            this.dataSource = DataSourceProvider.getDataSource();
        } catch (NamingException e) {
            throw new RuntimeException(e);
        }
    }


    public void save(Person person) {
        final String sql = String.format("INSERT INTO person (firstName, lastName, login, pass, accountType) VALUES('%s', '%s', '%s', '%s', '%s')",
                person.getFirstName(), person.getLastName(), person.getLogin(), person.getPass(), person.getAccountType());
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {
            statement.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                person.setId(generatedKeys.getInt(1));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Optional<Person> findPersonWithLoginAndPassword(String selectedLogin, String selectedPassword) {
        String sql = String.format("SELECT id, firstName, lastName, login, pass, accountType from person where login='%s' and pass='%s'", selectedLogin, selectedPassword);
        return getPersonWithQuery(sql);
    }

    public Optional<Person> findUserWithFirstAndLastName(String selectedFirstName, String selectedLastName) {
        String sql = String.format("SELECT id, firstName, lastName, login, pass, accountType from person " +
                "where firstName='%s' and lastName='%s'", selectedFirstName, selectedLastName);
        return getPersonWithQuery(sql);
    }

    private Optional<Person> getPersonWithQuery(String sql) {
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                Integer id = resultSet.getInt("id");
                String firstName = resultSet.getString("firstName");
                String lastName = resultSet.getString("lastName");
                String login = resultSet.getString("login");
                String pass = resultSet.getString("pass");
                String accountType = resultSet.getString("accountType");
                return Optional.of(new Person(id, firstName, lastName, login, pass, accountType));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Optional.empty();
    }

    public List<Person> findUsers(){
        List<Person> users = new ArrayList<>();
        String sql = String.format("SELECT id, firstName, lastName, login, pass, accountType from person " +
                "where accountType='%s'", "USER");
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                Integer id = resultSet.getInt("id");
                String firstName = resultSet.getString("firstName");
                String lastName = resultSet.getString("lastName");
                String login = resultSet.getString("login");
                String pass = resultSet.getString("pass");
                String accountType = resultSet.getString("accountType");
                users.add(new Person(id, firstName, lastName, login, pass, accountType));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return users;
    }


    public void remove(int selectedId) {
        String sql = String.format("DELETE FROM person WHERE id=%d", selectedId);
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {
            statement.executeUpdate(sql);
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
}


