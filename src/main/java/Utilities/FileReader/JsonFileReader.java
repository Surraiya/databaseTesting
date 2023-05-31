package Utilities.FileReader;

import aquality.selenium.core.utilities.ISettingsFile;
import aquality.selenium.core.utilities.JsonSettingsFile;
import lombok.SneakyThrows;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

public class JsonFileReader {
    private static final Map<String, ISettingsFile> fileMap = new HashMap<>();

    static {
        loadJsonFiles();
    }

    @SneakyThrows
    private static void loadJsonFiles() {
        Files.walk(Path.of(""))
                .filter(Files::isRegularFile)
                .filter(file -> file.getFileName().toString().endsWith(".json"))
                .forEach(file -> {
                    String filename = file.getFileName().toString().replace(".json","");
                    ISettingsFile configFileReader = new JsonSettingsFile(file.getFileName().toString());
                    fileMap.put(filename, configFileReader);
                });
    }

    public static String getStringValue(String filename, String key) {
        ISettingsFile fileReader = fileMap.get(filename);
        return (fileReader != null) ? fileReader.getValue(key).toString() : null;
    }

    public static long getLongValue(String filename, String key) {
        ISettingsFile fileReader = fileMap.get(filename);
        return (fileReader != null) ? Long.parseLong(fileReader.getValue(key).toString()) : 0;
    }

    public static int getIntValue(String filename, String key){
        ISettingsFile fileReader = fileMap.get(filename);
        return (fileReader != null) ? Integer.parseInt(fileReader.getValue(key).toString()) : 0;
    }
}
