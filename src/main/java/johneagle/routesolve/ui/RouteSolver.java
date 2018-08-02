package johneagle.routesolve.ui;

import johneagle.routesolve.algorithm.Finder;
import johneagle.routesolve.domain.Chell;
import johneagle.routesolve.domain.Config;
import johneagle.routesolve.domain.Map;
import johneagle.routesolve.filesystem.Reader;

import java.util.Scanner;
import java.util.Stack;

/**
 *
 * @author Joni
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

            if (komento.contains("ohje")) {
                instructions();
            } else if (komento.contains("lopeta")) {
                break;
            } else if (komento.contains("lisaa")) {
                toAddMap(lukija, fileReader, solver);
            } else if (komento.contains("ratkaise")) {
                toSolve(lukija, solver);
            } else {
                System.out.println("tuntematon komento!");
            }
        }
    }

    private static void instructions() {
        System.out.println("komennot: ");
        System.out.println("'lisaa' - asetaaksesi karttan");
        System.out.println("'ratkaise' - selvittääksesi reitin");
        System.out.println("'ohje' - saadaksesi tämän");
        System.out.println("'lopeta' - sammuttaaksesi ohjelman");
    }

    private static void toSolve(Scanner lukija, Finder solver) {
        System.out.print("aloitus x-koordinaatti: ");
        int startX = Integer.parseInt(lukija.nextLine()) - 1;

        System.out.print("aloitus y-koordinaatti: ");
        int startY = Integer.parseInt(lukija.nextLine()) - 1;

        System.out.print("lopetus x-koordinaatti: ");
        int endX = Integer.parseInt(lukija.nextLine()) - 1;

        System.out.print("lopetus y-koordinaatti: ");
        int endY = Integer.parseInt(lukija.nextLine()) - 1;

        Stack<Chell> result = solver.getPath(startX, startY, endX, endY);

        if (result != null) {
            System.out.println(result.size());
        } else {
            System.out.println("tarvittavia tietoja ei ole määritelty!");
        }
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
}
