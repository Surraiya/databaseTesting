package Database.DataEntity.EntityColumns;

public enum SessionColumns {
    ID("id"),
    BUILD_NUMBER("build_number"),
    CREATED_TIME("created_time"),
    SESSION_KEY("session_key");

    public final String columnName;
    SessionColumns(String columnName){
        this.columnName = columnName;
    }
}
