package Database.DataAccessObjects.Implementation;

import Database.DataAccessObjects.Interface.IProjectDao;
import Database.DataEntity.Entities.Project;

import java.sql.*;

import static Database.DataEntity.EntityColumns.ProjectColumns.ID;
import static Database.DataEntity.EntityColumns.ProjectColumns.NAME;
import static Database.DbUtil.ExecuteQueryUtil.*;
import static Database.DbUtil.ResultSetUtil.getLongFromResultSet;


public class ProjectDao implements IProjectDao {

    private final String addProjectQuery = String.format("INSERT INTO project(%s) VALUES (?)", NAME.columnName);
    private final String getProjectIdByNameQuery = String.format("SELECT %s FROM project WHERE %s = ?",
            ID.columnName,
            NAME.columnName);
    private final String deleteProjectQuery = String.format("DELETE FROM test WHERE %s = ?",
            ID.columnName);
    private final String existQuery = String.format("SELECT count(*) FROM project WHERE %s = ?",
            NAME.columnName);


    @Override
    public long addProjectAndGetId(Project project) {
        return executeAndGetGeneratedId(addProjectQuery, project.getName());
    }

    @Override
    public long getProjectIdByName(String name) {
        ResultSet resultSet = executeQueryWithParameter(getProjectIdByNameQuery, name);
        return resultSet != null ? getLongFromResultSet(resultSet, 1) : 0;
    }

    @Override
    public int deleteProjectById(long id){
        return executeUpdate(deleteProjectQuery, id);
    }

    public boolean ifProjectExistsInDb(String name) {
        return executeQueryAndGetRowCount(existQuery, name) == 1;
    }
}
