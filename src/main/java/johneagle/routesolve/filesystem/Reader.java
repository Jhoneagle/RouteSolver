package johneagle.routesolve.filesystem;

import johneagle.routesolve.library.DataList;

import java.io.FileInputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

/**
 * Handles all reading from configuration files and files containing ascii map.
 * Ascii grid has to be pure matrix of characters.
 * Also configuration file has to be formulated right way.
 *
 * @author Johneagle
 */
public class Reader {

    private char[] walkable;
    private char[] unwalkable;

    public Reader() {
        walkable = new char[0];
        unwalkable = new char[0];
    }

    /**
     * Reads the hole file and returns it as list of text (String) lines.
     *
     * @see DataList
     *
     * @param fileName  Name of the file with folder name first if needed.
     *
     * @return List of String/text lines
     */
    private DataList<String> readAll(String fileName) {
        DataList<String> lines = new DataList<>();

        try {
            Files.lines(Paths.get(fileName)).forEach(rivi -> lines.add(rivi));
        } catch (Exception e) {
            System.out.println("Virheellinen tiedoston nimi!");
        }

        return lines;
    }

    /**
     * First asks the lines from the file and then forms an boolean matrix of it by using the info from config file.
     * If found ascii symbol that it doesn't know then returns null.
     *
     * @see Reader#readAll(String)
     * @see Reader#typeValue(char)
     *
     * @param mapfile   Map that needs to be formulated to ascii grid.
     *
     * @return boolean matrix
     */
    public boolean[][] getMap(String mapfile) {
        DataList<String> lines = readAll(mapfile);
        int rows = lines.size();

        if (rows == 0) {
            return null;
        }

        String line = lines.get(0);
        int columns = line.length();
        boolean[][] map = new boolean[rows][columns];

        for (int y = 0; y < rows; y++) {
            line = lines.get(y);

            for (int x = 0; x < columns; x++) {
                char current = line.charAt(x);
                int value = typeValue(current);

                if (value < 0) {
                    map[y][x] = false;
                } else if (value > 0) {
                    map[y][x] = true;
                } else {
                    return null;
                }
            }
        }

        return map;
    }

    /**
     * If walkable returns 1 and if not bassable then -1. Otherwise 0 because symbol is not in the known ones.
     *
     * @param type  ascii symbol
     *
     * @return Integer
     */
    private int typeValue(char type) {
        for (int i = 0; i < walkable.length; i++) {
            if (type == walkable[i]) {
                return 1;
            }
        }

        for (int i = 0; i < unwalkable.length; i++) {
            if (type == unwalkable[i]) {
                return -1;
            }
        }

        return 0;
    }

    /**
     * Reads properties file that is declared by the param and stores the info for use.
     *
     * @param configFileName    Name of the configuration/properties file.
     */
    public void getConfigs(String configFileName) {
        Properties properties = new Properties();

        try {
            properties.load(new FileInputStream(configFileName));

            String[] walk = properties.getProperty("bassable").split(",");
            String[] unwalk = properties.getProperty("unbassable").split(",");

            walkable = new char[walk.length];
            unwalkable = new char[unwalk.length];

            for (int i = 0; i < walk.length; i++) {
                walkable[i] = walk[i].charAt(0);
            }

            for (int i = 0; i < unwalk.length; i++) {
                unwalkable[i] = unwalk[i].charAt(0);
            }
        } catch (Exception e) {
            System.out.println("väärin määritelty config file!");
        }
    }
}
