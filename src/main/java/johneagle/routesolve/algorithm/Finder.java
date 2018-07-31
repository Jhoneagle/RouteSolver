package johneagle.routesolve.algorithm;

import johneagle.routesolve.domain.Config;
import johneagle.routesolve.domain.Point;

public class Finder {
    private String[][] map = null;
    private Config properties;

    public Finder() {
    }

    public void setMap(String[][] map) {
        this.map = map;
    }

    public void setProperties(Config properties) {
        this.properties = properties;
    }

    public void getPath(int startX, int startY, int endX, int endY) {
        if (this.map == null || this.properties == null) {
            System.out.println("tarvittavia tietoja ei ole määritelty!");
            return;
        }


    }

    private Integer getAproxDistance(int fristX, int secondX, int fristY, int secondY) {
        int aproximate = (fristX - secondX) + (fristY - secondY);

        if (aproximate < 0) {
            aproximate *= -1;
        }

        return aproximate;
    }
}
