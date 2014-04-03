import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JPanel;

/**
 * The Hand model.
 * @author Glavin Wiechert & Dylan Meijer & Faisal K. AlMazroa
 *
 */
public class Hand extends Model
{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 
	 */
    private ArrayList<Card> cards;

    /**
     * Constructor
     */
    public Hand()
    {
        cards = new ArrayList<Card>();

    }
    
    /**
     *
     */
    public void clearCards()
    {
        cards.clear();
        notifyViews();
    }
    
    public ArrayList<Card> getCards()
    {
    	return cards;
    }

    /**
     *
     * @param theCard	The card to add to this hand.
     */
    public void removeCard(Card theCard)
    {
        cards.remove(theCard);
        notifyViews(); 
    }

    /**
     *
     */
    public void addCard(Card theCard)
    {
        if (theCard != null)
        {
            cards.add(theCard);
            ////System.out.println("Add Card");
            notifyViews(); 
        }
    }

    /**
     *
     * @return	The number of cards in this hand.
     */
    public int cardsInHand()
    {
        return cards.size();
    }

    /**
     *
     */
    public void sortBySuit()
    {

        ArrayList<Card> temp = new ArrayList<Card>();
        while (cards.size() > 0)
        {
            // we target the minimum card.
            int position = 0;
            Card tempCard = (Card) cards.get(0); // to have the smallest card.
            for (int i = 1; i < cards.size(); i++)
            {
                Card card1 = (Card) cards.get(i);
                if (card1.getSuit() < tempCard.getSuit() ||
                        card1.getSuit() == tempCard.getSuit() &&
                        card1.getValue() < tempCard.getValue())
                {
                    position = i;
                    tempCard = card1;
                }
            }
            cards.remove(position);
            temp.add(tempCard);
        }
        cards = temp;
        
        // Display cards in new order
        notifyViews();
    }

    /**
     *
     */
    public void sortByValue()
    {
        ArrayList<Card> temp = new ArrayList<Card>();
        while (cards.size() > 0)
        {
            // we target the minimum card.
            int position = 0;
            Card tempCard = (Card) cards.get(0); // to have the smallest card.
            for (int i = 1; i < cards.size(); i++)
            {
                Card card1 = (Card) cards.get(i);
                if (card1.getValue() < tempCard.getValue() ||
                        card1.getValue() == tempCard.getValue() &&
                        card1.getSuit() < tempCard.getSuit())
                {
                    position = i;
                    tempCard = card1;
                }
            }
            cards.remove(position);
            temp.add(tempCard);
        }
        cards = temp;
        
        // Display cards in new order
        notifyViews();
    }


    /**
     * 
     */
    public int getValue()
    {
    	int total = 0;
    	int aceCount = 0;
    	for (Card c : cards)
    	{
    		if (! c.isFolded() )
    			if (c.getValue() == 1)
    			{
    				total += 1;
    				aceCount ++;
    			}
    			else
    				total += c.getValue();
    	}
    	for (int i = 0; i < aceCount; i++)
    		if (total + 10 <= 21)
    			total = total + 10;
    	return total;
    }
    
    
    /**
     * 
     */
    public void showAllCards()
    {
    	// Flip all cards to face up
		for (Card c : cards)
		{
			c.unfold();
		}
		notifyViews();
    }
    
    public boolean isBust()
    {
    	if (this.getValue() > 21)
    		return true;
    	else
    		return false;
    }
    
    public boolean isBlackJack()
    {
    	if(getValue() == 21 && cardsInHand() == 2)
    		return true;
    	else
    		return false;
    }
    
}