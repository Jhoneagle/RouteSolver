package johneagle.routesolve.algorithm;

import johneagle.routesolve.domain.Chell;
import johneagle.routesolve.library.DataList;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class JPSTest extends PathFinding {

    @Before
    public void before() {
        super.setUp();
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