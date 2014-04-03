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
 * @author Glavin Wiechert & Dylan
 *
 */
public class Game extends Model
{


	private GameView view;
    
    /**
     * Is the deck being reshuffled?
     */
    private boolean reset = false;
    
    /**
     * The number of decks in the shoe
     */
    private int numDecks; 

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

    /**
     * 
     */
    private Player currentPlayer;



    /**
     * 
     */
    public Game () 
    {
        // Initialize
        this(1);

    }
    
    public Game (int numDecks) 
    {
        // Initialize
        dealer = new Dealer(this);
        deck = new Deck(numDecks);
        this.numDecks = numDecks;

        dealer.setDeck(deck);

        players.add(dealer);
        //notifyViews();

        resetGame(); // Starts the game for the first time.

    }
    
    public void setGameView(GameView v)
    {
    	view = v;
    }
    
    public GameView getGameView()
    {
    	return view;
    }
    
    public int getNumDecks()
    {
    	return numDecks;
    }

    public boolean isReset()
    {
    	return reset;
    }
    public void hitPlayer(Player player)
    {
        // FIXME: Check if this is the current player
        if (player.equals(this.currentPlayer)) 
        {

            // Deal new card to player
            Card newCard = deck.dealCard();
            
 
            
            newCard.unfold();
            
            Hand hand = player.getHand();
            hand.addCard(newCard);

            if (hand.getValue() == 21)
            {
                player.setLabels("Player has 21!");
                moveToNextPlayer();
            }
            else if (hand.isBust())
            {
                player.setLabels("Player busted!");
                moveToNextPlayer();
            }
        }

        notifyViews();
    }

    public void standPlayer(Player player)
    {
        player.setLabels("Player has " 
        		+ player.getHand().getValue());
        moveToNextPlayer();
    }

    /**
     * 
     */
    private void moveToNextPlayer()
    {
        currentPlayer = getNextPlayer(currentPlayer);
        if (currentPlayer.getHand().isBlackJack())
        {
        	moveToNextPlayer();
        	return;
        }
        for (Player p : players)
        {
            if (!p.equals(currentPlayer))
            {
                p.waitForTurn();
            }
        }
        notifyViews();
        currentPlayer.requestMove();
    }


    public void resetGame()
    {
    	reset = true;
        deck.shuffle();
        playAgain();
        reset = false;
    }

    /**
     * 
     */
    public void playAgain()
    {

    	for (Card c : dealer.getHand().getCards())
    		c.unfold();
    	notifyViews();
    	
        // Clear Hands
        for (Player p : players)
        {
            p.getHand().clearCards();
        }

        // Shuffle Deck 
        dealer.setDeck(deck);

        // Temporary fix for shuffling active cards
        if (deck.cardsRemaining() < 10*players.size())
            deck.shuffle();

        for (Player p : players)
        {
            if (p.getClass().equals(Dealer.class))
            {
                // Deal to players
                dealer.dealCardToPlayer(dealer,	false);
                dealer.dealCardToPlayer(dealer,	true);
                //setDealerMessage("Dealer's Hand: " + dealer.getHand().getValue());
            }
            else
            {
            	p.setLabels("Wait your turn.");
                dealer.dealCardToPlayer(p,	true);
                dealer.dealCardToPlayer(p,	true);
            }
            
            if (p.getHand().isBlackJack()) 
                p.setLabels("BlackJack!");
        }

        // Ask first player for move
        currentPlayer = dealer;
        moveToNextPlayer();

        notifyViews();
    }



    /**
     * 
     */
    public void resolveGame()
    {

        // Get the hands to compare
        Hand dHand = dealer.getHand();
        int dealerWins = 0;
        int dealerDraws = 0;

        for (Player player : players)
        {
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

            if (player.getClass() == Player.class)
            {
            // Display the appropriate message
	            if (isPush)
	            {
	                player.setLabels("Push! Draw Game");
	                dealerDraws++;	
	            }
	            else if (isWin)
	                player.setLabels("Player Wins!");
	            else
	            {
	                player.setLabels("Dealer Wins!");
	                dealerWins++;
	            }
            }
           
            String msg = (dealerWins == 0
            		&& dealerDraws == 0 ?
            				"Dealer Loses!"
            				: (dealerWins != 0
            				? "Dealer Wins " + dealerWins
            				: "Dealer Draws" + dealerDraws));
        	if (dealerWins != 0 && dealerDraws != 0)
        		msg += " & Draws " + dealerDraws;
            dealer.setLabels(msg);
        }

    }

    /**
     * 
     * @return
     */
    public ArrayList<Player> getPlayers()
    {
        return players;
    }

    /**
     * Add a new player to the game.  Will start a new round if successful.
     * @param newPlayer
     * @return True if successfully added newPlayer. Will return false if player already added.
     */
    public boolean addPlayer(Player newPlayer) 
    {
        if (!players.contains(newPlayer))
        {
            players.add(newPlayer);
            playAgain();
            notifyViews();
            return true;
        } else {
            return false;
        }
    }
    
    /**
     * Remove player from game. Will start a new round if successful.
     * @param oldPlayer Player to be removed.
     * @return True if successfully removed oldPlayer. Will return false if player was not in game.
     */
    public boolean removePlayer(Player oldPlayer) 
    {
        int idx = players.indexOf(oldPlayer);
        if (idx > -1)
        {
            players.remove(idx);
            playAgain();
            notifyViews();
            return true;
        } else {
            return false;
        }
    }
    
    /**
     * 
     * @return
     */
    public Dealer getDealer()
    {
        return dealer;
    }

    /**
     * 
     * @param player
     * @return
     */
    private int getIndexOfPlayer(Player player) 
    {
        int idx = players.indexOf(player);
        return idx;
    }

    /**
     * 
     * @param player
     * @return
     */
    private int getNextPlayerIndex(Player player) 
    {
        int idx = getIndexOfPlayer(player);
        idx++;
        if (idx >= players.size()) 
        {
            idx = 0;
        }
        return idx;
    }

    /**
     * 
     * @param player
     * @return
     */
    private Player getNextPlayer(Player player) 
    {
        int nextIndex = getNextPlayerIndex(player); 
        if (nextIndex > -1)
        {
            Player next = players.get(nextIndex);
            return next;
        } else {
            return null;
        }
    }

}
