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
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 * 
 */
public class GameView implements View
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
	
	public GameView(Game g)
	{
		game = g;
		game.registerView(this);
		game.setGameView(this);
		
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
			if (player.getClass().getName() == "Player" )
			{
				// Player
				PlayerView playerView = new PlayerView(player);
				contentPane.add(playerView, BorderLayout.LINE_START);
			}	
		}
		
		// Display the window.
		if (game.getPlayers().size() > 2)
			frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		else
			frame.setSize(1000, 600);
		
		frame.setResizable(true);
		//frame.pack();
		frame.setVisible(true);
		

	}
	

	public void drawItUp() 
	{
		// Remove all components from pane
		contentPane.removeAll();
		
		// Add in currently existing components
		for (Player p : game.getPlayers() )
		{
			contentPane.add(new PlayerView(p));
		}
		
		frame.repaint();
	}	
	
	public JFrame getFrame()
	{
		return frame;
	}


	
	

}
