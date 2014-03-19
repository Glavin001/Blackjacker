import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 * 
 */

/**
 * @author Dylan
 *
 */
public class DealerView extends JPanel implements View{
	private Dealer dealer;
	

	/**
	 * Displays player hand value
	 */
	private JLabel dealerLabel;

	/**
	 * 
	 */
	public DealerView(Dealer d)
	{
		dealer = d;
		d.registerView(this);
		
		this.setLayout(new GridBagLayout());
		
		// 
		GridBagConstraints c = new GridBagConstraints();
		
		this.setBackground( new Color(10,60,70) );

		//
		int row = 0;
		
		
		//
		dealerLabel = new JLabel("");
		dealerLabel.setHorizontalAlignment(SwingConstants.CENTER);
		dealerLabel.setForeground(Color.WHITE);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.5;
		c.gridwidth = 5;
		c.gridx = 0;
		c.gridy = row;
		this.add(dealerLabel, c);

		
		row++;
		
		DeckView deck = new DeckView(dealer.getDeck());
		deck.setBackground( new Color(10,60,70) );
		GridBagConstraints cDeck = new GridBagConstraints();
		cDeck.fill = GridBagConstraints.HORIZONTAL;
		cDeck.weightx = 0.0;
		cDeck.gridx = 0;
		cDeck.gridy = row;
		this.add(deck, cDeck);
		
		//
		HandView hand = new HandView(dealer.getHand());
		hand.setBackground( new Color(10,60,70) );
		GridBagConstraints cHand = new GridBagConstraints();
		cHand.fill = GridBagConstraints.HORIZONTAL;
		cHand.ipady = 60;      //make this component tall
		cHand.weightx = 0.0;
		cHand.gridwidth = 5;
		cHand.gridx = 1;
		cHand.gridy = row;
		this.add(hand, cHand);

		
	}

	public void drawItUp() 
	{
		repaint();
	}
	

}
