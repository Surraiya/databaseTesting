package Database.DataAccessObjects.EntityMapper;

import Database.DataEntity.Entities.Author;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static Database.DataEntity.EntityColumns.AuthorColumns.*;
import static Database.DataEntity.EntityColumns.Columns.*;

public class AuthorMapper {

    public static void mapAuthorToInsertStatement(PreparedStatement preparedStatement, Author author) throws SQLException {
        preparedStatement.setString(FIRST.index, author.getName());
        preparedStatement.setString(SECOND.index, author.getLogin());
        preparedStatement.setString(THIRD.index, author.getEmail());
    }

    public static Author extractAuthorFromResultSet(ResultSet resultSet) throws SQLException {
        return new Author(
                resultSet.getLong(ID.columnName),
                resultSet.getString(NAME.columnName),
                resultSet.getString(LOGIN.columnName),
                resultSet.getString(EMAIL.columnName)
        );
    }
}
