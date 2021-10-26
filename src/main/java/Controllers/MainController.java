package Controllers;

import Models.Model;
import javafx.event.ActionEvent;
import javafx.scene.control.TextField;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import org.apache.commons.io.FileUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.text.DateFormat;
import java.text.Format;
import java.util.ArrayList;
import java.util.Date;
import java.util.Formatter;
import java.util.List;
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

        if (Model.sourceDirectory == null) {
            CONSOLE.log(Level.WARNING, "No source folder selected!");
        }
        sourceDirectoryTextField.setText(Model.sourceDirectory.getPath());
    }

    public void openTargetDirectory() {
        DirectoryChooser targetDirectoryChooser = new DirectoryChooser();
        Model.targetDirectory = targetDirectoryChooser.showDialog(null);

        if (Model.targetDirectory == null) {
            CONSOLE.log(Level.WARNING, "No target folder selected!");
        }

        targetDirectoryTextField.setText(Model.targetDirectory.getPath());
    }

    public void openLogFile() {
        FileChooser logFileChooser = new FileChooser();
        logFileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Logs", "*.log"));
        Model.logFile = logFileChooser.showOpenDialog(null);

        if (Model.logFile == null) {
            CONSOLE.log(Level.WARNING, "No log file selected!");
        }

        logFileTextField.setText(Model.logFile.getPath());
    }

    public void openAboutWindow() {
        CONSOLE.log(Level.INFO, "On action\n");
    }

    public void startCopy() throws IOException {

    }

    public void parseLogFile() {

    }

    public void quit() {
        System.exit(0);
    }

    // TODO: поменять массив на объект
    public void save() throws IOException {
        JSONObject data = new JSONObject();
        JSONArray ja = new JSONArray();
        ja.put("data");
        data.put("source_directory", Model.sourceDirectory);
        data.put("target_directory", Model.targetDirectory);
        data.put("log_file", Model.logFile.getPath());
        ja.put(data);

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save data");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("TXT", "*.json"));
        File file = fileChooser.showSaveDialog(null);

        if (file != null) {
            PrintWriter writer = new PrintWriter(file, StandardCharsets.UTF_8);
            writer.print(ja);
            writer.close();
        }

        System.out.println(ja);
    }

    public void open() throws IOException{
        FileChooser openJson = new FileChooser();
        openJson.getExtensionFilters().add(new FileChooser.ExtensionFilter("TXT", "*.json"));
        File jsonFile = openJson.showOpenDialog(null);
        String json = FileUtils.fileRead(jsonFile.getAbsolutePath());
    }
}