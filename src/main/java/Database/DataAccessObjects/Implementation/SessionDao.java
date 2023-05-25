package Database.DataAccessObjects.Implementation;

import Database.Connection.DatabaseConnection;
import Database.DataAccessObjects.Interface.ISessionDao;
import Database.DataEntity.Entities.Session;
import Database.DataEntity.EntityColumns.SessionColumns;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static Database.DataAccessObjects.EntityMapper.SessionMapper.*;
import static Database.DataEntity.EntityColumns.Columns.FIRST;
import static Database.DataEntity.EntityColumns.SessionColumns.ID;
import static Database.DataEntity.EntityColumns.SessionColumns.SESSION_KEY;


public class SessionDao implements ISessionDao {

    private final Connection connection;
    private Statement statement;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;
    private final String addSessionQuery = String.format("INSERT INTO session(%s) VALUES (?,?,?)",
            Stream.of(SessionColumns.values())
                    .filter(column -> column != ID)
                    .map(column -> column.columnName)
                    .collect(Collectors.joining(",")));
    private final String getAllSessionsQuery = "SELECT * FROM session";
    private final String getSessionByKeyQuery = String.format("SELECT * FROM session WHERE %s = ?", SESSION_KEY.columnName);

    public SessionDao(){
        connection = DatabaseConnection.getDatabaseConnection();
    }

    @Override
    public boolean addSession(Session session) throws SQLException {
        preparedStatement = connection.prepareStatement(addSessionQuery);
        mapSessionToInsertStatement(preparedStatement,session);
        return preparedStatement.execute();
    }

    @Override
    public List<Session> getAllSessions() throws SQLException {
        statement = connection.createStatement();
        resultSet = statement.executeQuery(getAllSessionsQuery);
        List<Session> sessions = new ArrayList<>();
        while (resultSet.next()){
            sessions.add(extractSessionFromResultSet(resultSet));
        }
        return sessions;
    }

    @Override
    public Session getSessionByKey(String key) throws SQLException {
        preparedStatement = connection.prepareStatement(getSessionByKeyQuery);
        preparedStatement.setString(FIRST.index, key);
        resultSet = preparedStatement.executeQuery();
        return resultSet.next() ? extractSessionFromResultSet(resultSet) : null;
    }

    public boolean ifSessionExistsInDb(String key) throws SQLException {
        List<Session> sessions = getAllSessions();
        return sessions.stream().anyMatch(session -> session.getSession_key().equals(key));
    }
}
