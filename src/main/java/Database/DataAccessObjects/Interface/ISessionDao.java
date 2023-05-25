package Database.DataAccessObjects.Interface;

import Database.DataEntity.Entities.Session;

import java.sql.SQLException;
import java.util.List;

public interface ISessionDao {

    boolean addSession(Session session) throws SQLException;
    List<Session> getAllSessions() throws SQLException;
    Session getSessionByKey(String key) throws SQLException;
}
