package prj.another.side.recourcesmover;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HelloController {
    private final Logger  CONSOLE = Logger.getLogger("HelloController");
    private static String pathFromFolder;
    private static String pathWhereFolder;
    private static String pathToLogFile;
    private static List<String> pathsList;


    public void openFromFolder(ActionEvent actionEvent) {
        CONSOLE.log(Level.INFO, "Open from folder\n");
    }

    public void openWhereFolder(ActionEvent actionEvent) {
        CONSOLE.log(Level.INFO, "Open where folder\n");
    }

    public void openLogFile(ActionEvent actionEvent) {
        CONSOLE.log(Level.INFO, "Open log file\n");
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