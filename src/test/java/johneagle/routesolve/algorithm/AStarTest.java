package johneagle.routesolve.algorithm;

import johneagle.routesolve.domain.Chell;
import johneagle.routesolve.library.DataList;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class AStarTest extends PathFinding {

    @Before
    public void before() {
        super.setUp();
        solver = new AStar(asciiMap);
    }

    @Test
    public void allStarOne() {
        DataList<Chell> resultAStar = this.solver.getPath(1,1,6,1);
        Assert.assertEquals(5, resultAStar.size() - 1);  // size - 1 equals the amount of points in the grid that has been visited before goal.
    }

    @Test
    public void allStarTwo() {
        DataList<Chell> resultAStar = this.solver.getPath(1,10,13,9);
        Assert.assertEquals(14, resultAStar.size() - 1);  // size - 1 equals the amount of points in the grid that has been visited before goal.
    }

    @Test
    public void allStarThree() {
        DataList<Chell> resultAStar = this.solver.getPath(9,22,6,22);
        Assert.assertEquals(11, resultAStar.size() - 1);  // size - 1 equals the amount of points in the grid that has been visited before goal.
    }

    @Test
    public void allStarFourth() {
        DataList<Chell> resultAStar = this.solver.getPath(14,12,20,1);
        Assert.assertEquals(15, resultAStar.size() - 1);  // size - 1 equals the amount of points in the grid that has been visited before goal.
    }

    @Test
    public void allStarFifth() {
        DataList<Chell> resultAStar = this.solver.getPath(10,10,8,7);
        Assert.assertEquals(3, resultAStar.size() - 1);  // size - 1 equals the amount of points in the grid that has been visited before goal.
    }

    @Test
    public void allStarSix() {
        DataList<Chell> resultAStar = this.solver.getPath(20,13,20,22);
        Assert.assertEquals(9, resultAStar.size() - 1);  // size - 1 equals the amount of points in the grid that has been visited before goal.
    }

    @Test
    public void allStarSeven() {
        DataList<Chell> resultAllStar = this.solver.getPath(1,22,1,13);
        Assert.assertEquals(16, resultAllStar.size() - 1);  // size - 1 equals the amount of points in the grid that has been visited before goal.
    }

    @Test
    public void allStarEight() {
        DataList<Chell> resultAllStar = this.solver.getPath(15,10,15,9);
        Assert.assertEquals(1, resultAllStar.size() - 1);  // size - 1 equals the amount of points in the grid that has been visited before goal.
    }
}