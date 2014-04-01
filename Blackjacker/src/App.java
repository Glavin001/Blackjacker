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
        // 
        Game game = new Game();
        game.addPlayer(new Player(game));
        game.addPlayer(new Player(game));
        game.addPlayer(new Player(game));

        GameView view = new GameView(game);
        game.notifyViews();
        game.resetGame();

    }

}
