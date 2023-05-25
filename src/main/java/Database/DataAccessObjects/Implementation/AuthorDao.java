package Database.DataAccessObjects.Implementation;

import Database.Connection.DatabaseConnection;
import Database.DataAccessObjects.Interface.IAuthorDao;
import Database.DataEntity.Entities.Author;
import Database.DataEntity.EntityColumns.AuthorColumns;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static Database.DataAccessObjects.EntityMapper.AuthorMapper.*;
import static Database.DataEntity.EntityColumns.AuthorColumns.*;
import static Database.DataEntity.EntityColumns.Columns.FIRST;

public class AuthorDao implements IAuthorDao {
    private final Connection connection;
    private Statement statement;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;
    private final String addAuthorQuery = String.format("INSERT INTO author(%s) VALUES (?,?,?)",
            Stream.of(AuthorColumns.values())
                    .filter(column -> column != ID)
                    .map(column -> column.columnName)
                    .collect(Collectors.joining(",")));
    private final String getAllAuthorsQuery = "SELECT * FROM author";
    private final String getAuthorByLoginQuery = String.format("SELECT * FROM author WHERE %s = ?", LOGIN.columnName);

    public AuthorDao(){
        connection = DatabaseConnection.getDatabaseConnection();
    }

    @Override
    public boolean addAuthor(Author author) throws SQLException {
        preparedStatement = connection.prepareStatement(addAuthorQuery);
        mapAuthorToInsertStatement(preparedStatement,author);
        return preparedStatement.execute();
    }

    @Override
    public List<Author> getAllAuthors() throws SQLException {
        statement = connection.createStatement();
        resultSet = statement.executeQuery(getAllAuthorsQuery);

        List<Author> authors = new ArrayList<>();
        while (resultSet.next()){
            authors.add(extractAuthorFromResultSet(resultSet));
        }

        return authors;
    }

    @Override
    public Author getAuthorByLogin(String login) throws SQLException {
        preparedStatement = connection.prepareStatement(getAuthorByLoginQuery);
        preparedStatement.setString(FIRST.index, login);
        resultSet = preparedStatement.executeQuery();
        return resultSet.next() ? extractAuthorFromResultSet(resultSet) : null;
    }

    public boolean ifAuthorExistsInDb(String login) throws SQLException {
        List<Author> authors = getAllAuthors();
        return authors.stream()
                .anyMatch(author -> author.getLogin().equals(login));
    }
}
