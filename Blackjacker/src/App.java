/**
 * @author Glavin Wiechert
 *
 */
public class App 
{

    /**
     * @param args
     */
    public static void main(String[] args) 
    {
        // Init Game
        Game game = new Game(4);
        // Add Players
        game.addPlayer(new Player(game));
        game.addPlayer(new Player(game));
        game.addPlayer(new Player(game));
        // Setup the GUI (View)
        GameView view = new GameView(game);
        // Card Counting

        CounterView cView = new CounterView(new Counter(game));
        
        game.resetGame();
        //game.notifyViews();
        

    }

}
