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
     * 
     */
    private JButton restartButton;

    /**
     * 
     */	
    private JButton reshuffleButton;


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
        cHand.ipady = 60;      //make this component tall
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


        // 
        restartButton = new JButton("Play Again");
        restartButton.setActionCommand("restart");
        restartButton.addActionListener(this);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridwidth = 1;
        c.weightx = 0.5;
        c.gridx = 2;
        c.gridy = row;
        this.add(restartButton, c);

        reshuffleButton = new JButton("ReShuffle");
        reshuffleButton.setActionCommand("reshuffle");
        reshuffleButton.addActionListener(this);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridwidth = 1;
        c.weightx = 0.5;
        c.gridx = 3;
        c.gridy = row;
        this.add(reshuffleButton, c);
        
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
        playerLabel.setText(isTurn?"Your turn!":"Wait your turn.");
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
            /*
            if (player.getHand().getValue() >= 21)
                moveDealer();
            else
            {
                System.out.println("Hit or Stand?");
                setPrompt("Player's Turn: Hit or Stand?");
            }
            */
        }
        else if ("stand".equals(e.getActionCommand()))
        {
           player.stand();
            //moveDealer();
        }
        else if ("restart".equals(e.getActionCommand()))
        {
            player.playAgain();
        }
        else if ("reshuffle".equals(e.getActionCommand()))
        {
            player.resetGame();
        }
        else if ("sortByValue".equals(e.getActionCommand()))
        {
            player.getHand().sortByValue();
        }
        else if ("sortBySuit".equals(e.getActionCommand()))
        {
            player.getHand().sortBySuit();
        }
        else
        {
            System.out.println("Unknown action: "+e.getActionCommand());
        }
    }



}
