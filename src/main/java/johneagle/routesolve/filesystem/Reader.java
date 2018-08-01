package johneagle.routesolve.filesystem;

import johneagle.routesolve.domain.Config;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Properties;

public class Reader {

    public Reader() {
    }

    private ArrayList<String> readAll(String fileName) {
        ArrayList<String> lines = new ArrayList();

        try {
            Files.lines(Paths.get("mapFiles/" + fileName)).forEach(rivi -> lines.add(rivi));
        } catch (Exception e) {
            System.out.println("Virheellinen tiedoston nimi!");
        }

        return lines;
    }

    public char[][] getMap(String mapfile) {
        ArrayList<String> lines = readAll(mapfile);
        int rows = lines.size();

        if (rows == 0) {
            return null;
        }

        String[] line = lines.get(0).split(";");
        int columns = line.length;
        char[][] map = new char[rows][columns];

        for (int y = 1; y < rows; y++) {
            for (int x = 0; x < columns; x++) {
                map[y][x] = line[x].charAt(0);
            }

            line = lines.get(y).split(";");
        }

        return map;
    }

    public Config getConfigs() {
        Properties properties = new Properties();

        try {
            properties.load(new FileInputStream("config/config.properties"));

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
        } catch(Exception e) {
            System.out.println("väärin määritelty config file!");
            return null;
        }
    }
}
