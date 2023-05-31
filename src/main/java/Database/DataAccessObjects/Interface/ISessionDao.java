package Database.DataAccessObjects.Interface;

import Database.DataEntity.Entities.Session;


public interface ISessionDao {

    long addSessionAndGetId(Session session);
}
