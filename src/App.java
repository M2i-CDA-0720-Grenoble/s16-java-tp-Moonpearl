import java.util.Scanner;
import java.util.regex.*;

public class App
{
    // Cette méthode est appelée automatiquement au lancement de l'application
    public static void main(String[] args) throws Exception {
        // Efface la console
        System.out.print("\033[H\033[2J");   
        System.out.flush();

        // Initialise un objet permettant de traiter la saisie de l'utilisateur        
        Scanner scanner = new Scanner(System.in);

        String userInput = null;
        // Tant que la saisie de l'utilisateur n'est pas vide
        while (!"".equals(userInput)) {
            // Récupère une saisie de l'utilisateur
            // en enlevant les espaces avant et après, et en convertissant en majuscules
            userInput = scanner.nextLine().trim().toUpperCase();

            // Vérifie si la saisie de l'utilisateur correspond au schéma attendu
            // (exactement 4 caractères parmi R, V, B, J, C et M, sans rien avant ni rien après)
            System.out.println(
                Pattern.matches("^[RVBJCM]{4}$", userInput)
            );
        }

        // Nettoie l'objet permettant de traiter la saisie de l'utilisateur
        scanner.close();

        System.out.print("Goodbye Marylou!");
    }
}
