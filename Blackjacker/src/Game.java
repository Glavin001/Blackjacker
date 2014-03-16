import java.awt.Color;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 * @author Glavin Wiechert
 *
 */
public class Game implements ActionListener 
{

	/**
	 * 
	 */
	JFrame frame;
	
	/**
	 * 
	 */
	private Deck deck;
	/**
	 * 
	 */
	private Dealer dealer;
	/**
	 * 
	 */
	private Player player;
	/**
	 * 
	 */
	private JButton hitButton;
	/**
	 * 
	 */
	private JButton standButton;
	/**
	 * 
	 */
	private JButton restartButton;
	
	/**
	 * 
	 */
	public Game () 
	{
		// Initialize
		deck = new Deck();
		dealer = new Dealer();
		player = new Player();
		
		// Setup
		frame = new JFrame("Blackjacker");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container contentPane = frame.getContentPane();
		contentPane.setLayout(new GridBagLayout());
		
		// 
		GridBagConstraints c = new GridBagConstraints();
		
		contentPane.setBackground( new Color(10,60,70) );

		//
		int row = 0;
		
		// 
		Hand dealerHand = dealer.getHand();
		dealerHand.setBackground( new Color(10,60,70) );
		GridBagConstraints cDealerHand = new GridBagConstraints();
		cDealerHand.fill = GridBagConstraints.HORIZONTAL;
		cDealerHand.ipady = 40;      //make this component tall
		cDealerHand.weightx = 0.0;
		cDealerHand.gridwidth = 5;
		cDealerHand.gridx = 0;
		cDealerHand.gridy = row;
		contentPane.add(dealerHand, cDealerHand);

		// Next line
		row++;
		
		//
		Hand hand = player.getHand();
		hand.setBackground( new Color(10,60,70) );
		GridBagConstraints cHand = new GridBagConstraints();
		cHand.fill = GridBagConstraints.HORIZONTAL;
		cHand.ipady = 60;      //make this component tall
		cHand.weightx = 0.0;
		cHand.gridwidth = 5;
		cHand.gridx = 0;
		cHand.gridy = row;
		contentPane.add(hand, cHand);
				
		// Next line
		row++;
		
		// 
		hitButton = new JButton("Hit!");
		hitButton.setActionCommand("hit");
		hitButton.addActionListener(this);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.5;
		c.gridx = 0;
		c.gridy = row;
		contentPane.add(hitButton, c);
		
		// 
		standButton = new JButton("Stand!");
		standButton.setActionCommand("stand");
		standButton.addActionListener(this);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.5;
		c.gridx = 1;
		c.gridy = row;
		contentPane.add(standButton, c);

		
		// 
		restartButton = new JButton("Restart");
		restartButton.setActionCommand("restart");
		restartButton.addActionListener(this);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.5;
		c.gridx = 2;
		c.gridy = row;
		contentPane.add(restartButton, c);

		//
		JButton sortBySuit = new JButton("Sort by Suit");
		sortBySuit.setActionCommand("sortBySuit");
		sortBySuit.addActionListener(this);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.5;
		c.gridx = 3;
		c.gridy = row;
		contentPane.add(sortBySuit, c);

		JButton sortByValue = new JButton("Sort by Value");
		sortByValue.setActionCommand("sortByValue");
		sortByValue.addActionListener(this);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.5;
		c.gridx = 4;
		c.gridy = row;
		contentPane.add(sortByValue, c);

		
		// Display the window.
		frame.setSize(600, 600);
		frame.setResizable(true);
		//frame.pack();
		frame.setVisible(true);

		restartGame(); // Starts the game for the first time.
		
	}

	/**
	 * 
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		// 
		System.out.println(e.getActionCommand());
		// 
		if ("hit".equals(e.getActionCommand()))
		{
			// Deal new card to player
			Card newCard = deck.dealCard();
			newCard.unfold();
			Hand hand = player.getHand();
			hand.addCard(newCard);
			// Check if Player:
			int handValue = hand.getValue();
			System.out.println("Hand: "+handValue);
			// == 21
			if (handValue == 21)
			{
				System.out.println("Blackjack! You win!");
			}
			// == Bust
			else if (handValue > 21)
			{
				System.out.println("You busted!");
			}
			// == Else: good for another hit/stand
			else
			{
				System.out.println("Another go?");
			}
			
		}
		else if ("stand".equals(e.getActionCommand()))
		{
			// Next player's turn
			// The Dealer's turn
			dealer.makeMoves();
		}
		else if ("restart".equals(e.getActionCommand()))
		{
			// 
			restartGame();
			
		}
		else if ("sortByValue".equals(e.getActionCommand()))
		{
			// 
			player.getHand().sortByValue();
			
		}
		else if ("sortBySuit".equals(e.getActionCommand()))
		{
			// 
			player.getHand().sortBySuit();
		}
		else
		{
			System.out.println("Unknown action: "+e.getActionCommand());
		}
	}
		
	/**
	 * 
	 */
	public void restartGame()
	{
		// Clear Hands
		dealer.getHand().clearCards();
		player.getHand().clearCards();
		
		// Shuffle Deck 
		dealer.setDeck(deck);
		deck.shuffle();
		
		// Deal to players
		dealer.dealCardToPlayer(dealer,	false);
		dealer.dealCardToPlayer(dealer,	true);
		
		dealer.dealCardToPlayer(player,	true);
		dealer.dealCardToPlayer(player,	true);

		//
		
	}
	
	
	
}
