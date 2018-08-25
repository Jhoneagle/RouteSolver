package johneagle.routesolve.algorithm;

import johneagle.routesolve.domain.Map;
import johneagle.routesolve.filesystem.Reader;
import org.junit.After;
import org.junit.Rule;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Only Super class for actual junit test classes.
 * Gives them same support for to do preparations and end work without repeating it in every one of them.
 */
public class PathFinding {

    protected File tempConfig;
    protected File tempMapFile;
    protected Finder solver;
    protected Map asciiMap;

    @Rule
    public TemporaryFolder tempFolder = new TemporaryFolder();

    public void setUp() {
        asciiMap = new Map();

        try {
            this.tempConfig = tempFolder.newFile("test.properties");
            this.tempMapFile = tempFolder.newFile("test.csv");
        } catch (IOException ex) {
            Logger.getLogger(PathFinding.class.getName()).log(Level.SEVERE, null, ex);
        }

        Reader reader = new Reader();
        this.tempMapFile.setWritable(true);
        this.tempConfig.setWritable(true);
        String map = "0000000000000000000000" + "\n" +
                "0111111011011111110110" + "\n" +
                "0000001010000000010100" + "\n" +
                "0111101011111111010110" + "\n" +
                "0010101001000101010010" + "\n" +
                "0010101111000101011110" + "\n" +
                "0010101001000101010010" + "\n" +
                "0011101011110111010110" + "\n" +
                "0010111010011101110100" + "\n" +
                "0010101011000101010110" + "\n" +
                "0110101001101101010010" + "\n" +
                "0010111100001000010110" + "\n" +
                "0010000100111010010000" + "\n" +
                "0111111111101111110110" + "\n" +
                "0000001010000000010100" + "\n" +
                "0111101011001111010110" + "\n" +
                "0010101001000101010010" + "\n" +
                "0010101111111101011110" + "\n" +
                "0010101001010101010010" + "\n" +
                "0011101011010111010110" + "\n" +
                "0010111010000101110100" + "\n" +
                "0010101011010101010110" + "\n" +
                "0110101001011101010010" + "\n" +
                "0000000000000000000000";

        insertMap(map);
        insertConfig("1", "0");

        reader.getConfigs(this.tempConfig.getAbsolutePath());
        boolean[][] matrix = reader.getMap(this.tempMapFile.getAbsolutePath());

        asciiMap.setMap(matrix);
    }

    @After
    public void restore() {
        this.tempConfig.delete();
        this.tempMapFile.delete();
    }

    private void insertConfig(String bass, String unbass) {
        FileWriter writer;
        try {
            writer = new FileWriter(this.tempConfig);
            writer.write("bassable=" + bass + "\n" +
                    "unbassable=" + unbass + "");
            writer.close();
        } catch (IOException ex) {
            Logger.getLogger(PathFinding.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void insertMap(String data) {
        FileWriter writer;
        try {
            writer = new FileWriter(this.tempMapFile);
            writer.write(data);
            writer.close();
        } catch (IOException ex) {
            Logger.getLogger(PathFinding.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}