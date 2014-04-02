import java.awt.Color;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.JComponent;

/**
 * @author Glavin Wiechert && Faisal K Al Mazroa A.K.A ./~Saudi Coder
 * 
 */
public class DeckView extends JComponent implements View {

    private Dimension preferredSize;
    private Deck deck;

    public DeckView(Deck d)
    {
        deck = d;
        d.registerView(this);
        float sizeRatio = 2;
        preferredSize = new Dimension( (int) (63*sizeRatio), (int) (89*sizeRatio) ); // new Dimension(63, 89);
    }

    public Dimension preferredSize()
    {
        return preferredSize;
    }

    public Dimension minimumSize()
    {
        return preferredSize;
    }


    public void drawItUp() 
    {
        repaint();
    }

    /**
     * 
     */
    public void paintComponent(Graphics g) 
    {
        Graphics2D g2 = (Graphics2D) g;
        super.paintComponent(g);  

        URL resource = getClass().getResource("rsrc/Images/back.png");
        BufferedImage bg;
        try
        {
            bg = ImageIO.read(resource);
            g2.drawImage(bg, 0, 0, this.getWidth(), this.getHeight(),
                    0, 0, bg.getWidth(), bg.getHeight(),
                    this);
        }
        catch (Exception e)
        {
            // Draw background
            g2.setColor(Color.RED);
            Rectangle r = new Rectangle(0, 0, this.getSize().width, this.getSize().height);
            ////System.out.println(r);
            g2.fill(r);

            // Face up to players
            g2.setColor(Color.BLACK);
            FontMetrics fm = g.getFontMetrics();
            String str = "*Face Down*";
            // Draw message
            g2.drawString(str, this.getWidth()/2 - fm.stringWidth(str)/2, this.getHeight()/2);
        }

        g2.setColor(Color.BLACK);
        FontMetrics fm = g.getFontMetrics();
        String str = "Cards: " + deck.cardsRemaining();
        // Draw cards remaining
        g2.drawString(str, this.getWidth()/2 - fm.stringWidth(str)/2, this.getHeight() - fm.stringWidth(str)/2);

    }


}
