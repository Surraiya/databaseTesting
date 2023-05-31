package Database.DataAccessObjects.Interface;

import Database.DataEntity.Entities.Project;


public interface IProjectDao {

    long addProjectAndGetId(Project project);
    long getProjectIdByName(String name);
    int deleteProjectById(long id);
}
