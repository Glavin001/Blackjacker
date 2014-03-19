import java.awt.Color;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

/**
 * @author Glavin Wiechert
 *
 */
public class Game extends Model
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
	private ArrayList<Player> players = new ArrayList<Player>();
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
	private JButton reshuffleButton;
	
	/**
	 * Displays a message about this game.
	 */
	private String prompt;
	
	
	/**
	 * 
	 */
	public Game () 
	{
		// Initialize
		
		dealer = new Dealer();
		deck = new Deck();
		player = new Player();
		

		dealer.setDeck(deck);

		
		// FIXME: Add players
		players.add(dealer);
		players.add(player);
		//notifyViews();

		//resetGame(); // Starts the game for the first time.
		
	}

	
	public void hitPlayer()
	{
		// Deal new card to player
		Card newCard = deck.dealCard();
		newCard.unfold();
		Hand hand = player.getHand();
		hand.addCard(newCard);
		/*
			if (hand.isBlackJack())
				setPlayerMessage("Blackjack!");
			else if (hand.isBust())
				setPlayerMessage("Player busted! Player's Hand:  "+ hand.getValue());
			else 
				setPlayerMessage("Player's Hand:  "+ hand.getValue());
		*/
		notifyViews();
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
		
		/*
		// Set Dealer Message
		if (hand.isBlackJack())
			setDealerMessage("Blackjack!");
		else if (hand.isBust())
			setDealerMessage( "Dealer busted! Dealer's Hand: " + hand.getValue() );
		else 
			setDealerMessage( "Dealer's Hand: " + hand.getValue());
		*/
		notifyViews();
		
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
		
		// Shuffle Deck 
		dealer.setDeck(deck);
		
		// Temporary fix for shuffling active cards
		if (deck.cardsRemaining() < 20)
			deck.shuffle();
		
		// Deal to players
		dealer.dealCardToPlayer(dealer,	false);
		dealer.dealCardToPlayer(dealer,	true);
		//setDealerMessage("Dealer's Hand: " + dealer.getHand().getValue());
		
		dealer.dealCardToPlayer(player,	true);
		dealer.dealCardToPlayer(player,	true);
		
		if (player.getHand().isBlackJack()) 
		{
			//setPlayerMessage("Player's Hand: BlackJack!");
			moveDealer();
		}
		else
		{
			setPrompt("Player's turn.");
			//setPlayerMessage("Player's Hand: " + player.getHand().getValue());
			
		}
		//
		
		notifyViews();
		
		
	}
	
	/**
	 * 
	 * @param msg
	 */
	public void setPrompt(String msg)
	{
		prompt = msg;
		notifyViews();
	}
	
	/**
	 * 
	 */
	public String getPrompt()
	{
		return prompt;
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
	
	public ArrayList<Player> getPlayers()
	{
		return players;
	}
	
	public Dealer getDealer()
	{
		return dealer;
	}
}
