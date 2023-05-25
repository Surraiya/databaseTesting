package Database.DataEntity.EntityColumns;

public enum AuthorColumns {

    ID ("id"),
    NAME ("name"),
    LOGIN ("login"),
    EMAIL ("email");

    public final String columnName;

    AuthorColumns(String columnName){
        this.columnName = columnName;
    }
}
