import java.util.ArrayList;


/**
 * The Model to observe.
 * @author Glavin Wiechert & Dylan Meijer & Faisal K. AlMazroa
 */
public abstract class Model {
    /**
     * All observers.
     */
    private ArrayList<View> views = new ArrayList<View>();


    /**
     * Register the view to observe this model.
     */
    public void registerView(View v)
    {
        views.add(v);
    }

    /**
     * Notify all observers of model change.
     */
    public void notifyViews()
    {
        for (View v : views)
        {
            v.drawItUp();
        }
    }

    /**
     * Get the number of observers.
     * @return The number of observers.
     */
    public int numViews()
    {
        return views.size();
    }

    /**
     * Get all observing views.
     * @return  All observing views.
     */
    public  ArrayList<View> getViews()
    {
        return views;
    }

}
