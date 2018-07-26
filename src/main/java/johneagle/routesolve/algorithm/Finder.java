package johneagle.routesolve.algorithm;

import johneagle.routesolve.filesystem.Reader;

import java.util.ArrayList;

public class Finder {
    private ArrayList data;

    public Finder() {
    }

    public String solve(String name) {
        Reader reader = new Reader(name);
        this.data = reader.getData();

        return getPath();
    }

    private String getPath() {
        String result = "";



        return result;
    }
}
