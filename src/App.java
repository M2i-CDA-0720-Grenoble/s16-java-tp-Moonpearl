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
                
                // Compte le nombre de couleurs bien placées dans la saisie de l'utilisateur
                int correct = 0;
                for (int i = 0; i < 4; i += 1) {
                    if (userInputArray[i] == solutionArray[i]) {
                        correct += 1;
                    }
                }
                System.out.println("Vous avez " + correct + " couleurs bien placées.");

                // Compte le nombre de couleurs dans la solution
                HashMap<Character, Integer> solutionColorCount = new HashMap<>() {
                    {
                        put('R', 0);
                        put('V', 0);
                        put('B', 0);
                        put('C', 0);
                        put('J', 0);
                        put('M', 0);
                    }
                };
                for (char color: solutionArray) {
                    // Récupère quantité actuelle de cette couleur
                    int currentColorCount = solutionColorCount.get(color);
                    // Remplace la quantité actuelle de cette couleur en lui ajoutant 1
                    solutionColorCount.put(color, currentColorCount + 1);
                }

                // Compte le nombre de couleurs dans la solution
                HashMap<Character, Integer> userInputColorCount = new HashMap<>() {
                    {
                        put('R', 0);
                        put('V', 0);
                        put('B', 0);
                        put('C', 0);
                        put('J', 0);
                        put('M', 0);
                    }
                };
                for (char color: userInputArray) {
                    // Récupère quantité actuelle de cette couleur
                    int currentColorCount = userInputColorCount.get(color);
                    // Remplace la quantité actuelle de cette couleur en lui ajoutant 1
                    userInputColorCount.put(color, currentColorCount + 1);
                }
                
                // Compte le nombre de chaque couleur dans la saisie de l'utilisateur absentes de la solution
                int absent = 0;
                for (char color: App.COLORS) {
                    int excess = userInputColorCount.get(color) - solutionColorCount.get(color);
                    if (excess > 0) {
                        absent += excess;
                    }
                }
                System.out.println("Votre proposition contient " + absent + " couleurs absentes de la solution.");

            } else {
                System.out.println(ConsoleColor.YELLOW + "Ceci n'est pas une combinaison valide!" + ConsoleColor.RESET);
            }
        }

        // Nettoie l'objet permettant de traiter la saisie de l'utilisateur
        scanner.close();

        System.out.print("Goodbye!");
    }
}
