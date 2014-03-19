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
	
	
	public Dealer ()
	{
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
			// Check if Blackjack (21)
			if (hand.getValue() == 21)
			{
				System.out.println("BLACKJACK! Dealer wins");
				return;
			}
			// Check if Bust
			else if (hand.getValue() > 21)
			{
				System.out.println("Busted. Dealer loses.");
				return;
			}
		}
		// Stand
		System.out.println("Dealer stands.");
	}
	public void makeMoves(int value)
	{
		Hand hand = this.getHand();
		hand.showAllCards();
		
		int minVal = 17;
		while (hand.getValue() < 17 || (hand.getValue() < value) )
		{
			// Hit
			dealCardToPlayer(this, true);
			// Check if Blackjack (21)
			if (hand.getValue() == 21)
			{
				System.out.println("Dealer got 21!");
				return;
			}
			// Check if Bust
			else if (hand.getValue() > 21)
			{
				System.out.println("Busted.");
				return;
			}
		}
		// Stand
		System.out.println("Dealer stands.");
	}
	
	
}
