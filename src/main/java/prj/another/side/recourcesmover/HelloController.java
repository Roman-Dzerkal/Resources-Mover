package prj.another.side.recourcesmover;

import javafx.scene.control.TextField;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HelloController {
    private final Logger CONSOLE = Logger.getLogger("HelloController");
    private File logFile;
    private File sourceDirectory;
    private File targetDirectory;
    private List<String> pathsList;

    public TextField logFileTextField;
    public TextField sourceDirectoryTextField;
    public TextField targetDirectoryTextField;


    public void openSourceDirectory() {
        DirectoryChooser sourceDirectoryChooser = new DirectoryChooser();
        sourceDirectory = sourceDirectoryChooser.showDialog(null);

        if (sourceDirectory == null) {
            CONSOLE.log(Level.WARNING, "No source folder selected!");
        }

        sourceDirectoryTextField.setText(sourceDirectory.getPath());
    }

    public void openTargetDirectory() {
        DirectoryChooser targetDirectoryChooser = new DirectoryChooser();
        targetDirectory = targetDirectoryChooser.showDialog(null);

        if (targetDirectory == null) {
            CONSOLE.log(Level.WARNING, "No target folder selected!");
        }

        targetDirectoryTextField.setText(targetDirectory.getPath());
    }

    public void openLogFile() {
        FileChooser logFileChooser = new FileChooser();
        logFileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Logs", "*.log"));
        logFile = logFileChooser.showOpenDialog(null);

        if (logFile == null) {
            CONSOLE.log(Level.WARNING, "No log file selected!");
        }

        logFileTextField.setText(logFile.getPath());
    }

    public void openAboutWindow() {
        CONSOLE.log(Level.INFO, "On action\n");
    }

    public void startMove() throws IOException {
        CONSOLE.log(Level.INFO, "Start\n");
        pathsList = new ArrayList<>();
        FileInputStream fstream = new FileInputStream(logFile.getPath());
        BufferedReader br = new BufferedReader(new InputStreamReader(fstream));

        String pathToResource;

        while ((pathToResource = br.readLine()) != null) {
            pathToResource = pathToResource.substring("[16.58.24] ! Can't find texture '".length());
            pathToResource = pathToResource.replace("'", "");

            pathsList.add(pathToResource);
        }
        fstream.close();
    }
}