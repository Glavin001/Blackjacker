import java.awt.Container;
import java.awt.GridBagLayout;

import javax.swing.*;


/**
 * @author Glavin Wiechert
 *
 */
public class Player 
{

	/**
	 * 
	 */
	private Hand hand;

	/**
	 * 
	 */
	public Player()
	{
		hand = new Hand();
	}
	
	/**
	 * 
	 * @return	This player's hand.
	 */
	public Hand getHand()
	{
		return hand;
	}

}
