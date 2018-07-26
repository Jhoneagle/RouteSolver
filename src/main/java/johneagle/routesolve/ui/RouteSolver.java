package johneagle.routesolve.ui;

import johneagle.routesolve.algorithm.Finder;

import java.util.Scanner;

/**
 *
 * @author Joni
 */
public class RouteSolver {

    public static void main(String[] args) {
        Scanner lukija = new Scanner(System.in);

        System.out.print("Anna Labyrintti: ");
        String csv = lukija.nextLine();

        Finder route = new Finder();
        String result = route.solve(csv);

        System.out.println("path is " + result);
    }
}
