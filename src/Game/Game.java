package Game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.regex.*;

import Utils.ConsoleColor;

public class Game {

    private static final char[] COLORS = {'R', 'V', 'B', 'C', 'J', 'M'};

    Scanner scanner;
    Color[] solutionColors;
    boolean isRunning;

    public Game ()
    {
        // Initialise un objet permettant de traiter la saisie de l'utilisateur        
        scanner = new Scanner(System.in);

        // Définit l'état de fonctionnement du jeu
        isRunning = true;

        // Initialise la partie
        init();
    }

    public void init()
    {
        // Définit la solution du jeu
        solutionColors = Color.fromString("MMCR");
    }

    public void update()
    {
        // Récupère une saisie de l'utilisateur
        // en enlevant les espaces avant et après, et en convertissant en majuscules
        String userInput = scanner.nextLine().trim().toUpperCase();

        // Vérifie si la saisie de l'utilisateur correspond au schéma attendu
        // (exactement 4 caractères parmi R, V, B, J, C et M, sans rien avant ni rien après)
        if (Pattern.matches("^[RVBJCM]{4}$", userInput)) {
            Color[] userColors = Color.fromString(userInput);
            
            ArrayList<Color> incorrectUserInputColors = new ArrayList<>();
            ArrayList<Color> incorrectSolutionColors = new ArrayList<>();
            // Compte le nombre de couleurs bien placées dans la saisie de l'utilisateur
            int correct = 0;
            for (int i = 0; i < 4; i += 1) {
                if (userColors[i] == solutionColors[i]) {
                    correct += 1;
                // Retient les couleurs qui ne sont pas bien placés pour l'étape suivante
                } else {
                    incorrectUserInputColors.add(userColors[i]);
                    incorrectSolutionColors.add(solutionColors[i]);
                }
            }
            
            // Compte le nombre de couleurs, parmi les couleurs qui ne sont pas bien placées,
            // qui sont en trop par rapport aux couleurs restantes dans la solution
            int absent = 0;
            for (Color color: Color.values()) {
                int excess = Collections.frequency(incorrectUserInputColors, color) - Collections.frequency(incorrectSolutionColors, color);
                // Si le nombre de couleurs en trop est négatif, c'est donc qu'il manque des couleurs de la solution
                // dans la proposition de l'utilisateur
                // Il ne faut pas les compter parmi les couleurs de la proposition absentes de la solution
                if (excess > 0) {
                    absent += excess;
                }
            }

            // Si l'utilisateur a rentré la combinaison exacte
            if (correct == 4) {
                System.out.println(ConsoleColor.GREEN + "Bravo! Vous avez trouvé la bonne combinaison!" + ConsoleColor.RESET);

                // Demande à l'utilisateur s'il souhait recommencer une nouvelle partie
                System.out.println("\nUne autre partie? (O/N)");

                // Tant que la saisie de l'utilisateur ne correspond pas à "O" ou "N",
                // demande à l'utilisateur de rentrer "O" ou "N"
                String input = "";
                while (!Pattern.matches("^[onON]$", input)) {
                    input = scanner.nextLine().toUpperCase();
                }
                // Si oui, réinitialise la partie
                if ("O".equals(input)) {
                    init();
                // Sinon, arrête le jeu
                } else {
                    terminate();
                }
                return;
            }

            int misplaced = 4 - correct - absent;

            for (Color color: userColors) {
                System.out.print(color);
                System.out.print(" ");
            }

            System.out.print("  =>   ");
            
            for (int i = 0; i < correct; i += 1) {
                System.out.print("O ");
            }
            for (int i = 0; i < misplaced; i += 1) {
                System.out.print("X ");
            }
            for (int i = 0; i < absent; i += 1) {
                System.out.print("- ");
            }

            System.out.println("");

        } else {
            System.out.println(ConsoleColor.YELLOW + "Ceci n'est pas une combinaison valide!" + ConsoleColor.RESET);
        }

        System.out.println("");
    }

    public void terminate()
    {
        // Nettoie l'objet permettant de traiter la saisie de l'utilisateur
        scanner.close();

        isRunning = false;
    }

    public boolean isRunning()
    {
        return isRunning;
    }

}
