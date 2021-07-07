package Game;

import java.util.Scanner;
import java.util.regex.*;

import Utils.ConsoleColor;

public class Game {

    private Scanner scanner;
    private ColorCombination solutionCombination;
    private boolean isRunning;
    private int combinationLength = 4;

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
        solutionCombination = ColorCombination.createRandom(combinationLength);

        System.out.println(ConsoleColor.CYAN + "L'ordinateur a choisi une combinaison et attend vos propositions...\n" + ConsoleColor.RESET);
    }

    public void update()
    {
        // Récupère une saisie de l'utilisateur
        // en enlevant les espaces avant et après, et en convertissant en majuscules
        String userInput = scanner.nextLine().trim().toUpperCase();

        // Vérifie si la saisie de l'utilisateur correspond au schéma attendu
        // (exactement 4 caractères parmi R, V, B, J, C et M, sans rien avant ni rien après)
        if (Pattern.matches("^[" + Color.getColorsAsString() + "]{" + combinationLength + "}$", userInput)) {
            // Crée une combinaison de couleurs à partir de la saisie de l'utilisateur
            // et lui demande de se comparer avec la solution
            ColorCombination userCombination = new ColorCombination(userInput);
            ComparisonResult result = userCombination.compareTo(solutionCombination);

            // Demande à la combinaison entrée par l'utilisateur de s'afficher
            System.out.print( userCombination.display() );
            System.out.print("  =>   ");
            // Demande au résultat de la comparaison de s'afficher
            System.out.print( result.display() );

            // Si l'utilisateur a rentré la combinaison exacte
            if (result.isCorrect()) {
                System.out.println(ConsoleColor.GREEN + "\nBravo! Vous avez trouvé la bonne combinaison!" + ConsoleColor.RESET);

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
