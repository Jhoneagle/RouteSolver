package johneagle.routesolve.ui;

import johneagle.routesolve.algorithm.Finder;
import johneagle.routesolve.domain.Config;
import johneagle.routesolve.filesystem.Reader;

import java.util.Scanner;

/**
 *
 * @author Joni
 */
public class RouteSolver {
    public static void main(String[] args) {
        Scanner lukija = new Scanner(System.in);

        Finder solver = new Finder();
        Reader fileReader = new Reader();

        Config configs = fileReader.getConfigs();
        solver.setProperties(configs);
        instructions();

        while(true) {
            System.out.println("> ");
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
        System.out.println("aloitus x-koordinaatti: ");
        int startX = Integer.parseInt(lukija.nextLine());

        System.out.println("aloitus y-koordinaatti: ");
        int startY = Integer.parseInt(lukija.nextLine());

        System.out.println("lopetus x-koordinaatti: ");
        int endX = Integer.parseInt(lukija.nextLine());

        System.out.println("lopetus y-koordinaatti: ");
        int endY = Integer.parseInt(lukija.nextLine());

        solver.getPath(startX, startY, endX, endY);
    }

    private static void toAddMap(Scanner lukija, Reader fileReader, Finder solver) {
        System.out.println("anna kartta tiedoston nimi: ");
        String fileName = lukija.nextLine();

        String[][] map = fileReader.getMap(fileName);

        if (map == null) {
            System.out.println("yritä uudelleen!");
        } else {
            solver.setMap(map);
        }
    }
}
