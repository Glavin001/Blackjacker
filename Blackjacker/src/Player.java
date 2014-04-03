import java.awt.Container;
import java.awt.GridBagLayout;

import javax.swing.*;


/**
 * The Player class.
 * @author Glavin Wiechert & Dylan Meijer & Faisal K. AlMazroa
 */
public class Player extends Model
{
    /**
     * 
     */
    protected Game game;

    /**
     * 
     */
    private Hand hand;
    /**
     * 
     */
    private boolean waitingForMove = false;

    /**
     * 
     */
    private Counter count = null;

    /**
     * Constructor
     */
    public Player(Game game)
    {
        this.game = game;
        hand = new Hand();
    }

    /**
     * 
     * @return	This player's hand.
     */
    public Hand getHand()
    {
        return hand;
    }

    /**
     * Get the counter
     * @return The counter.
     */
    public Counter getCounter() 
    {
        return count;
    }

    /**
     * Register's counter
     */
    public void registerCounter(Counter c)
    {
        count = c;
    }

    /**
     * Player hits.
     */
    public void hit()
    {
        game.hitPlayer(this);
    }

    /**
     * Player stands.
     */
    public void stand()
    {
        game.standPlayer(this);
    }

    /**
     * Request to player again.
     */
    public void playAgain()
    {
        game.playAgain();
    }

    /**
     * 
     */
    public void resetGame()
    {
        game.resetGame();
    }

    /**
     * Check if it's the player's turn.
     * @return  If it is the player's turn.
     */
    public boolean isTurn()
    {
        return waitingForMove;
    }

    /**
     * 
     */
    public void requestMove()
    {
        waitingForMove = true;
        notifyViews();
    }

    public void waitForTurn()
    {
        waitingForMove = false;
        notifyViews();
    }

    public void setLabels(String msg) 
    {
        for (View view: getViews())
        {
            if (view.getClass() == PlayerView.class) 
                ((PlayerView) view).getLabel().setText(msg);
        }	
    }

}
