package Database.DataAccessObjects.Implementation;

import Database.Connection.DatabaseConnection;
import Database.DataAccessObjects.Interface.IProjectDao;
import Database.DataEntity.Entities.Project;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static Database.DataAccessObjects.EntityMapper.ProjectMapper.*;
import static Database.DataEntity.EntityColumns.Columns.FIRST;
import static Database.DataEntity.EntityColumns.ProjectColumns.NAME;


public class ProjectDao implements IProjectDao {

    private final Connection connection;
    private Statement statement;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;
    private final String addProjectQuery = String.format("INSERT INTO project(%s) VALUES (?)", NAME.columnName);
    private final String getAllProjectsQuery = "SELECT * FROM project";
    private final String getProjectByNameQuery = String.format("SELECT * FROM project WHERE %s = ?", NAME.columnName);

    public ProjectDao(){
        connection = DatabaseConnection.getDatabaseConnection();
    }

    @Override
    public boolean addProject(Project project) throws SQLException {
        preparedStatement = connection.prepareStatement(addProjectQuery);
        mapProjectToInsertStatement(preparedStatement,project);
        return preparedStatement.execute();
    }

    @Override
    public List<Project> getAllProjects() throws SQLException {
        statement = connection.createStatement();
        resultSet = statement.executeQuery(getAllProjectsQuery);

        List<Project> projects = new ArrayList<>();
        while (resultSet.next()){
            projects.add(extractProjectFromResultSet(resultSet));
        }
        return projects;
    }

    @Override
    public Project getProjectByName(String name) throws SQLException {
        preparedStatement = connection.prepareStatement(getProjectByNameQuery);
        preparedStatement.setString(FIRST.index, name);
        resultSet = preparedStatement.executeQuery();
        return resultSet.next() ? extractProjectFromResultSet(resultSet) : null;
    }

    public boolean ifProjectExistsInDb(String name) throws SQLException {
        List<Project> projects = getAllProjects();
        return projects.stream().anyMatch(project -> project.getName().equals(name));
    }
}
