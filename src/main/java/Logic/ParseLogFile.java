package Logic;

import Models.Model;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Stream;

/**
 * Thread to parse a log.txt file.
 * Main goal - extract the paths (to textures) from file.
 * NOTE: If file doesn't exist by path - ignore.
 */
public class ParseLogFile implements Runnable {

    /**
     * file.log, e.g [17.10.2021 13.12.53].log
     */
    private final File logFile;

    /**
     * Logger for this class
     */
    private final Logger CONSOLE = Logger.getLogger(ParseLogFile.class.getName());


    public ParseLogFile(File logFile) {
        this.logFile = logFile;
    }

    @Override
    public void run() {
        String readFile = null; // Content of the log file
        try {
            readFile = FileUtils.fileRead(logFile.getAbsolutePath());
        } catch (IOException ioException) {
            CONSOLE.warning(ioException.getMessage());
        }

        assert readFile != null;
        List<String> t = readFile.lines().toList(); // Split log file to lines

        for (String line : t) {
            int start = line.indexOf("Can't find texture '") + "Can't find texture '".length(); //second position ' sign
            if (line.contains("Can't find texture")) { // find all lines, where specify path to texture

                // E:\S.T.A.L.K.E.R. Call of Pripyat\gamedata\textures\ + wm\wm_knife_wood2
                Model.pathList.add(Model.sourceDirectory.getAbsolutePath() + line.substring(start, line.length() - 1));
                // Result: full path to texture: E:\S.T.A.L.K.E.R. Call of Pripyat\gamedata\textures\wm\wm_knife_wood2
            }
        }
    }
}
