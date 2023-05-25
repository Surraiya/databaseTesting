package Database.DataEntity.EntityColumns;

public enum ProjectColumns {
    ID("id"),
    NAME("name");

    public final String columnName;

    ProjectColumns(String columnName){
        this.columnName = columnName;
    }
}
