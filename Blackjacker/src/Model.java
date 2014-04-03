import java.util.ArrayList;


/**
 * 
 */
public abstract class Model {
	private ArrayList<View> views = new ArrayList<View>();
	
	

	/**
	 * 
	 */
	public void registerView(View v)
	{
		views.add(v);
	}

	/**
	 * 
	 */
	public void notifyViews()
	{
		for (View v : views)
		{
			v.drawItUp();
		}
	}
	
	public int numViews()
	{
		return views.size();
	}
	
	/**
	 * 
	 */
	public  ArrayList<View> getViews()
	{
		return views;
	}
	
}
