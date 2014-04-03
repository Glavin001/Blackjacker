import java.awt.Container;
import java.awt.GridBagLayout;

import javax.swing.*;


/**
 * @author Glavin Wiechert
 *
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
     * 
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
     * 
     * 
     */
	public Counter getCounter() 
	{
		return count;
	}
    
    /**
     * 
     * @return	This player's hand.
     */
    public void registerCounter(Counter c)
    {
    	count = c;
    }

    /**
     * 
     */
    public void hit()
    {
        game.hitPlayer(this);
    }
    
    /**
     * 
     */
    public void stand()
    {
        game.standPlayer(this);
    }
    
    /**
     * 
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
     * 
     * @return
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
