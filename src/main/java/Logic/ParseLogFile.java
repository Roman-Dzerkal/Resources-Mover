package Logic;

import java.io.*;
import java.util.concurrent.Callable;
import java.util.function.Consumer;
import java.util.logging.Logger;
import java.util.stream.Stream;

/**
 * Thread to parse a log.txt file.
 * Main goal - extract the paths (to textures) from file.
 * NOTE: If file doesn't exist by path - ignore.
 */
public class ParseLogFile implements Callable<Boolean> {

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
    public Boolean call() {
        try {
            Reader reader = new FileReader(logFile.getAbsolutePath());
            BufferedReader logFileReader = new BufferedReader(reader);
            Stream<String> lines = logFileReader.lines();

            lines.forEach(new Consumer<String>() {
                @Override
                public void accept(String s) {
                    if (s.contains("Can't find texture")) {
                        System.out.println(s);
                    }
                }
            });
            return true;
        } catch (Exception e) {
            CONSOLE.warning(e.getMessage());
            return false;
        }
    }
}
