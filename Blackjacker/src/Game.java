import java.awt.Color;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

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
	
	private JButton reshuffleButton;
	
	/**
	 * Displays dealer hand value
	 */
	private JLabel dealerLabel;
	
	/**
	 * Displays player hand value
	 */
	private JLabel playerLabel;
	
	/**
	 * Displays a message about this game.
	 */
	private JLabel prompt;
	
	
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
		dealerLabel = new JLabel("");
		dealerLabel.setHorizontalAlignment(SwingConstants.CENTER);
		dealerLabel.setForeground(Color.WHITE);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.5;
		c.gridwidth = 5;
		c.gridx = 0;
		c.gridy = row;
		contentPane.add(dealerLabel, c);

		
		// Next line
		row++;
		
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
		playerLabel = new JLabel("");
		playerLabel.setHorizontalAlignment(SwingConstants.CENTER);
		playerLabel.setForeground(Color.WHITE);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.5;
		c.gridwidth = 5;
		c.gridx = 0;
		c.gridy = row;
		contentPane.add(playerLabel, c);

		
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
		prompt = new JLabel("");
		prompt.setHorizontalAlignment(SwingConstants.CENTER);
		prompt.setForeground(Color.WHITE);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.5;
		c.gridwidth = 5;
		c.gridx = 0;
		c.gridy = row;
		contentPane.add(prompt, c);

		
		row++;
		
		// 
		hitButton = new JButton("Hit!");
		hitButton.setActionCommand("hit");
		hitButton.addActionListener(this);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.5;
		c.gridwidth = 1;
		c.gridx = 0;
		c.gridy = row;
		contentPane.add(hitButton, c);
		
		// 
		standButton = new JButton("Stand!");
		standButton.setActionCommand("stand");
		standButton.addActionListener(this);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.5;
		c.gridwidth = 1;
		c.gridx = 1;
		c.gridy = row;
		contentPane.add(standButton, c);

		
		// 
		
		restartButton = new JButton("Play Again");
		restartButton.setActionCommand("restart");
		restartButton.addActionListener(this);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridwidth = 1;
		c.weightx = 0.5;
		c.gridx = 2;
		c.gridy = row;
		contentPane.add(restartButton, c);
		
		
		reshuffleButton = new JButton("ReShuffle");
		reshuffleButton.setActionCommand("reshuffle");
		reshuffleButton.addActionListener(this);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridwidth = 1;
		c.weightx = 0.5;
		c.gridx = 3;
		c.gridy = row;
		contentPane.add(reshuffleButton, c);
		
		

		//
/*		JButton sortBySuit = new JButton("Sort by Suit");
		sortBySuit.setActionCommand("sortBySuit");
		sortBySuit.addActionListener(this);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridwidth = 1;
		c.weightx = 0.5;
		c.gridx = 3;
		c.gridy = row;
		contentPane.add(sortBySuit, c);

		JButton sortByValue = new JButton("Sort by Value");
		sortByValue.setActionCommand("sortByValue");
		sortByValue.addActionListener(this);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridwidth = 1;
		c.weightx = 0.5;
		c.gridx = 4;
		c.gridy = row;
		contentPane.add(sortByValue, c);*/

		
		// Display the window.
		frame.setSize(1000, 600);
		frame.setResizable(true);
		//frame.pack();
		frame.setVisible(true);
		frame.setResizable(false);
		

		resetGame(); // Starts the game for the first time.
		
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
			hitPlayer();
			if (player.getHand().getValue() >= 21)
				moveDealer();
			else
			{
				System.out.println("Hit or Stand?");
				setPrompt("Player's Turn: Hit or Stand?");
			}
		}
		else if ("stand".equals(e.getActionCommand()))
			moveDealer();			
		else if ("restart".equals(e.getActionCommand()))
			playAgain();
		else if ("reshuffle".equals(e.getActionCommand()))
			resetGame();
		else if ("sortByValue".equals(e.getActionCommand()))
			player.getHand().sortByValue();
		else if ("sortBySuit".equals(e.getActionCommand()))
			player.getHand().sortBySuit();
		else
			System.out.println("Unknown action: "+e.getActionCommand());
	}
	
	public void hitPlayer()
	{
		// Deal new card to player
		Card newCard = deck.dealCard();
		newCard.unfold();
		Hand hand = player.getHand();
		hand.addCard(newCard);
		
			if (hand.isBlackJack())
				setPlayerMessage("Blackjack!");
			else if (hand.isBust())
				setPlayerMessage("Player busted! Player's Hand:  "+ hand.getValue());
			else 
				setPlayerMessage("Player's Hand:  "+ hand.getValue());
		
	}
	
	public void moveDealer()
	{
		// Player's turn is over
		hitButton.setEnabled(false);
		standButton.setEnabled(false);
		
		// Next player's turn
		// The Dealer's turn
		setPrompt("Dealer's turn.");
		if (player.getHand().isBust())
			dealer.makeMoves();
		else
			dealer.makeMoves(player.getHand().getValue());
		
		Hand hand = dealer.getHand();
		
		// Set Dealer Message
		if (hand.isBlackJack())
			setDealerMessage("Blackjack!");
		else if (hand.isBust())
			setDealerMessage( "Dealer busted! Dealer's Hand: " + hand.getValue() );
		else 
			setDealerMessage( "Dealer's Hand: " + hand.getValue());
				
		resolveGame();
	}
	
	public void resetGame()
	{
		deck.shuffle();
		playAgain();
	}
	
	/**
	 * 
	 */
	public void playAgain()
	{
		
		// Clear Hands
		dealer.getHand().clearCards();
		player.getHand().clearCards();
		
		// Enable Play Buttons
		hitButton.setEnabled(true);
		standButton.setEnabled(true);
		
		
		// Shuffle Deck 
		dealer.setDeck(deck);
		
		// Temporary fix for shuffling active cards
		if (deck.cardsRemaining() < 20)
			deck.shuffle();
		
		// Deal to players
		dealer.dealCardToPlayer(dealer,	false);
		dealer.dealCardToPlayer(dealer,	true);
		setDealerMessage("Dealer's Hand: " + dealer.getHand().getValue());
		
		dealer.dealCardToPlayer(player,	true);
		dealer.dealCardToPlayer(player,	true);
		
		if (player.getHand().isBlackJack()) 
		{
			setPlayerMessage("Player's Hand: BlackJack!");
			moveDealer();
		}
		else
		{
			setPrompt("Player's turn.");
			setPlayerMessage("Player's Hand: " + player.getHand().getValue());
			
		}
		//
		

		
		
	}
	
	/**
	 * 
	 * @param msg
	 */
	public void setDealerMessage(String msg)
	{
		dealerLabel.setText(msg);
	}
	
	public void setPlayerMessage(String msg)
	{
		playerLabel.setText(msg);
	}
	
	public void setPrompt(String msg)
	{
		prompt.setText(msg);
	}
	
	public void resolveGame()
	{
		// Get the hands to compare
		Hand dHand = dealer.getHand();
		Hand pHand = player.getHand();
		
		// Helper conditions
		boolean handsEqual = ( dHand.getValue() == pHand.getValue() );
		boolean bothBust = dHand.isBust() && pHand.isBust();
		boolean bothBlackJack = dHand.isBlackJack() && pHand.isBlackJack();
		boolean neitherBlackJack = !dHand.isBlackJack() && !pHand.isBlackJack();
		
		// Define the push and win conditions
		boolean isPush = bothBust || bothBlackJack || (handsEqual && neitherBlackJack);
		boolean isWin = !pHand.isBust() && (pHand.getValue() > dHand.getValue()
				|| ( pHand.isBlackJack() && !dHand.isBlackJack() ) || dHand.isBust() );
		
		// Display the appropriate message
		if (isPush)
			setPrompt("Push! Draw Game");
		else if (isWin)
			setPrompt("Player Wins!");
		else
			setPrompt("Dealer Wins!");			
	}
	
	
	
}
