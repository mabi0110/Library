package com.example.dao;

import com.example.models.Book;

import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;

public class BookDao {

    private final DataSource dataSource;

    public BookDao() {
        try {
            this.dataSource = DataSourceProvider.getDataSource();
        } catch (NamingException e) {
            throw new RuntimeException(e);
        }
    }

    public void save(Book book) {
        final String sql = String.format("INSERT INTO book (title, isbn, publisher, publicationYear) VALUES('%s', '%s', '%s', %d)",
                book.getTitle(), book.getIsbn(), book.getPublisher(), book.getPublicationYear());
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {
            statement.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                book.setId(generatedKeys.getInt(1));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Optional<Book> findBookWithTitle(String selectedTitle) {
        String sql = String.format("SELECT id, title, isbn, publisher, publicationYear from book " +
                "where title='%s'", selectedTitle);
        return getBookWithQuery(sql);
    }

    private Optional<Book> getBookWithQuery(String sql) {
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                Integer id = resultSet.getInt("id");
                String title = resultSet.getString("title");
                String isbn = resultSet.getString("isbn");
                String publisher = resultSet.getString("publisher");
                int publicationYear = resultSet.getInt("publicationYear");
                return Optional.of(new Book(id, title, isbn, publisher, publicationYear));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Optional.empty();
    }

    public void remove(int selectedId) {
        String sql = String.format("DELETE FROM book WHERE id=%d", selectedId);
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {
            statement.executeUpdate(sql);
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
}
