import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JPanel;

/**
 * @author Glavin Wiechert and Faisal K Al Mazroa A.K.A ./~Saudi Coder 
 *
 */
public class Hand extends JPanel
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
     * 
     */
    public Hand()
    {
        cards = new ArrayList<Card>();
		this.setLayout(new FlowLayout(FlowLayout.LEFT));
    }
    
    /**
     *
     */
    public void clearCards()
    {
        cards.clear();
        this.removeAll();
        repaint();
    }

    /**
     *
     * @param theCard	The card to add to this hand.
     */
    public void removeCard(Card theCard)
    {
        cards.remove(theCard);
        this.remove(theCard);
        this.revalidate();
        repaint();
    }

    /**
     *
     */
    public void addCard(Card theCard)
    {
        if (theCard != null)
        {
            cards.add(theCard);
            this.add(theCard);
            this.revalidate();
            repaint();
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
        forceRefresh();
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
        forceRefresh();
    }

    /**
     * 
     */
    public void forceRefresh()
    {
    	// Remove cards
    	this.removeAll();
    	// Re-add all cards
    	for (Card card : cards)
    	{
    		this.add(card);
    	}
        this.revalidate();
        repaint();
    }
    
    /**
     * we do not need this because sorting both the values and suit will sort all the cards !!=
    public void sortCards()
    {

    }
    */

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
    			if (c.getValue() == -1)
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
    
    private int max(int n, int m)
    {
    	if ( n > m)
    		return n;
    	else
    		return m;
    }
    
    private int min(int n, int m)
    {
    	if ( n < m)
    		return n;
    	else
    		return m;
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
		repaint();
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
    
    /**
     * 
     */
    public void paint(Graphics g)
    {
    	System.out.println("Paint Hand");
    	/*
    	int maxWidth = this.getWidth() - 20;
    	int cardWidth = (cards.size() == 0)? 0 : (maxWidth / cards.size());
    	int cardHeight = this.getHeight();
    	for (Card card : cards)
    	{
    		Dimension dMax = new Dimension(cardWidth, cardHeight);
    		Dimension dMin = new Dimension(6, 8);
    		Dimension dPref = new Dimension(cardWidth, cardHeight);
    		card.setMaximumSize(dMax);
    		card.setMinimumSize(dMin);
    		card.setPreferredSize(dPref);
    	}
    	*/
    	super.paint(g);
    	super.paintComponents(g);
    	//this.revalidate();
    }
    
}