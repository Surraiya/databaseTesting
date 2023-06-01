package Utilities;

import org.apache.commons.lang3.RandomStringUtils;
import org.passay.*;

import java.util.Arrays;
import java.util.List;

import static Utilities.RandomGenerator.getRandomInteger;

public class RegistrationCardDataGenerator {

    public static String getRandomPassword(int minLength,
                                           String emailName,
                                           int minCapitalLetter,
                                           int minNumeralChar,
                                           int minCyrillicChar) {

        int minAlphabets = minLength - minCapitalLetter - minNumeralChar - minCyrillicChar;

        PasswordGenerator passwordGenerator = new PasswordGenerator();
        return passwordGenerator.generatePassword(minLength,
                cyrillicRule(minCyrillicChar),
                capitalLetterRule(minCapitalLetter),
                alphabeticRule(minAlphabets),
                numericalRule(minNumeralChar)).concat(emailChar(emailName));
    }

    private static CharacterRule cyrillicRule(int minAlphabets){
        CharacterData characterData = EnglishCharacterData.LowerCase;
        CharacterRule rule = new CharacterRule(characterData);
        rule.setNumberOfCharacters(minAlphabets);
        return rule;
    }

    private static CharacterRule capitalLetterRule(int minCapitalLetter){
        CharacterData characterData = EnglishCharacterData.UpperCase;
        CharacterRule rule = new CharacterRule(characterData);
        rule.setNumberOfCharacters(minCapitalLetter);
        return rule;
    }

    private static CharacterRule numericalRule(int minNumericalChar){
        CharacterData characterData = EnglishCharacterData.Digit;
        CharacterRule rule = new CharacterRule(characterData);
        rule.setNumberOfCharacters(minNumericalChar);
        return rule;
    }

    private static String emailChar(String email){
        return String.valueOf(getRandomInteger(email.length()));
    }

    private static CharacterRule alphabeticRule(int minCyrillicChar){
        CharacterData characterData = CyrillicCharacterData.LowerCase;
        CharacterRule rule = new CharacterRule(characterData);
        rule.setNumberOfCharacters(minCyrillicChar);
        return rule;
    }

    public static String getEmailName(int minLength, int maxLength) {
        return RandomStringUtils.randomAlphabetic(minLength, maxLength).toLowerCase();
    }

    public static String getEmailDomain() {
        List<String> domains = Arrays.stream(domainsList.values())
                .map(Enum::name)
                .toList();
        return domains.get(getRandomInteger(domains.size()));
    }

    public enum domainsList {
        yahoo,
        gmail,
        hotmail,
        aol,
        icloud,
        outlook
    }
}
