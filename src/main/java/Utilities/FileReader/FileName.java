package Utilities.FileReader;

public enum FileName {
    DATABASE("database"),
    CONFIG_DATA("configData"),
    SETTINGS("settings"),
    REGISTRATION("registrationData"),
    TIMER("timerTestData");

    public final String fileName;

    FileName(String fileName){
        this.fileName = fileName;
    }
}
