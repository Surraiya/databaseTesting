package Database.DataAccessObjects.EntityMapper;

import Database.DataEntity.Entities.Session;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

import static Database.DataEntity.EntityColumns.Columns.*;
import static Database.DataEntity.EntityColumns.SessionColumns.*;

public class SessionMapper {

    public static void mapSessionToInsertStatement(PreparedStatement preparedStatement, Session session) throws SQLException {
        preparedStatement.setLong(FIRST.index, session.getBuild_number());
        preparedStatement.setObject(SECOND.index, session.getCreated_time());
        preparedStatement.setString(THIRD.index, session.getSession_key());
    }


    public static Session extractSessionFromResultSet(ResultSet resultSet) throws SQLException {
        return new Session(
                resultSet.getLong(ID.columnName),
                resultSet.getLong(BUILD_NUMBER.columnName),
                resultSet.getObject(CREATED_TIME.columnName, LocalDateTime.class),
                resultSet.getString(SESSION_KEY.columnName)
        );
    }
}
