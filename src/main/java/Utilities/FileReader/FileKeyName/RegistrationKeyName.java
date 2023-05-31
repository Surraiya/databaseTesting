package Utilities.FileReader.FileKeyName;

public enum RegistrationKeyName {
    MIN_PASS_LENGTH("/minPassLength"),
    MIN_CAPITAL_LETTER("/minCapitalLetter"),
    MIN_NUMERICAL_CHAR("/minNumeralChar"),
    MIN_CYRILLIC_CHAR("/minCyrillicChar"),
    MIN_EMAIL_CHAR("/minEmailChar"),
    MAX_EMAIL_CHAR("/maxEmailChar"),
    NUM_OF_RANDOM_INTEREST("/numOfRandomInterest"),
    FILENAME("/filename");

    public final String key;

    RegistrationKeyName(String key){
        this.key = key;
    }
}