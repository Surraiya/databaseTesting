package Database.DataAccessObjects.Implementation;

import Database.DataAccessObjects.Interface.IAuthorDao;
import Database.DataEntity.Entities.Author;
import Database.DataEntity.EntityColumns.AuthorColumns;

import java.sql.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static Database.DataEntity.EntityColumns.AuthorColumns.*;
import static Database.DbUtil.ExecuteQueryUtil.*;
import static Database.DbUtil.ResultSetUtil.getLongFromResultSet;

public class AuthorDao implements IAuthorDao {
    private final String addAuthorQuery = String.format("INSERT INTO author(%s) VALUES (?,?,?)",
            Stream.of(AuthorColumns.values())
                    .filter(column -> column != ID)
                    .map(column -> column.columnName)
                    .collect(Collectors.joining(",")));
    private final String getAuthorIdByLoginQuery = String.format("SELECT %s FROM author WHERE %s = ?",
            ID.columnName,
            LOGIN.columnName);
    private final String deleteAuthorQuery = String.format("DELETE FROM test WHERE %s = ?",
            ID.columnName);

    private final String existQuery = String.format("SELECT count(*) FROM author WHERE %s = ?",
            LOGIN.columnName);

    @Override
    public long addAuthorAndGetId(Author author) {
        return executeAndGetGeneratedId(addAuthorQuery,
                author.getName(),
                author.getLogin(),
                author.getEmail()
        );
    }

    @Override
    public long getAuthorIdByLogin(String login) {
        ResultSet resultSet = executeQueryWithParameter(getAuthorIdByLoginQuery, login);
        return resultSet != null ? getLongFromResultSet(resultSet,1) : 0;
    }

    @Override
    public int deleteAuthorById(long id) {
        return executeUpdate(deleteAuthorQuery, id);
    }

    public boolean ifAuthorExistsInDb(String login) {
        return executeQueryAndGetRowCount(existQuery, login) == 1;
    }
}
