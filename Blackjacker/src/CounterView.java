import java.awt.Color;
import java.awt.Container;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

/**
 * 
 */


/**
 * @author s7113041
 *
 */
public class CounterView  implements View
{

	/**
	 * 
	 */
	private Counter counter;
	


	/**
	 * 
	 */
	public CounterView(Counter counter)
	{
		this.counter = counter;
		this.counter.registerView(this);
	}

	@Override
	public void drawItUp() 
	{
		JOptionPane.showMessageDialog(counter.getGame()
				.getGameView().getFrame()
				, counter.getMessage());

	}
	
}
