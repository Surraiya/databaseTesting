package Database.DataAccessObjects.Implementation;

import Database.DataAccessObjects.Interface.ISessionDao;
import Database.DataEntity.Entities.Session;
import Database.DataEntity.EntityColumns.SessionColumns;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import static Database.DataEntity.EntityColumns.SessionColumns.ID;
import static Database.DbUtil.ExecuteQueryUtil.*;


public class SessionDao implements ISessionDao {

    private final String addSessionQuery = String.format("INSERT INTO session(%s) VALUES (?,?,?)",
            Stream.of(SessionColumns.values())
                    .filter(column -> column != ID)
                    .map(column -> column.columnName)
                    .collect(Collectors.joining(",")));


    @Override
    public long addSessionAndGetId(Session session) {
        return executeAndGetGeneratedId(addSessionQuery,
                session.getBuild_number(),
                session.getCreated_time(),
                session.getSession_key()
        );
    }
}
