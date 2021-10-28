package Models;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Model {
    private final String[] extensions = {"*.thm", "*.dds"};

    public static File logFile;
    public static File sourceDirectory;
    public static File targetDirectory;
    public static List<String> pathList = new ArrayList<>();

}
