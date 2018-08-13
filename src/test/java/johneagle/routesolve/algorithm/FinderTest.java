package johneagle.routesolve.algorithm;

import johneagle.routesolve.domain.Chell;
import johneagle.routesolve.domain.Config;
import johneagle.routesolve.domain.Map;
import johneagle.routesolve.filesystem.Reader;
import johneagle.routesolve.filesystem.ReaderTest;
import johneagle.routesolve.library.DataList;
import org.junit.*;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FinderTest {

    private File tempConfig;
    private File tempMapFile;
    private Finder solver;

    @Rule
    public TemporaryFolder tempFolder = new TemporaryFolder();

    @Before
    public void setUp() throws Exception {
        Map asciiMap = new Map();

        try {
            this.tempConfig = tempFolder.newFile("test.properties");
            this.tempMapFile = tempFolder.newFile("test.csv");
        } catch (IOException ex) {
            Logger.getLogger(ReaderTest.class.getName()).log(Level.SEVERE, null, ex);
        }

        Reader reader = new Reader();
        this.tempMapFile.setWritable(true);
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
        char[][] matrix = reader.getMap(this.tempMapFile.getAbsolutePath());

        this.tempConfig.setWritable(true);
        insertConfig("22", "24", "1", "0");

        Config configs = reader.getConfigs(this.tempConfig.getAbsolutePath());

        asciiMap.setProperties(configs);
        asciiMap.setMap(matrix);

        this.solver = new Finder(asciiMap);
    }

    @Test
    public void testOne() {
        DataList<Chell> result = this.solver.getPathAllstar(1,1,6,1);
        Assert.assertEquals(5, result.size() - 1);
    }

    @Test
    public void testTwo() {
        DataList<Chell> result = this.solver.getPathAllstar(1,10,13,9);
        Assert.assertEquals(19, result.size() - 1);
    }

    @Test
    public void testThree() {
        DataList<Chell> result = this.solver.getPathAllstar(9,22,6,22);
        Assert.assertEquals(15, result.size() - 1);
    }

    @Test
    public void testFourth() {
        DataList<Chell> result = this.solver.getPathAllstar(14,12,20,1);
        Assert.assertEquals(21, result.size() - 1);
    }

    @Test
    public void testFifth() {
        DataList<Chell> result = this.solver.getPathAllstar(10,10,8,7);
        Assert.assertEquals(5, result.size() - 1);
    }

    @Test
    public void testSix() {
        DataList<Chell> result = this.solver.getPathAllstar(20,13,20,22);
        Assert.assertEquals(13, result.size() - 1);
    }

    @Test
    public void testSeven() {
        DataList<Chell> result = this.solver.getPathAllstar(1,22,1,13);
        Assert.assertEquals(21, result.size() - 1);
    }

    @Test
    public void testEight() {
        DataList<Chell> result = this.solver.getPathAllstar(15,10,15,9);
        Assert.assertEquals(1, result.size() - 1);
    }

    @After
    public void restore() {
        this.tempConfig.delete();
        this.tempMapFile.delete();
    }

    private void insertConfig(String x, String y, String bass, String unbass) {
        FileWriter writer;
        try {
            writer = new FileWriter(this.tempConfig);
            writer.write("x=" + x + "\n" +
                    "y=" + y + "\n" +
                    "bassable=" + bass + "\n" +
                    "unbassable=" + unbass + "");
            writer.close();
        } catch (IOException ex) {
            Logger.getLogger(ReaderTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void insertMap(String data) {
        FileWriter writer;
        try {
            writer = new FileWriter(this.tempMapFile);
            writer.write(data);
            writer.close();
        } catch (IOException ex) {
            Logger.getLogger(ReaderTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}