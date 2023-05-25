package Utilities;

import aquality.selenium.core.utilities.ISettingsFile;
import aquality.selenium.core.utilities.JsonSettingsFile;
import lombok.SneakyThrows;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
        if (fileReader != null) {
            return fileReader.getValue(key).toString();
        }
        return null;
    }

    public static long getLongValue(String filename, String key) {
        ISettingsFile fileReader = fileMap.get(filename);
        if (fileReader != null) {
            return Long.parseLong(fileReader.getValue(key).toString());
        }
        return 0;
    }

    public static int getIntValue(String filename, String key){
        ISettingsFile fileReader = fileMap.get(filename);
        if(fileReader != null)
            return Integer.parseInt(fileReader.getValue(key).toString());
        return  0;
    }
}
