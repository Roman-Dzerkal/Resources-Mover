package UI;

import Logic.ParseLogFile;
import Models.Model;
import javafx.scene.control.TextField;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import org.controlsfx.control.Notifications;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
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
        if (Model.sourceDirectory == null) {
            Notifications.create()
                    .title("Empty source directory")
                    .text("Select the source directory")
                    .showInformation();
            return;
        }

        DirectoryChooser targetDirectoryChooser = new DirectoryChooser();
        Model.targetDirectory = targetDirectoryChooser.showDialog(null);

        /*
         * Check if source and target folders are the same,
         * otherwise throw error notification.
         * It's necessary because we shouldn't copy files in the same folder,
         * from they were uploaded
         */
        if (Model.targetDirectory.equals(Model.sourceDirectory)) {
            Notifications.create()
                    .title("Same directories")
                    .text("Source and target directories cannot be the same!\n" +
                            "Choose another folder.")
                    .showError();
            return;
        }

        targetDirectoryTextField.setText(Model.targetDirectory.getPath());
        System.out.printf("Target directory path: %s\n", Model.targetDirectory.getPath());
    }

    public void openLogFile() {
        if (Model.sourceDirectory == null) {
            Notifications.create()
                    .title("Empty source directory")
                    .text("Select the source directory")
                    .showInformation();
        } else if (Model.targetDirectory == null) {
            Notifications.create()
                    .title("Empty target directory")
                    .text("Select the target directory")
                    .showInformation();
        }

        FileChooser logFileChooser = new FileChooser();
        logFileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Logs", "*.log"));
        Model.logFile = logFileChooser.showOpenDialog(null);
        logFileTextField.setText(Model.logFile.getPath());
        System.out.printf("Log file path: %s\n", Model.logFile.getPath());

        ExecutorService service = Executors.newCachedThreadPool();
        service.submit(new ParseLogFile(Model.logFile));
    }

    public void openAboutWindow() {
        CONSOLE.log(Level.INFO, "On action\n");
    }

    public void startCopy() {
        if (Model.sourceDirectory == null) {
            Notifications.create()
                    .title("Empty source directory")
                    .text("Select the source directory")
                    .showInformation();
        } else if (Model.targetDirectory == null) {
            Notifications.create()
                    .title("Empty target directory")
                    .text("Select the target directory")
                    .showInformation();
        } else if (Model.logFile == null) {
            Notifications.create()
                    .title("Log file not selected")
                    .text("Select a log file")
                    .showInformation();
        }
    }

    public void quit() {
        System.exit(0);
    }
}