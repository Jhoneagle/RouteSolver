package johneagle.routesolve.algorithm;

import johneagle.routesolve.TempFile;
import johneagle.routesolve.domain.Chell;
import johneagle.routesolve.domain.Map;
import johneagle.routesolve.filesystem.Reader;
import johneagle.routesolve.library.DataList;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JPSTest extends TempFile {

    private Finder solver;
    private Map asciiMap;

    @Before
    public void setUp() {
        asciiMap = new Map();

        try {
            this.tempConfig = tempFolder.newFile("test.properties");
            this.tempMapFile = tempFolder.newFile("test.csv");
        } catch (IOException ex) {
            Logger.getLogger(TempFile.class.getName()).log(Level.SEVERE, null, ex);
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
        solver = new JPS(asciiMap);
    }

    @Test
    public void jPSOne() {
        DataList<Chell> resultJPS = this.solver.getPath(1,1,6,1);
        Assert.assertEquals(1, resultJPS.size() - 1); // size - 1 equals the amount of jumps needed to achieve goal.
    }

    @Test
    public void jPSTwo() {
        DataList<Chell> resultJPS = this.solver.getPath(1,10,13,9);
        Assert.assertEquals(10, resultJPS.size() - 1); // size - 1 equals the amount of jumps needed to achieve goal.
    }

    @Test
    public void jPSThree() {
        DataList<Chell> resultJPS = this.solver.getPath(9,22,6,22);
        Assert.assertEquals(8, resultJPS.size() - 1); // size - 1 equals the amount of jumps needed to achieve goal.
    }

    @Test
    public void jPSFourth() {
        DataList<Chell> resultJPS = this.solver.getPath(14,12,20,1);
        Assert.assertEquals(10, resultJPS.size() - 1); // size - 1 equals the amount of jumps needed to achieve goal.
    }

    @Test
    public void jPSFifth() {
        DataList<Chell> resultJPS = this.solver.getPath(10,10,8,7);
        Assert.assertEquals(1, resultJPS.size() - 1); // size - 1 equals the amount of jumps needed to achieve goal.
    }

    @Test
    public void jPSSix() {
        DataList<Chell> resultJPS = this.solver.getPath(20,13,20,22);
        Assert.assertEquals(7, resultJPS.size() - 1); // size - 1 equals the amount of jumps needed to achieve goal.
    }

    @Test
    public void jPSSeven() {
        DataList<Chell> resultJPS = this.solver.getPath(1,22,1,13);
        Assert.assertEquals(10, resultJPS.size() - 1); // size - 1 equals the amount of jumps needed to achieve goal.
    }

    @Test
    public void jPSEight() {
        DataList<Chell> resultJPS = this.solver.getPath(15,10,15,9);
        Assert.assertEquals(0, resultJPS.size() - 1); // size - 1 equals the amount of jumps needed to achieve goal.
    }
}