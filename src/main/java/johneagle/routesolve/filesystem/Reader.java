package johneagle.routesolve.filesystem;

import johneagle.routesolve.domain.Config;

import java.io.FileInputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Properties;

/**
 * Handles all reading from configuration files and files containing ascii map.
 * Ascii grid has to be pure matrix of characters.
 * Also configuration file has to be formulated right way.
 *
 * @author Johneagle
 */
public class Reader {

    public Reader() {
    }

    /**
     * Reads the hole file and returns it as list of text (String) lines.
     *
     * @see ArrayList
     *
     * @param fileName  Name of the file with folder name first if needed.
     *
     * @return List of String/text lines
     */
    private ArrayList<String> readAll(String fileName) {
        ArrayList<String> lines = new ArrayList();

        try {
            Files.lines(Paths.get(fileName)).forEach(rivi -> lines.add(rivi));
        } catch (Exception e) {
            System.out.println("Virheellinen tiedoston nimi!");
        }

        return lines;
    }

    /**
     * First asks the lines from the file and then forms an character matrix of it.
     *
     * @see Reader#readAll(String)
     *
     * @param mapfile   Map that needs to be formulated to ascii grid.
     *
     * @return Character matrix
     */
    public char[][] getMap(String mapfile) {
        ArrayList<String> lines = readAll(mapfile);
        int rows = lines.size();

        if (rows == 0) {
            return null;
        }

        String line = lines.get(0);
        int columns = line.length();
        char[][] map = new char[rows][columns];

        for (int y = 0; y < rows; y++) {
            line = lines.get(y);

            for (int x = 0; x < columns; x++) {
                map[y][x] = line.charAt(x);
            }
        }

        return map;
    }

    /**
     * Returns Config object of the properties file that is declared by the param.
     *
     * @see Config
     *
     * @param configFileName    Name of the configuration/properties file.
     *
     * @return Config object
     */
    public Config getConfigs(String configFileName) {
        Properties properties = new Properties();

        try {
            properties.load(new FileInputStream(configFileName));

            int x = Integer.parseInt(properties.getProperty("x"));
            int y = Integer.parseInt(properties.getProperty("y"));

            String[] walk = properties.getProperty("bassable").split(",");
            String[] unwalk = properties.getProperty("unbassable").split(",");

            char[] walkable = new char[walk.length];
            char[] unwalkable = new char[unwalk.length];

            for (int i = 0; i < walk.length; i++) {
                walkable[i] = walk[i].charAt(0);
            }

            for (int i = 0; i < unwalk.length; i++) {
                unwalkable[i] = unwalk[i].charAt(0);
            }

            Config configs = new Config(x, y, walkable, unwalkable);
            return configs;
        } catch (Exception e) {
            return null;
        }
    }
}
