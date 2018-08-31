package johneagle.routesolve.ui;

import johneagle.routesolve.algorithm.AStar;
import johneagle.routesolve.algorithm.Finder;
import johneagle.routesolve.algorithm.JPS;
import johneagle.routesolve.domain.Chell;
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
    private static Map asciiMap;

    public static void main(String[] args) {
        Scanner lukija = new Scanner(System.in);

        asciiMap = new Map();
        Reader fileReader = new Reader();
        fileReader.getConfigs("config/config.properties");

	// if want performance testing
        if (args.length >= 4) {
            String test = args[2];
            String mapName = args[0];

            boolean[][] map = fileReader.getMap("mapFiles/" + mapName);
            DataList<String> scenarios = fileReader.readAll(args[1]);

            if (map == null) {
                System.out.println("Karttaa ei löydetty!");
                return;
            } else {
                asciiMap.setMap(map);
            }

            testPerformance(test, scenarios, args[3]);

            return;
        }

	//otherwise normal run
	
        instructions();
        while(true) {
            System.out.print("> ");
            String komento = lukija.nextLine();

            if (komento.contains("komennot")) {
                instructions();
            } else if (komento.contains("sammuta")) {
                break;
            } else if (komento.contains("lisaa kartta")) {
                toAddMap(lukija, fileReader);
            } else if (komento.contains("ratkaise reitti")) {
                toSolve(lukija);
            } else {
                System.out.println("Tuntematon komento!");
            }
        }
    }

    private static void instructions() {
        System.out.println("Komennot: ");
        System.out.println("'lisaa kartta' - Asetaaksesi kartan");
        System.out.println("'ratkaise reitti' - Selvittääksesi reitin");
        System.out.println("'komennot' - Saadaksesi tämän");
        System.out.println("'sammuta' - Sammuttaaksesi ohjelman");
    }

    private static void toAddMap(Scanner lukija, Reader fileReader) {
        System.out.print("Anna kartta tiedoston nimi: ");
        String fileName = lukija.nextLine();

        boolean[][] map = fileReader.getMap("mapFiles/" + fileName);

        if (map == null) {
            System.out.println("Tuntematon merkki löydetty. Yritä uudelleen!");
        } else {
            asciiMap.setMap(map);
            System.out.println("Kartta lisätty onnistuneesti.");
        }
    }


    private static int readInt(Scanner lukija, String msg) {
        int result;
        System.out.print(msg);

        while (true) {
            String number = lukija.nextLine();

            try {
                result = Integer.parseInt(number);
                return result;
            } catch (Exception e) {
                System.out.println("Virheellinen arvo!");
                System.out.print(msg);
            }
        }
    }

    private static void toSolve(Scanner lukija) {
        System.out.print("JPS vai A*? ");
        String which = lukija.nextLine();
        Finder solver;

        int startX = readInt(lukija, "Aloitus x-koordinaatti: ");
        int startY = readInt(lukija, "Aloitus y-koordinaatti: ");
        int endX = readInt(lukija, "Lopetus x-koordinaatti: ");
        int endY = readInt(lukija, "Lopetus y-koordinaatti: ");

        if (which.toLowerCase().contains("jps")) {
            solver = new JPS(asciiMap);
            DataList<Chell> resultJPS = solver.getPath(startX, startY, endX, endY);

            if (resultJPS != null) {
                System.out.println("JPS");
                if (resultJPS.size() > 1) {
                    System.out.println("Tarvittavia hyppyjä: " + (resultJPS.size() - 1));
                    System.out.println("Pituus oktaalilla liikkumisella: " + resultJPS.get(0).getDistanceToStart());
                } else {
                    System.out.println("Reittiä ei löydy!");
                }
            } else {
                System.out.println("Tarvittavia tietoja ei ole määritelty!");
            }
        } else {
            solver = new AStar(asciiMap);
            DataList<Chell> resultAStar = solver.getPath(startX, startY, endX, endY);

            if (resultAStar != null) {
                System.out.println("A*");
                if (resultAStar.size() > 1) {
                    System.out.println("Tarvittavia askelia: " + (resultAStar.size() - 1));
                    System.out.println("Pituus oktaalilla liikkumisella: " + resultAStar.get(0).getDistanceToStart());
                } else {
                    System.out.println("Reittiä ei löydy!");
                }
            } else {
                System.out.println("Tarvittavia tietoja ei ole määritelty!");
            }
        }
    }

    /**
     * Testing performance from now on.
     */
    private static void testPerformance(String test, DataList<String> scenarios, String which) {
        Finder solver;

        if (which.toLowerCase().contains("jps")) {
            solver = new JPS(asciiMap);
        } else {
            solver = new AStar(asciiMap);
        }

        if (test.contains("time")) {
            speedTesting(scenarios, solver);
        } else if (test.contains("memory")) {
            memoryTesting(scenarios, solver);
        } else {
            System.out.println("Testin tyyppiä ei tunnistettu!");
        }
    }

    /**
     * Method is for testing time consumption off the path finding algorithm with different parameters.
     */
    private static void speedTesting(DataList<String> scenarios, Finder solver) {
        for (int i = 0; i < scenarios.size(); i++) {
            String[] line = scenarios.get(i).split("\t");

            int startX = Integer.parseInt(line[4]);
            int startY = Integer.parseInt(line[5]);
            int endX = Integer.parseInt(line[6]);
            int endY = Integer.parseInt(line[7]);

            double sum = 0;
            DataList<Chell> result = solver.getPath(startX, startY, endX, endY);

            for (int index = 0; index < 10; index++) {
                long timeInBegin = System.nanoTime();

                solver.getPath(startX, startY, endX, endY);

                long timeInEnd = System.nanoTime();
                double elapsedTime = timeInEnd - timeInBegin;

                sum += elapsedTime;
            }

            sum /= 10;
            System.out.println(result.get(0).getDistanceToStart() + " - " + (sum / 1000000) + " ms");
        }
    }

    /**
     * Method is for testing memory consumption off the path finding algorithm with different parameters.
     */
    private static void memoryTesting(DataList<String> scenarios, Finder solver) {
        for (int i = 0; i < scenarios.size(); i++) {
            String[] line = scenarios.get(i).split("\t");

            int startX = Integer.parseInt(line[4]);
            int startY = Integer.parseInt(line[5]);
            int endX = Integer.parseInt(line[6]);
            int endY = Integer.parseInt(line[7]);

            double sum = 0;
            DataList<Chell> result = solver.getPath(startX, startY, endX, endY);

            // Get the Java runtime
            Runtime runtime = Runtime.getRuntime();

            // Run the garbage collector before starting
            runtime.gc();

            for (int index = 0; index < 10; index++) {
                solver.getPath(startX, startY, endX, endY);

                // Run the garbage collector to get usd memory
                runtime.gc();

                // Calculate the used memory
                long memory = runtime.totalMemory() - runtime.freeMemory();
                sum += memory;
            }

            sum /= 10;
            System.out.println(result.get(0).getDistanceToStart() + " - " + (sum / (1024L * 1024L)) + " MB");
        }
    }
}
