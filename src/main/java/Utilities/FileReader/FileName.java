package Utilities.FileReader;

public enum FileName {
    DATABASE("database"),
    DB_TEST_DATA("dbTestData"),
    SETTINGS("settings"),
    REGISTRATION("registrationData"),
    TIMER("timerTestData");

    public String fileName;

    FileName(String fileName){
        this.fileName = fileName;
    }
}
