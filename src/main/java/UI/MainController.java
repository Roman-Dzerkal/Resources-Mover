package UI;

import Logic.ParseLogFile;
import Models.Model;
import javafx.scene.control.TextField;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import org.apache.commons.io.FileUtils;
import org.controlsfx.control.Notifications;
import org.json.JSONObject;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MainController {
    private final Logger CONSOLE = Logger.getLogger(MainController.class.getName());


    public TextField logFileTextField;
    public TextField sourceDirectoryTextField;
    public TextField targetDirectoryTextField;

    public void initialize() {
    }


    public void openSourceDirectory() {
        DirectoryChooser sourceDirectoryChooser = new DirectoryChooser();
        Model.sourceDirectory = sourceDirectoryChooser.showDialog(null);
        sourceDirectoryTextField.setText(Model.sourceDirectory.getPath());
        System.out.printf("Source directory path: %s\n", Model.sourceDirectory.getPath());
    }

    public void openTargetDirectory() {
        DirectoryChooser targetDirectoryChooser = new DirectoryChooser();
        Model.targetDirectory = targetDirectoryChooser.showDialog(null);
        targetDirectoryTextField.setText(Model.targetDirectory.getPath());
        System.out.printf("Target directory path: %s\n", Model.targetDirectory.getPath());
    }

    public void openLogFile() {

        if (Model.sourceDirectory == null) {
            Notifications.create()
                    .title("Empty source directory")
                    .text("Select the source directory")
                    .show();
        } else if (Model.targetDirectory == null) {
            Notifications.create()
                    .title("Empty target directory")
                    .text("Select the target directory")
                    .show();
        } else {
            FileChooser logFileChooser = new FileChooser();
            logFileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Logs", "*.log"));
            Model.logFile = logFileChooser.showOpenDialog(null);
            logFileTextField.setText(Model.logFile.getPath());
            System.out.printf("Log file path: %s\n", Model.logFile.getPath());

            Thread thread = new Thread(new ParseLogFile(Model.logFile));
            thread.start();
        }
    }

    public void openAboutWindow() {
        CONSOLE.log(Level.INFO, "On action\n");
    }

    public void startCopy() throws IOException {
        Thread thread = new Thread(new ParseLogFile(Model.logFile));
        thread.start();
    }

    public void quit() {
        System.exit(0);
    }

    // TODO: поменять массив на объект
    public void save() throws IOException {
        JSONObject data = new JSONObject();
        data.put("source_directory", Model.sourceDirectory);
        data.put("target_directory", Model.targetDirectory);
        data.put("log_file", Model.logFile.getPath());
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("data", data);

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save data");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("TXT", "*.json"));
        File fileToSave = fileChooser.showSaveDialog(null);

        if (fileToSave != null) {
            PrintWriter writer = new PrintWriter(fileToSave, StandardCharsets.UTF_8);
            writer.print(jsonObject);
            writer.close();
        }
    }

    public void open() throws IOException{
        FileChooser openJson = new FileChooser();
        openJson.getExtensionFilters().add(new FileChooser.ExtensionFilter("TXT", "*.json"));
        File jsonFile = openJson.showOpenDialog(null);
        String json = FileUtils.fileRead(jsonFile.getAbsolutePath());
        JSONObject jsonObject = new JSONObject(json);
        System.out.println(jsonObject.getJSONObject("data"));
    }
}