package Database.DataAccessObjects.ResultSetMapper;

import Database.DataEntity.Entities.Author;

import java.sql.ResultSet;
import java.sql.SQLException;

import static Database.DataEntity.EntityColumns.AuthorColumns.*;

public class AuthorMapper {

    public static Author extractAuthorFromResultSet(ResultSet resultSet) {
        try {
            return new Author(
                    resultSet.getLong(ID.columnName),
                    resultSet.getString(NAME.columnName),
                    resultSet.getString(LOGIN.columnName),
                    resultSet.getString(EMAIL.columnName)
            );
        } catch (SQLException e) {
            throw new RuntimeException("Error in extracting author from author table", e);
        }
    }
}
