import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 * @author Dylan & Glavin
 *
 */
public class PlayerView extends JPanel implements View, ActionListener {

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
     * Displays player hand value
     */
    private JLabel playerLabel;

    /**
     * 
     */
    public PlayerView(Player p)
    {
        player = p;
        p.registerView(this);

        this.setLayout(new GridBagLayout());

        // 
        GridBagConstraints c = new GridBagConstraints();

        this.setBackground( new Color(10,60,70) );

        //
        int row = 0;

        //
        playerLabel = new JLabel("Player");
        playerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        playerLabel.setForeground(Color.WHITE);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridwidth = 5;
        c.gridx = 0;
        c.gridy = row;
        this.add(playerLabel, c);

        row++;

        //
        HandView hand = new HandView(player.getHand());
        hand.setBackground( new Color(10,60,70) );
        GridBagConstraints cHand = new GridBagConstraints();
        cHand.fill = GridBagConstraints.HORIZONTAL;
        cHand.ipady = 5;   // padding between buttons and cards
        cHand.weightx = 0.0;
        cHand.gridwidth = 5;
        cHand.gridx = 0;
        cHand.gridy = row;
        this.add(hand, cHand);

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
        this.add(hitButton, c);

        // 
        standButton = new JButton("Stand!");
        standButton.setActionCommand("stand");
        standButton.addActionListener(this);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridwidth = 1;
        c.gridx = 1;
        c.gridy = row;
        this.add(standButton, c);
        
    }

    /**
     * 
     */
    public void drawItUp() 
    {
        // Player's turn is over
        boolean isTurn = player.isTurn();
        hitButton.setEnabled(isTurn);
        standButton.setEnabled(isTurn);

        //
        playerLabel.setText(isTurn?
        		"Your turn!": playerLabel.getText());
        //
        repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        // 
        if ("hit".equals(e.getActionCommand()))
        {
            player.hit();
        }
        else if ("stand".equals(e.getActionCommand()))
        {
           player.stand();
        }
        else
        {
            System.out.println("Unknown action: "+e.getActionCommand());
        }
    }

	public JLabel getLabel() 
	{
		return playerLabel;
	}



}
