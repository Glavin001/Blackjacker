import java.awt.Container;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;

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

	private JTextArea label;
	

	public CounterView(Counter counter)
	{
		this.counter = counter;
		this.counter.registerView(this);
		
		
		// Create separate Frame
		frame = new JFrame("Card Counter");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//Container contentPane = frame.getContentPane();
		
		// Add Message Label
		label = new JTextArea();
		
		frame.setContentPane(label);
		//frame.getContentPane().add(label);
		// Display the window.
		frame.setSize(200, 600);
		frame.setResizable(true);
		//frame.pack();
		frame.setVisible(true);
		
	}

	@Override
	public void drawItUp() {
		// TODO Auto-generated method stub
		label.setText(counter.getMessage());
		//System.out.println(counter.getMessage());
	}
	
}
