	import java.util.ArrayList;

import javax.swing.Box;

/**
 * @author Glavin Wiechert
 *
 */
public class Dealer extends Player 
{

    /**
     * Minimum hand value.
     */
    private int minimumHandValue;

    /**
     * 
     */
    private Deck deck;

    /**
     * 
     */
    private ArrayList<Player> players;

    /**
     * 
     */
    private Player currentPlayer;


    public Dealer (Game game)
    {
        super(game);
    }

    public Deck getDeck()
    {
        return deck;
    }

    /**
     * 
     * @return	The next player.
     */
    public Player getNextPlayer()
    {
        // TODO
        return null;
    }

    /**
     * 
     * @param player
     */
    public void dealCardToPlayer(Player player, boolean faceUp)
    {
        Card card = deck.dealCard();
        if (faceUp)
        {
            card.unfold();
        }
        player.getHand().addCard(card);
        notifyViews();
    }

    /**
     * 
     * @param newDeck
     */
    public void setDeck(Deck newDeck)
    {
        deck = newDeck;
        notifyViews();

    }

    /**
     * Dealer makes it's moves.
     */
    public void makeMoves()
    {
        Hand hand = this.getHand();
        hand.showAllCards();

        int minVal = 17;
        while (hand.getValue() < 17)
        {
            // Hit
            dealCardToPlayer(this, true);
            // Check if (21)
            if (hand.getValue() == 21)
            {
                setLabels("Dealer has 21!");
                return;
            }
            // Check if Bust
            else if (hand.getValue() > 21)
            {
                setLabels("Dealer Busted!");
                return;
            }
        }
        boolean hitAgain = true;
        for (Player p : game.getPlayers())
        {
        	if (p != this)
        		if (p.getHand().getValue()
        				<= hand.getValue() )
                    hitAgain = false;
        }
        if (hitAgain) dealCardToPlayer(this, true);
    }
    
 

    public void requestMove()
    {
        makeMoves();
        game.resolveGame();
    }
    
	public void setLabels(String msg) 
	{
		for (View view: getViews())
		{
			if (view.getClass() == DealerView.class) 
				((DealerView) view).getLabel().setText(msg);
		}	
	}

}
