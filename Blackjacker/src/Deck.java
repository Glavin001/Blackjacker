import java.awt.Color;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.*;

import javax.swing.JComponent;

/**
 * @author Glavin Wiechert && Faisal K Al Mazroa A.K.A ./~Saudi Coder
 * 
 */
public class Deck extends JComponent
{

	/**
	 * Keep track how many cards are being used.
	 */
	private int maxCards;
	
	/**
	 * Array that holds the cards that represents the Deck.
	 */
	private Card[] cards;

    /**
     * 
     */
    private Dimension preferredSize;

	/**
	 * 
	 */
    public Deck()
    {
    	reset();
    
    	// UI
        // Size of playing cards: http://www.ask.com/question/what-are-the-dimensions-of-a-standard-playing-card 
        float sizeRatio = 2;
        preferredSize = new Dimension( (int) (63*sizeRatio), (int) (89*sizeRatio) ); // new Dimension(63, 89);
    }
    
	public Dimension preferredSize()
	{
		
		return preferredSize;
	}
	
	public Dimension minimumSize()
	{
		
		return preferredSize;
	}
    /**
     * 
     */
    public void reset()
    {
        cards = new Card[52]; // create an unshuffled cards.
        int howManyCards = 0; // howMany cards have been created.
        for(int theSuit = 0; theSuit <= 3; theSuit++)
        {
        	// Ace = 1, 13 = King
            for(int theValue = 1; theValue <= 13; theValue++)
            {
            	//System.out.println(howManyCards);
            	//System.out.println(theSuit);
            	//System.out.println(theValue);
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
		return 52 - maxCards;
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
		if(maxCards == 52) // if we used all cards deck is over , shuffle !
        {
            shuffle();
        }
        maxCards++; // still card left , deal a card and update the deck with the remaining.
        repaint();
        return cards[maxCards - 1];
	}

	/**
	 *  We don't need this function ! S.C
	 */
	public void addCard()
	{
		// TODO
	}
	

    /**
     * 
     */
    public void paintComponent(Graphics g) 
    {
        Graphics2D g2 = (Graphics2D) g;
        super.paintComponent(g);  
        
        // Draw background
        g2.setColor(Color.WHITE);
        Rectangle r = new Rectangle(0, 0, this.getSize().width, this.getSize().height);
        //System.out.println(r);
        g2.fill(r);
        
    	// Face up to players
        g2.setColor(Color.BLACK);
        FontMetrics fm = g.getFontMetrics();
        String str = "Cards: " + new Integer(this.cardsRemaining()).toString();
        // Draw Suit
        g2.drawString(str, this.getWidth()/2 - fm.stringWidth(str)/2, this.getHeight()/2);

    }

}