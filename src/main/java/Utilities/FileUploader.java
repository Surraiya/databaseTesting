package Utilities;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.nio.file.Path;

public class FileUploader {

    public static void uploadFile(Path filePath) {
        try {
            Robot robot = new Robot();
            StringSelection selection = new StringSelection(filePath.toString());
            robot.delay(JsonFileReader.getIntValue("settings","/delayTime"));
            Toolkit.getDefaultToolkit().getSystemClipboard().setContents(selection, null);

            robot.keyPress(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_V);
            robot.keyRelease(KeyEvent.VK_CONTROL);
            robot.keyRelease(KeyEvent.VK_V);

            robot.keyPress(KeyEvent.VK_ENTER);
            robot.keyRelease(KeyEvent.VK_ENTER);
        } catch (AWTException e) {
            throw new RuntimeException("Failed to upload file: " + filePath, e);
        }
    }
}
