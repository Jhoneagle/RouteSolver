package johneagle.routesolve.filesystem;

import johneagle.routesolve.TempFile;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ReaderTest extends TempFile {

    private Reader reader;

    @Before
    public void setUp() {
        try {
            this.tempConfig = tempFolder.newFile("test.properties");
            this.tempMapFile = tempFolder.newFile("test.csv");
        } catch (IOException ex) {
            Logger.getLogger(ReaderTest.class.getName()).log(Level.SEVERE, null, ex);
        }

        this.tempConfig.setWritable(true);
        insertConfig("1", "0");

        this.reader = new Reader();
        reader.getConfigs(tempConfig.getAbsolutePath());
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
        boolean[][] matrix = this.reader.getMap("test5.csv");

        Assert.assertNull(matrix);
    }

    @Test
    public void wrongConfig() {
        Assert.assertFalse(this.reader.getConfigs("fail.properties"));
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
        boolean[][] matrix = this.reader.getMap(this.tempMapFile.getAbsolutePath());

        Assert.assertNotNull(matrix);
    }

    @Test
    public void getData2() {
        this.tempMapFile.setWritable(true);
        String map = "";

        insertMap(map);
        boolean[][] matrix = this.reader.getMap(this.tempMapFile.getAbsolutePath());

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
        boolean[][] matrix = this.reader.getMap(this.tempMapFile.getAbsolutePath());

        for (int y = 0; y < 5; y++) {
            int sum = 0;

            for (int x = 0; x < 5; x++) {
                if (matrix[y][x]) {
                    sum++;
                }
            }

            if (y == 2) {
                Assert.assertEquals(1, sum);
            } else {
                Assert.assertEquals(2, sum);
            }
        }
    }

    @Test
    public void terrainUnknown() {
        this.tempMapFile.setWritable(true);
        String map = "10001" + "\n" +
                "01010" + "\n" +
                "00200" + "\n" +
                "01010" + "\n" +
                "10003";

        insertMap(map);
        boolean[][] matrix = this.reader.getMap(this.tempMapFile.getAbsolutePath());

        Assert.assertNull(matrix);
    }
}