import java.util.ArrayList;

/**
 * 
 */

/**
 * @author glavin
 *
 */
public class Deck {

	private int maxCards;
	private ArrayList<Card> cards;
	
	/**
	 * 
	 */
	public void shuffle()
	{
		
	}
	
	/**
	 * 
	 * @return
	 */
	public int cardsRemaining()
	{
		return cards.size();
	}
	
	/**
	 * 
	 * @return
	 */
	public int cardsPlayed()
	{
		return maxCards - cardsRemaining();
	}
	
	/**
	 * 
	 * @return
	 */
	public Card dealCard()
	{
		// TODO
		return null;
	}
	
	/**
	 * 
	 */
	public void addCard()
	{
		// TODO
	}
	
}
