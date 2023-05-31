package Database.DataEntity.EntityColumns;

public enum TestColumns {

    ID("id"),
    NAME("name"),
    AUTHOR_ID("author_id"),
    PROJECT_ID("project_id"),
    SESSION_ID("session_id"),
    ENV("env"),
    BROWSER("browser"),
    METHOD_NAME("method_name"),
    START_TIME("start_time"),
    END_TIME("end_time"),
    STATUS_ID("status_id");

    public final String columnName;

    TestColumns(String columnName) {
        this.columnName = columnName;
    }
}
