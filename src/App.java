import Game.Game;

public class App
{
    // Cette méthode est appelée automatiquement au lancement de l'application
    public static void main(String[] args) throws Exception {
        // Efface la console
        System.out.print("\033[H\033[2J");   
        System.out.flush();

        Game game = new Game();
        // Tant que la saisie de l'utilisateur n'est pas vide
        while (game.isRunning()) {
            game.update();
        }

        System.out.print("Goodbye!");
    }
}
