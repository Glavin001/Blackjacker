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
 * 
 */

/**
 * @author Dylan
 *
 */
public class CardView extends JComponent implements View
{

    /**
     * 
     */
    private Dimension preferredSize;

    /**
     * 
     */
    private Card card;

    /**
     *
     * A constructor to create a Card object that will be either a "suit" or "value" card.
     */
    public CardView(Card card)
    {
        this.card = card;
        card.registerView(this);

        // Size of playing cards: 
        //     http://www.ask.com/question/what-are-the-dimensions-of-a-standard-playing-card 
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

    private BufferedImage getImage()
    {
        //System.out.println(card.getValue()+" "+card.getSuit());
        String path = "rsrc/Images/";
        switch (card.getValue())
        {
        case 2:
        case 3:
        case 4:
        case 5:
        case 6:
        case 7:
        case 8:
        case 9:
        case 10:
            path += card.getValue();
            break;
        case 11: 
            path += "J";
            break;
        case 12:
            path += "Q";
            break;
        case 13:
            path += "K";
            break;
        case 1:
            path += "A";
        }
        switch (card.getSuit())
        {
        case Card.SPADES:
            path += "S";
            break;
        case Card.HEARTS:
            path += "H";
            break;
        case Card.DIAMONDS:
            path += "D";
            break;
        case Card.CLUBS:
            path += "C";
            break;
        }
        path += ".png";

        URL resource = getClass().getResource(path);
        //System.out.println(path);

        try
        {
            return ImageIO.read(resource);
        }
        catch (Exception e)
        {
            return null; 
        }
    }

    public void paintComponent(Graphics g) 
    {
        System.out.println("Printing Card");
        Graphics2D g2 = (Graphics2D) g;
        super.paintComponent(g);  

        if (card.isFolded())
        {
            // Face down and hidden to players

            /* */
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
                //System.out.println(r);
                g2.fill(r);

                // Face up to players
                g2.setColor(Color.BLACK);
                FontMetrics fm = g.getFontMetrics();
                String str = "*Face Down*";
                // Draw message
                g2.drawString(str, this.getWidth()/2 - fm.stringWidth(str)/2, this.getHeight()/2);
            }


        }
        else
        {
            /*
             */
            BufferedImage cardImage = getImage();
            if (cardImage != null)
                g2.drawImage(cardImage, 0, 0, this.getWidth(), this.getHeight(),
                        0, 0, cardImage.getWidth(), cardImage.getHeight(),
                        this);
            else
            {
                // Draw background
                g2.setColor(Color.WHITE);
                Rectangle r = new Rectangle(0, 0, this.getSize().width, this.getSize().height);
                //System.out.println(r);
                g2.fill(r);

                // Face up to players
                g2.setColor(Color.BLACK);
                FontMetrics fm = g.getFontMetrics();
                String valStr = card.valueToString();
                String suitStr = card.suitToString();
                // Draw Value
                g2.drawString(valStr, fm.getHeight(), fm.getHeight());
                g2.drawString(valStr, this.getWidth() - fm.stringWidth(valStr) - fm.getHeight(), this.getHeight() - fm.getHeight());
                // Draw Suit
                g2.drawString(suitStr, this.getWidth()/2 - fm.stringWidth(suitStr)/2, this.getHeight()/2);
            }

        }        
    }

    @Override
    public void drawItUp() 
    {
        repaint();
    }

}
