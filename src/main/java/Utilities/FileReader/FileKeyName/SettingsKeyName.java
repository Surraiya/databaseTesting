package Utilities.FileReader.FileKeyName;

public enum SettingsKeyName {
    START_URL("/startUrl"),
    DELAY_TIME("/delayTime");

    public String key;

    SettingsKeyName(String key){
        this.key = key;
    }
}
