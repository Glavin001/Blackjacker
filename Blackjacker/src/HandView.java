import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * The Hand model's view.
 * @author Glavin Wiechert & Dylan Meijer & Faisal K. AlMazroa
 */
public class HandView extends JPanel implements View
{
    /**
     * The hand.
     */
    private Hand hand;

    /**
     * Constructor
     * @param h
     */
    public HandView(Hand h)
    {
        this.setLayout(new FlowLayout(FlowLayout.LEFT));


        hand = h;
        h.registerView(this);
    }


    /**
     * Handle the model change event.
     */
    public void drawItUp() 
    {
        this.removeAll();
        // Display Hand Value
        JLabel handLabel = new JLabel("Hand: "+hand.getValue());
        handLabel.setForeground(Color.WHITE);
        this.add(handLabel);
        // Display Cards
        ArrayList<Card> cards = hand.getCards();
        for (Card card : cards)
        {
            CardView temp = new CardView(card);
            this.add(temp);
        }

        //System.out.println("Cards " + hand.cardsInHand());
        revalidate();
        repaint(); 

    }

    /**
     * Paint to the view.
     */
    public void paint(Graphics g)
    {
        //System.out.println("Paint Hand");
        super.paint(g);
        super.paintComponents(g);

    }

}
