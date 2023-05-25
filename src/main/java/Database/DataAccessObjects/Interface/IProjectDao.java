package Database.DataAccessObjects.Interface;

import Database.DataEntity.Entities.Project;

import java.sql.SQLException;
import java.util.List;

public interface IProjectDao {

    boolean addProject(Project project) throws SQLException;
    List<Project> getAllProjects() throws SQLException;
    Project getProjectByName(String name) throws SQLException;
}
