import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;
import java.util.regex.*;

import Utils.ConsoleColor;

public class App
{
    private static final char[] COLORS = {'R', 'V', 'B', 'C', 'J', 'M'};

    // Cette méthode est appelée automatiquement au lancement de l'application
    public static void main(String[] args) throws Exception {
        // Efface la console
        System.out.print("\033[H\033[2J");   
        System.out.flush();

        // Initialise un objet permettant de traiter la saisie de l'utilisateur        
        Scanner scanner = new Scanner(System.in);

        // Définit la solution du jeu
        String solution = "MMCR";
        char[] solutionArray = solution.toCharArray();

        String userInput = null;
        // Tant que la saisie de l'utilisateur n'est pas vide
        while (!"".equals(userInput)) {
            // Récupère une saisie de l'utilisateur
            // en enlevant les espaces avant et après, et en convertissant en majuscules
            userInput = scanner.nextLine().trim().toUpperCase();

            // Vérifie si la saisie de l'utilisateur correspond au schéma attendu
            // (exactement 4 caractères parmi R, V, B, J, C et M, sans rien avant ni rien après)
            if (Pattern.matches("^[RVBJCM]{4}$", userInput)) {
                char[] userInputArray = userInput.toCharArray();
                
                ArrayList<Character> incorrectUserInputCharacters = new ArrayList<>();
                ArrayList<Character> incorrectSolutionCharacters = new ArrayList<>();
                // Compte le nombre de couleurs bien placées dans la saisie de l'utilisateur
                int correct = 0;
                for (int i = 0; i < 4; i += 1) {
                    if (userInputArray[i] == solutionArray[i]) {
                        correct += 1;
                    // Retient les couleurs qui ne sont pas bien placés pour l'étape suivante
                    } else {
                        incorrectUserInputCharacters.add(userInputArray[i]);
                        incorrectSolutionCharacters.add(solutionArray[i]);
                    }
                }
                
                // Compte le nombre de couleurs, parmi les couleurs qui ne sont pas bien placées,
                // qui sont en trop par rapport aux couleurs restantes dans la solution
                int absent = 0;
                for (char color: App.COLORS) {
                    int excess = Collections.frequency(incorrectUserInputCharacters, color) - Collections.frequency(incorrectSolutionCharacters, color);
                    if (excess > 0) {
                        absent += excess;
                    }
                }

                int misplaced = 4 - correct - absent;

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
        }

        // Nettoie l'objet permettant de traiter la saisie de l'utilisateur
        scanner.close();

        System.out.print("Goodbye!");
    }
}
