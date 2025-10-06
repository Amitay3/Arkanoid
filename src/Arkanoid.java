import game.Menu;

/**
 * The Ass5Game class serves as the entry point for the Arkanoid game application.
 * It initializes and starts the game.
 */
public class Arkanoid {

    /**
     * The main method is the entry point of the application.
     * It creates an instance of the Game class, initializes it, and starts the game loop.
     *
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        Menu menu = new Menu();
        menu.showMenu();
    }
}
