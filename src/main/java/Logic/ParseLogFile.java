package Logic;

import Models.Model;

import java.io.*;

/**
 * Thread to parse a log.txt file.
 * Main goal - extract the paths (to textures) from file.
 * NOTE: If file doesn't exist by path - ignore.
 */
public class ParseLogFile implements Runnable {

    private final File logFile;

    public ParseLogFile(File logFile) {
        this.logFile = logFile;
    }

    @Override
    public void run() {
        Reader logFileReader = null;
        try {
            logFileReader = new FileReader(logFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        BufferedReader bufferedReader = new BufferedReader(logFileReader);
        bufferedReader.lines().forEach(s -> {
            if (s.contains("! Can't find texture")) {
                String extractedPath = extractPath(s);
                Model.pathList.add(Model.sourceDirectory + extractedPath);
            }
        });

        for (String s : Model.pathList) {
            System.out.println(s);
        }
    }

    private String extractPath(String path) {
        int firstPosition = path.indexOf('\'');
        int secondPosition = path.indexOf('\'', firstPosition + 1);
        int lastPosition = path.lastIndexOf('\'');
        return path.substring(secondPosition + 1, lastPosition);
    }
}
