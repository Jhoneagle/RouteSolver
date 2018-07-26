package johneagle.routesolve.filesystem;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Reader {

    private String fileName;

    public Reader(String fileName) {
        this.fileName = fileName;
    }

    private ArrayList readAll() {
        ArrayList lines = new ArrayList();

        try {
            Files.lines(Paths.get("csvFiles/" + this.fileName)).forEach(rivi -> lines.add(rivi));
        } catch (Exception e) {
            System.out.println("Virheellinen tiedoston nimi!");
        }

        return lines;
    }

    public ArrayList getData() {
        ArrayList lines = readAll();



        return lines;
    }
}
