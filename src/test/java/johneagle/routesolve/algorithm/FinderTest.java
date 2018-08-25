package johneagle.routesolve.algorithm;

import johneagle.routesolve.domain.Chell;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class FinderTest extends PathFinding {

    @Before
    public void before() {
        super.setUp();
        solver = new AStar(asciiMap);
    }

    @Test
    public void start() {
        Chell shouldBe = new Chell(asciiMap.getMapWeight() - 1, asciiMap.getMapHeight() - 1);
        Chell goal = solver.prepare(1, 1, shouldBe.getX(), shouldBe.getY());

        Assert.assertTrue(goal.equals(shouldBe));
    }
}
