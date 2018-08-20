package johneagle.routesolve.domain;

import johneagle.routesolve.filesystem.Reader;
import org.junit.*;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MapTest {

    private Map asciiMap;
    private File tempConfig;
    private File tempMapFile;
    private Reader reader;

    @Rule
    public TemporaryFolder tempFolder = new TemporaryFolder();

    @Before
    public void setUp() throws Exception {
        this.asciiMap = new Map();

        try {
            this.tempConfig = tempFolder.newFile("test.properties");
            this.tempMapFile = tempFolder.newFile("test.csv");
        } catch (IOException ex) {
            Logger.getLogger(MapTest.class.getName()).log(Level.SEVERE, null, ex);
        }

        this.reader = new Reader();
        this.tempMapFile.setWritable(true);
        String map = "10011" + "\n" +
                "11010" + "\n" +
                "01110" + "\n" +
                "11010" + "\n" +
                "10011";

        insertMap(map);
        char[][] matrix = this.reader.getMap(this.tempMapFile.getAbsolutePath());

        this.tempConfig.setWritable(true);
        insertConfig("5", "5", "1", "0");

        Config configs = this.reader.getConfigs(this.tempConfig.getAbsolutePath());

        this.asciiMap.setProperties(configs);
        this.asciiMap.setMap(matrix);
    }

    @Test
    public void dataOk() {
        Assert.assertNotNull(this.asciiMap.getProperties());
        Assert.assertNotNull(this.asciiMap.getMap());

        Assert.assertEquals(5, this.asciiMap.getMapHeight());
        Assert.assertEquals(5, this.asciiMap.getMapWeight());
    }

    @Test
    public void hashTableToLine() {
        Assert.assertEquals(0, this.asciiMap.hash(0, 0));
        Assert.assertEquals(24, this.asciiMap.hash(4, 4));
        Assert.assertEquals(20, this.asciiMap.hash(0, 4));
        Assert.assertEquals(17, this.asciiMap.hash(2, 3));
        Assert.assertEquals(14, this.asciiMap.hash(4, 2));
        Assert.assertEquals(5, this.asciiMap.hash(0, 1));
        Assert.assertEquals(11, this.asciiMap.hash(1, 2));
        Assert.assertEquals(3, this.asciiMap.hash(3, 0));
    }

    @Test
    public void valuesOfMovement() {
        Assert.assertFalse(1 != this.asciiMap.getValueForMovement(0,0, 1, 0));
        Assert.assertFalse(1 != this.asciiMap.getValueForMovement(2,4, 2, 3));
        Assert.assertFalse(Math.sqrt(2) != this.asciiMap.getValueForMovement(0,0, 1, -1));
        Assert.assertFalse(Math.sqrt(2) != this.asciiMap.getValueForMovement(2,4, 3, 3));
        Assert.assertFalse(0 != this.asciiMap.getValueForMovement(2,4, 0, 0));
    }

    @Test
    public void whereCordinates() {
        Assert.assertTrue(this.asciiMap.isInsideMap(0, 0));
        Assert.assertTrue(this.asciiMap.isInsideMap(4, 4));
        Assert.assertTrue(this.asciiMap.isInsideMap(2, 3));
        Assert.assertTrue(this.asciiMap.isInsideMap(4, 1));
        Assert.assertTrue(this.asciiMap.isInsideMap(1, 1));
    }

    @Test
    public void whereCordinates2() {
        Assert.assertFalse(this.asciiMap.isInsideMap(-1, 0));
        Assert.assertFalse(this.asciiMap.isInsideMap(3, 5));
        Assert.assertFalse(this.asciiMap.isInsideMap(10, 1));
        Assert.assertFalse(this.asciiMap.isInsideMap(4, -1));
        Assert.assertFalse(this.asciiMap.isInsideMap(5, 2));
    }

    @Test
    public void whereCordinates3() {
        Assert.assertFalse(this.asciiMap.isInsideMap(-1, -1));
        Assert.assertFalse(this.asciiMap.isInsideMap(10, 6));
        Assert.assertFalse(this.asciiMap.isInsideMap(2, 5));
        Assert.assertFalse(this.asciiMap.isInsideMap(-4, 0));
        Assert.assertFalse(this.asciiMap.isInsideMap(12, 8));
    }

    @Test
    public void estimates() {
        Assert.assertFalse(1 != this.asciiMap.getAproxDistance(0, 0, 1, 0));
        Assert.assertFalse((3 + (Math.sqrt(2) - 1)) != this.asciiMap.getAproxDistance(1, 2, 6, 3));
        Assert.assertFalse(0 != this.asciiMap.getAproxDistance(0, 0, 0, 0));
        Assert.assertFalse((2 + (Math.sqrt(2) - 1) * 2) != this.asciiMap.getAproxDistance(7, 5, 5, 7));
        Assert.assertFalse((1 + (Math.sqrt(2) - 1)) != this.asciiMap.getAproxDistance(6, 5, 3, 2));
    }

    @Test
    public void estimates2() {
        Assert.assertFalse((9 + (Math.sqrt(2) - 1)) != this.asciiMap.getAproxDistance(3, 12, 8, 9));
        Assert.assertFalse((15 + (Math.sqrt(2) - 1) * 8) != this.asciiMap.getAproxDistance(5, 20, 1, 9));
        Assert.assertFalse((5 + (Math.sqrt(2) - 1) * 3) != this.asciiMap.getAproxDistance(5, 8, 3, 8));
        Assert.assertFalse((17 + (Math.sqrt(2) - 1) * 7) != this.asciiMap.getAproxDistance(7, 0, 17, 0));
        Assert.assertFalse((9 + (Math.sqrt(2) - 1) * 2) != this.asciiMap.getAproxDistance(4, 13, 19, 17));
    }

    @Test
    public void terrain() {
        char[][] map = this.asciiMap.getMap();
        map[1][2] = 'x';
        map[4][4] = 'y';
        this.asciiMap.setMap(map);

        for (int y = 0; y < 5; y++) {
            for (int x = 0; x < 5; x++) {
                char current = map[y][x];

                if (current == '1') {
                    Assert.assertEquals(1, (long) this.asciiMap.isWalkable(x, y));
                } else if (current == '0') {
                    Assert.assertEquals(-1, (long) this.asciiMap.isWalkable(x, y));
                } else {
                    Assert.assertEquals(0, (long) this.asciiMap.isWalkable(x, y));
                }
            }
        }
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
            Logger.getLogger(MapTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void insertMap(String data) {
        FileWriter writer;
        try {
            writer = new FileWriter(this.tempMapFile);
            writer.write(data);
            writer.close();
        } catch (IOException ex) {
            Logger.getLogger(MapTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}