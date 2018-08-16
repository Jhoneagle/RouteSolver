package johneagle.routesolve.ui;

import johneagle.routesolve.algorithm.Finder;
import johneagle.routesolve.domain.Chell;
import johneagle.routesolve.domain.Config;
import johneagle.routesolve.domain.Map;
import johneagle.routesolve.filesystem.Reader;
import johneagle.routesolve.library.DataList;

import java.util.Scanner;

/**
 * User Interface for the program that uses path finding algorithm to solve best way to get from point A to B in ascii grid.
 * Interface is textual so graphics are purely terminal based.
 *
 * Program implements configurations when started so they cant be chanced without closing the program.
 * Instead ascii map can be chanced as long as the basic size of it is same and it uses same ascii symbols in same work.
 * Which can be modified from configurations. Also it's possible to make multiple A to B search with same map.
 *
 * @author Johneagle
 */
public class RouteSolver {
    public static void main(String[] args) {
        Scanner lukija = new Scanner(System.in);

        Map asciiMap = new Map();
        Reader fileReader = new Reader();

        Config configs = fileReader.getConfigs("config/config.properties");

        if (configs == null) {
            System.out.println("väärin määritelty config file!");
        }

        asciiMap.setProperties(configs);
        instructions();

        Finder solver = new Finder(asciiMap);

        while(true) {
            System.out.print("> ");
            String komento = lukija.nextLine();

            if (komento.contains("komennot")) {
                instructions();
            } else if (komento.contains("sammuta")) {
                break;
            } else if (komento.contains("lisaa kartta")) {
                toAddMap(lukija, fileReader, solver);
            } else if (komento.contains("ratkaise reitti")) {
                toSolve(lukija, solver);
            } else if (komento.contains("timeTest")) {
                speedTesting(lukija, solver);
            } else if (komento.contains("memoryTest")) {
                memoryTesting(lukija, solver);
            } else {
                System.out.println("tuntematon komento!");
            }
        }
    }

    private static void instructions() {
        System.out.println("komennot: ");
        System.out.println("'lisaa kartta' - asetaaksesi karttan");
        System.out.println("'ratkaise reitti' - selvittääksesi reitin");
        System.out.println("'komennot' - saadaksesi tämän");
        System.out.println("'sammuta' - sammuttaaksesi ohjelman");
    }

    private static void toAddMap(Scanner lukija, Reader fileReader, Finder solver) {
        System.out.print("anna kartta tiedoston nimi: ");
        String fileName = lukija.nextLine();

        char[][] map = fileReader.getMap("mapFiles/" + fileName);

        if (map == null) {
            System.out.println("yritä uudelleen!");
        } else {
            solver.getAsciiMap().setMap(map);
        }
    }

    private static void toSolve(Scanner lukija, Finder solver) {
        System.out.print("aloitus x-koordinaatti: ");
        int startX = Integer.parseInt(lukija.nextLine());

        System.out.print("aloitus y-koordinaatti: ");
        int startY = Integer.parseInt(lukija.nextLine());

        System.out.print("lopetus x-koordinaatti: ");
        int endX = Integer.parseInt(lukija.nextLine());

        System.out.print("lopetus y-koordinaatti: ");
        int endY = Integer.parseInt(lukija.nextLine());

        DataList<Chell> resultAStar = solver.getPathAStar(startX, startY, endX, endY);

        if (resultAStar != null) {
            System.out.println("A*");
            System.out.println("tarvittavia askelia: " + (resultAStar.size() - 1));
            System.out.println("pituus oktaalilla liikkumisella: " + resultAStar.get(0).getDistanceToStart());
        } else {
            System.out.println("tarvittavia tietoja ei ole määritelty!");
        }

        DataList<Chell> resultJPS = solver.getPathJPS(startX, startY, endX, endY);

        if (resultJPS != null) {
            System.out.println("JPS");
            System.out.println("tarvittavia hyppyjä: " + (resultJPS.size() - 1));
            System.out.println("pituus oktaalilla liikkumisella: " + resultJPS.get(0).getDistanceToStart());
        } else {
            System.out.println("tarvittavia tietoja ei ole määritelty!");
        }
    }

    /**
     * Method is for testing time consumption off the path finding algorithm with different parameters.
     */
    private static void speedTesting(Scanner lukija, Finder solver) {
        System.out.print("aloitus x-koordinaatti: ");
        int startX = Integer.parseInt(lukija.nextLine());

        System.out.print("aloitus y-koordinaatti: ");
        int startY = Integer.parseInt(lukija.nextLine());

        System.out.print("lopetus x-koordinaatti: ");
        int endX = Integer.parseInt(lukija.nextLine());

        System.out.print("lopetus y-koordinaatti: ");
        int endY = Integer.parseInt(lukija.nextLine());

        for (int i = 0; i < 10; i++) {
            long aikaAlussa = System.currentTimeMillis();

            DataList<Chell> result = solver.getPathAStar(startX, startY, endX, endY);

            long aikaLopussa = System.currentTimeMillis();
            System.out.println("kierros: " + (i + 1) + ".");
            System.out.println("Operaatioon kului aikaa: " + (aikaLopussa - aikaAlussa) + "ms.");
        }
    }

    /**
     * Method is for testing memory consumption off the path finding algorithm with different parameters.
     */
    private static void memoryTesting(Scanner lukija, Finder solver) {
        System.out.print("aloitus x-koordinaatti: ");
        int startX = Integer.parseInt(lukija.nextLine());

        System.out.print("aloitus y-koordinaatti: ");
        int startY = Integer.parseInt(lukija.nextLine());

        System.out.print("lopetus x-koordinaatti: ");
        int endX = Integer.parseInt(lukija.nextLine());

        System.out.print("lopetus y-koordinaatti: ");
        int endY = Integer.parseInt(lukija.nextLine());

        DataList<Chell> result = solver.getPathAStar(startX, startY, endX, endY);

        // Get the Java runtime
        Runtime runtime = Runtime.getRuntime();
        // Run the garbage collector
        runtime.gc();
        // Calculate the used memory

        long memory = runtime.totalMemory() - runtime.freeMemory();
        System.out.println("Used memory in bytes: " + memory);
        System.out.println("Used memory in megabytes: " +  ((double) memory / (1024L * 1024L)));
    }
}
