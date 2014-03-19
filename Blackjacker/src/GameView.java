import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

/**
 * 
 */
public class GameView implements ActionListener, View
{
	/**
	 * 
	 */
	private Game game;
	
	/**
	 * 
	 */
	private JFrame frame = new JFrame("Blackjacker");
	/**
	 * 
	 */
	private Container contentPane = frame.getContentPane();
	

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
	
	public GameView(Game g)
	{
		game = g;
		game.registerView(this);
		//System.out.println(game.numViews());
		
		
		// Setup
		frame = new JFrame("Blackjacker");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container contentPane = frame.getContentPane();
		contentPane.setLayout(new GridLayout(2,0));

		contentPane.setBackground( new Color(10,60,70) );

		DealerView dealerView = new DealerView(game.getDealer());
		contentPane.add(dealerView, BorderLayout.LINE_START);
		
		for (Player player : game.getPlayers())
		{
			
			
			
			if (player.getClass().getName() == "Dealer" )
			{
				// Dealer
				//DealerView dealerView = new DealerView((Dealer) player);
				//contentPane.add(dealerView);
			}
			else
			{
				// Player
				PlayerView playerView = new PlayerView(player);
				contentPane.add(playerView, BorderLayout.LINE_START);
			}

			
			
			
			
			
		}
		
		/*
		//contentPane.setLayout(new GridBagLayout());
		// 
		GridBagConstraints c = new GridBagConstraints();		
		
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
		Dealer dealer = game.getDealer();
		DealerView dealerView = new DealerView(dealer);
		dealerView.setBackground( new Color(10,60,70) );
		GridBagConstraints cDealerHand = new GridBagConstraints();
		cDealerHand.fill = GridBagConstraints.HORIZONTAL;
		cDealerHand.ipady = 40;      //make this component tall
		cDealerHand.weightx = 0.0;
		cDealerHand.gridwidth = 5;
		cDealerHand.gridx = 0;
		cDealerHand.gridy = row;
		contentPane.add(dealerView, cDealerHand);
		 
		
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

		/*
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
		 */
		
		/*
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
		
		*/

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
		

	}


	public void drawItUp() {
		// Remove all components from pane
		contentPane.removeAll();
		// Add in currently existing components
		for (Player p : game.getPlayers() )
		{
			contentPane.add(new PlayerView(p));
		}
		// 
		
		// TODO
		
		/*
		// Enable Play Buttons
		hitButton.setEnabled(true);
		standButton.setEnabled(true);
		*/
		
		//
		frame.repaint();
	}

	/**
	 * 
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		// 
		System.out.println(e.getActionCommand());
		// 
		/*
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
			*/
			System.out.println("Unknown action: "+e.getActionCommand());
	
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
	
	

}