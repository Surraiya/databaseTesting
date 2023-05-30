package Database.DataAccessObjects.Implementation;

import Database.DataAccessObjects.Interface.IAuthorDao;
import Database.DataAccessObjects.ResultSetMapper.AuthorMapper;
import Database.DataEntity.Entities.Author;
import Database.DataEntity.EntityColumns.AuthorColumns;
import Database.DbUtil.ExecuteQueryUtil;

import java.sql.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static Database.DataEntity.EntityColumns.AuthorColumns.*;

public class AuthorDao implements IAuthorDao {
    private final String addAuthorQuery = String.format("INSERT INTO author(%s) VALUES (?,?,?)",
            Stream.of(AuthorColumns.values())
                    .filter(column -> column != ID)
                    .map(column -> column.columnName)
                    .collect(Collectors.joining(",")));
    private final String getAuthorByLoginQuery = String.format("SELECT * FROM author WHERE %s = ?", LOGIN.columnName);


    @Override
    public int addAuthor(Author author) {
        Object[] parameters = {
                author.getName(),
                author.getLogin(),
                author.getEmail()
        };
        return ExecuteQueryUtil.executeUpdate(addAuthorQuery, parameters);
    }

    @Override
    public Author getAuthorByLogin(String login) {
        ResultSet resultSet = ExecuteQueryUtil.executeQueryWithParameter(getAuthorByLoginQuery, login);
        return AuthorMapper.extractAuthorFromResultSet(resultSet);
    }

    public boolean ifAuthorExistsInDb(String login) {
        Author author = getAuthorByLogin(login);
        return author != null;
    }
}
