package johneagle.routesolve.filesystem;

import johneagle.routesolve.domain.Config;
import org.junit.*;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ReaderTest {

    private File tempConfig;
    private File tempMapFile;
    private Reader reader;

    @Rule
    public TemporaryFolder tempFolder = new TemporaryFolder();

    @Before
    public void setUp() {
        try {
            this.tempConfig = tempFolder.newFile("test.properties");
            this.tempMapFile = tempFolder.newFile("test.csv");
        } catch (IOException ex) {
            Logger.getLogger(ReaderTest.class.getName()).log(Level.SEVERE, null, ex);
        }

        this.reader = new Reader();
    }

    @Test
    public void wrongFileName() {
        this.tempMapFile.setWritable(true);
        String map = "00000" + "\n" +
                "00000" + "\n" +
                "00000" + "\n" +
                "00000" + "\n" +
                "00000";

        insertMap(map);
        char[][] matrix = this.reader.getMap("test5.csv");

        Assert.assertNull(matrix);
    }

    @Test
    public void getData() {
        this.tempMapFile.setWritable(true);
        String map = "00000" + "\n" +
                "00000" + "\n" +
                "00000" + "\n" +
                "00000" + "\n" +
                "00000";

        insertMap(map);
        char[][] matrix = this.reader.getMap(this.tempMapFile.getAbsolutePath());

        Assert.assertNotNull(matrix);
    }

    @Test
    public void getData2() {
        this.tempMapFile.setWritable(true);
        String map = "";

        insertMap(map);
        char[][] matrix = this.reader.getMap(this.tempMapFile.getAbsolutePath());

        Assert.assertNull(matrix);
    }

    @Test
    public void getData3() {
        this.tempMapFile.setWritable(true);
        String map = "10001" + "\n" +
                "01010" + "\n" +
                "00100" + "\n" +
                "01010" + "\n" +
                "10001";

        insertMap(map);
        char[][] matrix = this.reader.getMap(this.tempMapFile.getAbsolutePath());

        for (int y = 0; y < 5; y++) {
            int sum = 0;

            for (int x = 0; x < 5; x++) {
                String found = ""+matrix[y][x];
                sum += Integer.parseInt(found);
            }

            if (y == 2) {
                Assert.assertEquals(1, sum);
            } else {
                Assert.assertEquals(2, sum);
            }
        }
    }

    @Test
    public void getConfigs() {
        this.tempConfig.setWritable(true);
        insertConfig("5", "5", "1", "0");

        Config configs = this.reader.getConfigs(this.tempConfig.getAbsolutePath());
        Assert.assertNotNull(configs);
    }

    @Test
    public void getConfigs2() {
        this.tempConfig.setWritable(true);
        insertConfig("5", "5", "1", "0");

        Config configs = this.reader.getConfigs(this.tempConfig.getAbsolutePath());

        Assert.assertEquals(5, configs.getX());
        Assert.assertEquals(5, configs.getY());
        Assert.assertEquals(1, configs.getWalkable().length);
        Assert.assertEquals(1, configs.getUnwalkable().length);
    }

    @Test
    public void getConfigs3() {
        this.tempConfig.setWritable(true);
        insertConfig("5", "a", "1", "0");

        Config configs = this.reader.getConfigs(this.tempConfig.getAbsolutePath());
        Assert.assertNull(configs);
    }

    @Test
    public void getConfigs4() {
        this.tempConfig.setWritable(true);
        insertConfig("5", "5", "1,2,3,4,5", "0,f,i,q,w");

        Config configs = this.reader.getConfigs(this.tempConfig.getAbsolutePath());

        Assert.assertNotNull(configs);
        Assert.assertEquals(5, configs.getWalkable().length);
        Assert.assertEquals(5, configs.getUnwalkable().length);
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
                    "unbassable=" + bass + "");
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