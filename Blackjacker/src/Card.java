import java.awt.Color;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.JComponent;

/**
 * @author Glavin Wiechert & Faisal K Al Mazroa A.K.A ./~Saudi Coder
 *
 */
public class Card extends Model
{
	/**
	 * 
	 */
    private int value;
    /**
     * 
     */
    private int suit;
    /**
     *  The code value for the suits
     */
    public final static int SPADES = 0,
            HEARTS = 1,
            DIAMONDS = 2,
            CLUBS = 3;
    /**
     *  The code for the cards that have no number values.
     */
    public final static int ACE = 1,
            JACK = 11,
            QUEEN = 12,
            KING = 13;
    
    /**
     * 
     */
    private boolean folded;
    
    /**
     * 
     */
    private Dimension preferredSize;
    
    /**
     *
     * A constructor to create a Card object that will be either a "suit" or "value" card.
     */
    public Card(int initialValue, int initialSuit)
    {
        // 
    	value = initialValue;
        suit = initialSuit;
        
        //
        folded = true;
        
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
     * @return Suit of this card.
     */
    public int getSuit()
    {
        return suit;
    }

    /**
     *
     * @return Value of this card.
     */
    public int getValue()
    {
    	// See issue #8
    	if (value == JACK 
    			|| value == QUEEN 
    			|| value == KING)
    		return 10;
    	if (value == ACE)
    		return -1;
    	

    	return value;
    }

    /**
     * 
     * @return	If this card is folded (face down) or not.
     */
    public boolean isFolded()
    {
    	return folded;
    }
    
    /**
     * 
     */
    public void fold()
    {
    	folded = true;
    }

    /**
     * 
     */
    public void unfold()
    {
    	folded = false;
    }
    
    /**
     *
     * @return the String value of each card depending on the code(number-value).
     */
    public String suitToString()
    {
        if (suit == SPADES)
        {
            return "Spades";
        }
        if (suit == HEARTS)
        {
            return "Hearts";
        }
        if (suit == DIAMONDS)
        {
            return "Diamonds";
        }
        if (suit == CLUBS)
        {
            return "Clubs";
        }
        else
        {
            return "Unknown Suit";
        } // for the else statement we have to wait until we test the final GUI to see what error checking we should do here ! S.C
     }

            /**
             * 
             * @return the String value of each card depending of the code (number-value).
             */
    public String valueToString()
    {

        if (value == 1)
        {
            return "Ace";
        }
        if (value == 2)
        {
            return "2";
        }
        if (value == 3)
        {
            return "3";
        }
        if (value == 4)
        {
            return "4";
        }
        if (value == 5)
        {
            return "5";
        }
        if (value == 6)
        {
            return "6";
        }
        if (value == 7)
        {
            return "7";
        }
        if (value == 8)
        {
            return "8";
        }
        if (value == 9)
        {
            return "9";
        }
        if (value == 10)
        {
            return "10";
        }
        if (value == 11)
        {
            return "Jack";
        }
        if (value == 12)
        {
            return "Queen";
        }
        if (value == 13)
        {
            return "King";
        }
        else
        {
            return "Unknown Value";
        } //same case as the suit "else" statement wait until final version for testing.
    }

    /**
     * This function will print out the value of the cards each player will get.
     * (i,e 9 of Kings).
     */
    public String toString()
    {
        return valueToString() + " of " + suitToString();
    }
    
    /**
     * 
     */
    private BufferedImage getImage()
    {
    	String path = "rsrc/Images/";
    	switch (value)
    	{
    	case 2:
    	case 3:
    	case 4:
    	case 5:
    	case 6:
    	case 7:
    	case 8:
    	case 9:
    	case 10:
    		path += value;
    		break;
    	case 11: 
    		path += "J";
    		break;
    	case 12:
    		path += "Q";
    		break;
    	case 13:
    		path += "K";
    		break;
    	case 1:
    		path += "A";
    	}
    	switch (suit)
    	{
    	case SPADES:
    		path += "S";
    		break;
    	case HEARTS:
    		path += "H";
    		break;
    	case DIAMONDS:
    		path += "D";
    		break;
    	case CLUBS:
    		path += "C";
    		break;
    	}
    	path += ".png";
    	
    	URL resource = getClass().getResource(path);
    	System.out.println(path);
    	
    	try
    	{
    		return ImageIO.read(resource);
    	}
    	catch (Exception e)
    	{
    		return null; 
    	}
    }
    
}