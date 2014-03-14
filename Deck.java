import java.util.*;

/**
 * 
 */

/**
 * @author glavin && Faisal K Al Mazroa A.K.A ./~Saudi Coder
 * 
 */
public class Deck {

	private int maxCards; // keep track how many cards are being used.
	private Card[] cards; // array that holds the cards that represents the Deck.

    public Deck()
    {
        cards = new Card[52]; // create an unshuffled cards.
        int howManyCards = 0; // howMany cards have been created.
        for(int theSuit = 0; theSuit <=3; theSuit++)
        {
            for(int theValue = 0; theValue <= 13; theValue++)
            {
                cards[howManyCards] = new Card(theValue,theSuit);
                ++howManyCards;
            }
        }
       maxCards = 0;
    }

	/**
	 * 
	 */
	public void shuffle()
	{
        List<Card> list =  Arrays.asList(cards); //move it temporary to a list.
        Collections.shuffle(list); // shuffle the cards
        list.toArray(cards); // return it back to the orignal deck after shuffeling
        maxCards = 0; // after everyshuffle means that the game is done.
	}

	/**
	 * 
	 * @return
	 */
	public int cardsRemaining()
	{
		return 52 - maxCards;
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
	 * Deals on card and then it will return it.
	 */
	public Card dealCard()
	{
		if(maxCards == 52) // if we used all cards deck is over , shuffle !
        {
            shuffle();
        }
        else
        {
            maxCards++; // still card left , deal a card and update the deck with the remainning.
            return cards[maxCards - 1];
        }
	}

	/**
	 *  We dont need this function ! S.C
	 */
	public void addCard()
	{
		// TODO
	}

}
