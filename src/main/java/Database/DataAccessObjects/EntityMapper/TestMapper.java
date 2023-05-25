package Database.DataAccessObjects.EntityMapper;

import Database.DataEntity.Entities.Test;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

import static Database.DataEntity.EntityColumns.Columns.*;
import static Database.DataEntity.EntityColumns.TestColumns.*;

public class TestMapper {
    
    public static void mapTestToInsertStatement(PreparedStatement preparedStatement, Test test) throws SQLException {
        preparedStatement.setString(FIRST.index, test.getName());
        preparedStatement.setLong(SECOND.index, test.getAuthor_id());
        preparedStatement.setLong(THIRD.index, test.getProject_id());
        preparedStatement.setLong(FOURTH.index, test.getSession_id());
        preparedStatement.setString(FIFTH.index, test.getEnv());
        preparedStatement.setString(SIXTH.index, test.getBrowser());
        preparedStatement.setString(SEVENTH.index, test.getMethod_name());
        preparedStatement.setObject(EIGHTH.index, test.getStart_time());
        preparedStatement.setObject(NINTH.index, test.getEnd_time());
        preparedStatement.setInt(TENTH.index, test.getStatus_id());
    }

    public static Test extractTestFromResultSet(ResultSet resultSet) throws SQLException {
        return new Test(
                resultSet.getLong(ID.columnName),
                resultSet.getString(NAME.columnName),
                resultSet.getLong(AUTHOR_ID.columnName),
                resultSet.getLong(PROJECT_ID.columnName),
                resultSet.getLong(SESSION_ID.columnName),
                resultSet.getString(ENV.columnName),
                resultSet.getString(BROWSER.columnName),
                resultSet.getString(METHOD_NAME.columnName),
                resultSet.getObject(START_TIME.columnName, LocalDateTime.class),
                resultSet.getObject(END_TIME.columnName, LocalDateTime.class),
                resultSet.getInt(STATUS_ID.columnName)
        );
    }
}
