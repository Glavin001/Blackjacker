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

    /**
     * 
     */
    private Player currentPlayer;

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
        dealer = new Dealer(this);
        deck = new Deck();

        dealer.setDeck(deck);

        players.add(dealer);
        //notifyViews();

        resetGame(); // Starts the game for the first time.

    }


    public void hitPlayer(Player player)
    {
        // FIXME: Check if this is the current player
        if (player.equals(this.currentPlayer)) {

            // Deal new card to player
            Card newCard = deck.dealCard();
            newCard.unfold();
            Hand hand = player.getHand();
            hand.addCard(newCard);

            if (hand.isBlackJack())
            {
                //setPlayerMessage("Blackjack!");
                moveToNextPlayer();
            }
            else if (hand.isBust())
            {
                //setPlayerMessage("Player busted! Player's Hand:  "+ hand.getValue());
                moveToNextPlayer();
            }
            else 
            {
                //setPlayerMessage("Player's Hand:  "+ hand.getValue());

            }

        }
        else {
            System.out.println("Player, wait your turn!");
        }
        notifyViews();
    }

    public void standPlayer(Player player)
    {
        moveToNextPlayer();
    }

    /**
     * 
     */
    private void moveToNextPlayer()
    {
        currentPlayer = getNextPlayer(currentPlayer);
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

    /**
     * 
     */
    public void moveDealer()
    {
        System.out.println("Dealer's move!");

        // FIXME
        // Next player's turn
        // The Dealer's turn
        setPrompt("Dealer's turn.");
        /*
        if (player.getHand().isBust())
            dealer.makeMoves();
        else
            dealer.makeMoves(player.getHand().getValue());
        Hand hand = dealer.getHand();
         */

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
        for (Player p : players)
        {
            p.getHand().clearCards();
        }

        // Shuffle Deck 
        dealer.setDeck(deck);

        // Temporary fix for shuffling active cards
        if (deck.cardsRemaining() < 20)
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
                dealer.dealCardToPlayer(p,	true);
                dealer.dealCardToPlayer(p,	true);
            }
        }

        /*
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
         */
        //

        // Ask first player for move
        currentPlayer = dealer;
        moveToNextPlayer();

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

    /**
     * 
     */
    public void resolveGame()
    {

        // Get the hands to compare
        Hand dHand = dealer.getHand();

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

            // Display the appropriate message
            if (isPush)
                setPrompt("Push! Draw Game");
            else if (isWin)
                setPrompt("Player Wins!");
            else
                setPrompt("Dealer Wins!");
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
