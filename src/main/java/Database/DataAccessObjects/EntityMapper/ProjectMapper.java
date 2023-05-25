package Database.DataAccessObjects.EntityMapper;

import Database.DataEntity.Entities.Project;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static Database.DataEntity.EntityColumns.Columns.FIRST;
import static Database.DataEntity.EntityColumns.Columns.SECOND;
import static Database.DataEntity.EntityColumns.ProjectColumns.ID;
import static Database.DataEntity.EntityColumns.ProjectColumns.NAME;

public class ProjectMapper {

    public static void mapProjectToInsertStatement(PreparedStatement preparedStatement, Project project) throws SQLException {
        preparedStatement.setString(FIRST.index, project.getName());
    }

    public static Project extractProjectFromResultSet(ResultSet resultSet) throws SQLException {
        return new Project(
                resultSet.getLong(ID.columnName),
                resultSet.getString(NAME.columnName)
        );
    }
}
