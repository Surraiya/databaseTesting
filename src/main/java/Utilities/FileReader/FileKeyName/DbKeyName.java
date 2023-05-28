package Utilities.FileReader.FileKeyName;

public enum DbKeyName {
    DB_URL("/url"),
    DB_NAME("/database"),
    DB_USERNAME("/username"),
    DB_PASSWORD("/password");

    public String key;

    DbKeyName(String key){
        this.key = key;
    }
}