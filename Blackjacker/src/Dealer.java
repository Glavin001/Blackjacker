import java.util.ArrayList;

import javax.swing.Box;

/**
 * The Dealer model and logic.
 * @author Glavin Wiechert & Dylan Meijer & Faisal K. AlMazroa
 *
 */
public class Dealer extends Player 
{

    /**
     * Minimum hand value.
     */
    private int minimumHandValue;

    /**
     * The deck.
     */
    private Deck deck;

    /**
     * All players.
     */
    private ArrayList<Player> players;

    /**
     * The current player.
     */
    private Player currentPlayer;

    /**
     * Constructor
     * @param game
     */
    public Dealer (Game game)
    {
        super(game);
    }

    /**
     * Get the deck.
     * @return The deck.
     */
    public Deck getDeck()
    {
        return deck;
    }

    /**
     * Get the next player.
     * @return	The next player.
     */
    public Player getNextPlayer()
    {
        // TODO
        return null;
    }

    /**
     * Deal a card to the player.
     * @param player    The player to deal to.
     * @param faceUp    If card is faceUp
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
     * Set the deck for this dealer to use.
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



    /**
     * Request the moves of the players.
     */
    public void requestMove()
    {
        makeMoves();
        game.resolveGame();
    }

    /**
     * Set the text of the label to the message.
     * @param msg   The message.
     */
    public void setLabels(String msg) 
    {
        for (View view: getViews())
        {
            if (view.getClass() == DealerView.class) 
                ((DealerView) view).getLabel().setText(msg);
        }	
    }

}
