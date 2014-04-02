import java.util.*;


/**
 * @author Glavin Wiechert && Faisal K Al Mazroa A.K.A ./~Saudi Coder
 * 
 */
public class Deck extends Model
{

	/**
	 * Keep track how many cards are being used.
	 */
	private int maxCards;
	private int numDecks;
	
	/**
	 * Array that holds the cards that represents the Deck.
	 */
	private Card[] cards;

	/**
	 * 
	 */
    public Deck()
    {
    	reset(1);
    }
    
    public Deck(int n)
    {
    	reset(n);
    	numDecks = n;
    }
    

    /**
     * 
     */
    public void reset(int n)
    {
        cards = new Card[n*52]; // create an unshuffled cards.
        int howManyCards = 0; // howMany cards have been created.
        for(int theSuit = 0; theSuit <= 3; theSuit++)
        {
        	// Ace = 1, 13 = King
            for(int theValue = 1; theValue <= 13; theValue++)
            {
            	////System.out.println(howManyCards);
            	////System.out.println(theSuit);
            	////System.out.println(theValue);
            	for (int i = 0; i < n; i++)
            	{
            		cards[howManyCards] = new Card(theValue,theSuit);
            		++howManyCards;
            	}
                
            }
        }
       maxCards = 0;
    }
    

	/**
	 * 
	 */
	public void shuffle()
	{
		// Flip all cards to face down
		for (Card c : cards)
		{
			c.fold();
		}
        List<Card> list =  Arrays.asList(cards); //move it temporary to a list.
        Collections.shuffle(list); // shuffle the cards
        list.toArray(cards); // return it back to the original deck after shuffling
        maxCards = 0; // after every shuffle means that the game is done.
	}

	/**
	 * 
	 * @return	The number of cards remaining.
	 */
	public int cardsRemaining()
	{
		return 52*numDecks - maxCards;
	}

	/**
	 * 
	 * @return	The number of cards played.
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
		if(maxCards == numDecks*52) // if we used all cards deck is over , shuffle !
        {
            shuffle();
        }
        maxCards++; // still card left , deal a card and update the deck with the remaining.
        notifyViews(); 
        return cards[maxCards - 1];
	}


	



}