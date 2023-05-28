package Utilities.FileReader.FileKeyName;

public enum RegistrationKeyName {
    MIN_PASS_LENGTH("/minPassLength"),
    MIN_CAPITAL_LENGTH("/minCapitalLetter"),
    MIN_NUMERICAL_CHAR("/minNumeralChar"),
    MIN_CYRILLIC_CHAR("/minCyrillicChar"),
    MIN_EMAIL_CHAR("/minEmailChar"),
    MAX_EMAIL_CHAR("/maxEmailChar"),
    DROPDOWN_OPTION_TEXT("/dropDownOptionText"),
    NUM_OF_RANDOM_INTEREST("/numOfRandomInterest"),
    IMAGE("/image"),
    FILENAME("/filename");

    public String key;

    RegistrationKeyName(String key){
        this.key = key;
    }
}