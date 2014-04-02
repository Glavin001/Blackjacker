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
	private JLabel messageLabel;
	
	/**
	 * 
	 */
	private JFrame frame;

	/**
	 * 
	 */
	private Container contentPane;

	private JTextPane label;
	

	public CounterView(Counter counter)
	{
		this.counter = counter;
		this.counter.registerView(this);
		
		
		// Create separate Frame
		frame = new JFrame("Card Counter");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container pane = frame.getContentPane();
		
		// Add Message Label
		label = new JTextPane();
		
		
		// Display the window.
		frame.setSize(200, 600);
		//frame.setResizable(true);

		label.setBackground( new Color(10,60,70) );
		pane.add(label);
		label.setForeground(Color.WHITE);
		StyledDocument doc = label.getStyledDocument();
		SimpleAttributeSet center = new SimpleAttributeSet();
		StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
		doc.setParagraphAttributes(0, doc.getLength(), center, false);
		
		//frame.pack();
		//frame.setVisible(true);
		
	}

	@Override
	public void drawItUp() {
		// TODO Auto-generated method stub
		//label.setText(counter.getMessage());
		JOptionPane.showMessageDialog(frame, counter.getMessage());
		//System.out.println(counter.getMessage());
	}
	
}
