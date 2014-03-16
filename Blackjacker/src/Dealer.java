import java.util.ArrayList;

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
	
	
	public Dealer ()
	{
		
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
	}
	
	/**
	 * 
	 * @param newDeck
	 */
	public void setDeck(Deck newDeck)
	{
		deck = newDeck;
	}
	
}
