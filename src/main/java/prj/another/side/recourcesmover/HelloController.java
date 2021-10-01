package prj.another.side.recourcesmover;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;

import java.io.File;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HelloController {
    private final Logger  CONSOLE = Logger.getLogger("HelloController");
    private static File pathFromFolder;
    private static File pathWhereFolder;
    private static File pathToLogFile;
    private static List<String> pathsList;
    public TextField logFilePathtf;
    public TextField whereFolderPathtf;
    public TextField fromFolderPathtf;


    public void openFromFolder(ActionEvent actionEvent) {
        CONSOLE.log(Level.INFO, "Open from folder\n");

        DirectoryChooser fromFolderDirectoryChooser = new DirectoryChooser();
        pathFromFolder = fromFolderDirectoryChooser.showDialog(null);

        if (pathFromFolder == null) {
            CONSOLE.log(Level.WARNING, "No folder selected!");
        }

        CONSOLE.log(Level.INFO, pathFromFolder.getPath());
        
        fromFolderPathtf.setText(pathFromFolder.getPath());
    }

    public void openWhereFolder(ActionEvent actionEvent) {
        CONSOLE.log(Level.INFO, "Open where folder\n");

        DirectoryChooser whereFolderDirectoryChooser = new DirectoryChooser();
        pathWhereFolder = whereFolderDirectoryChooser.showDialog(null);

        if (pathWhereFolder == null) {
            CONSOLE.log(Level.WARNING, "No folder selected!");
        }

        CONSOLE.log(Level.INFO, pathWhereFolder.getPath());

        whereFolderPathtf.setText(pathWhereFolder.getPath());
    }

    public void openLogFile(ActionEvent actionEvent) {
        CONSOLE.log(Level.INFO, "Open log file\n");

        FileChooser logFileChooser = new FileChooser();
        logFileChooser.setSelectedExtensionFilter(new FileChooser.ExtensionFilter("Logs", "*.log"));
        pathToLogFile = logFileChooser.showOpenDialog(null);

        if (pathToLogFile == null) {
            CONSOLE.log(Level.WARNING, "No log file selected!");
        }

        CONSOLE.log(Level.INFO, pathToLogFile.getPath());
        logFilePathtf.setText(pathToLogFile.getPath());
    }

    public void onAction(ActionEvent actionEvent) {
        CONSOLE.log(Level.INFO, "On action\n");
    }

    public void onMenuValidation(Event event) {
        CONSOLE.log(Level.INFO, "On menu validation\n");
    }

    public void startMove(ActionEvent actionEvent) {
        CONSOLE.log(Level.INFO, "Start\n");
    }
}