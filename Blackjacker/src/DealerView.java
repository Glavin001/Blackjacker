import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
public class DealerView extends JPanel implements ActionListener, View{
	private Dealer dealer;
	

	/**
	 * Displays player hand value
	 */
	private JLabel dealerLabel;
	
    /**
     * 
     */
    private JButton restartButton;

    /**
     * 
     */	
    private JButton reshuffleButton;
    
    /**
     * 
     */	
    private JButton countButton;

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
			
	    reshuffleButton = new JButton("Reshuffle");
	    reshuffleButton.setActionCommand("reshuffle");
	    reshuffleButton.addActionListener(this);
	    c.fill = GridBagConstraints.HORIZONTAL;
	    c.gridwidth = 1;
	    c.weightx = 0.5;
	    c.gridx = 0;
	    c.gridy = row;
	    this.add(reshuffleButton, c);
	    
	    restartButton = new JButton("Deal Again");
	    restartButton.setActionCommand("restart");
	    restartButton.addActionListener(this);
	    c.fill = GridBagConstraints.HORIZONTAL;
	    c.gridwidth = 1;
	    c.weightx = 0.5;
	    c.gridx = 1;
	    c.gridy = row;
	    this.add(restartButton, c);
	    

	    countButton = new JButton("Count");
	    countButton.setActionCommand("count");
	    countButton.addActionListener(this);
	    c.fill = GridBagConstraints.HORIZONTAL;
	    c.gridwidth = 1;
	    c.weightx = 0.5;
	    c.gridx = 2;
	    c.gridy = row;
	    this.add(countButton, c);	
		
		
		row ++;
		
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
		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipady = 60;   
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

	@Override
	public void actionPerformed(ActionEvent e) 	
	{
		if ("restart".equals(e.getActionCommand()))
			dealer.playAgain();
		else if ("reshuffle".equals(e.getActionCommand()))
			dealer.resetGame();
		else if ("count".equals(e.getActionCommand()))
			dealer.getCounter().display();
		else
			System.out.println("Unknown action: "+ e.getActionCommand());	
	}
	
	public JLabel getLabel() 
	{
		return dealerLabel;
	}
	
	

}
