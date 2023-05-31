package Utilities.FileReader.FileKeyName;

public enum ConfigDataKeyName {
    PROJECT_NAME("/project/name"),
    ENV("/project/env"),
    BROWSER("/project/browser"),
    AUTHOR_NAME("/project/author/name"),
    AUTHOR_LOGIN("/project/author/login"),
    AUTHOR_EMAIL("/project/author/email"),
    SESSION_BUILDNUMBER("/project/session/buildNumber"),
    SESSION_KEY("/project/session/key"),
    TEST_NUMBER("/testNumber");

    public final String key;

    ConfigDataKeyName(String key){
        this.key = key;
    }
}
