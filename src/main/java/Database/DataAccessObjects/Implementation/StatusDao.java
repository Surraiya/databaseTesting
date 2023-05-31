package Database.DataAccessObjects.Implementation;

import Database.DataAccessObjects.Interface.IStatusDao;

import java.sql.ResultSet;
import java.util.List;

import static Database.DataEntity.EntityColumns.StatusColumns.ID;
import static Database.DbUtil.ExecuteQueryUtil.executeQueryWithoutParameter;
import static Database.DbUtil.ResultSetUtil.getIntListFromResultSet;

public class StatusDao implements IStatusDao {
    private final String getAllStatusIdsQuery = String.format("SELECT %s FROM status", ID.columnName);

    @Override
    public List<Integer> getAllStatusIds() {
        ResultSet resultSet = executeQueryWithoutParameter(getAllStatusIdsQuery);
        return resultSet != null ? getIntListFromResultSet(resultSet, ID.columnName) : null;
    }
}
