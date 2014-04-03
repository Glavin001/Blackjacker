import java.util.ArrayList;

/**
 * The Card Counter logic class.
 * @author Glavin Wiechert & Dylan Meijer & Faisal K. AlMazroa
 */
public class Counter extends Model implements View
{

    /**
     * Local reference to the game.
     */
    private Game game;
    private int counter;
    private int trueCount;
    private ArrayList<Card> counted = new ArrayList<Card>();

    /**
     * Constructor
     * @param game
     */
    public Counter(Game game)
    {
        this.game = game;
        this.game.registerView(this); // Watch for changes in game.
        this.game.getDealer().registerCounter(this);
        counter = 0;
    }

    public void updateTrueCount()
    {
        int seen = counted.size()/52;
        trueCount = counter/(game.getNumDecks() - seen);
    }
    public void count()
    {

        if (game.isReset())
        {
            counter = 0;
            trueCount = 0;
            counted = new ArrayList<Card>();
        }

        for(Player p : game.getPlayers())
        {
            for (Card c : p.getHand().getCards())
            {
                if(!c.isFolded())
                    countCard(c);

            }
        }
    }

    public Game getGame()
    {
        return game;
    }

    public void countCard(Card c)
    {

        boolean seen = false;

        for(Card old : counted)
        {
            if (c == old)
                seen = true;		
        }

        if (! seen)
        {
            switch(c.getValue())
            {
            case 10:
            case 1: counter--;
            break;
            case 2:
            case 3:
            case 4: 
            case 5:
            case 6: counter++;
            break;
            }
            counted.add(c);
            updateTrueCount();
        }
    }

    public void display()
    {
        notifyViews();
    }

    /**
     * 
     */
    @Override
    public void drawItUp() 
    {
        count();
    }

    /**
     * Return the string to display in the view
     */
    public String getMessage() 
    {
        String msg = "The count is "+ counter+"\n";
        msg += "The true count is " + trueCount +"\n";

        if (3 < trueCount)
            msg += "Raise your bet.\n";
        else if (0 > trueCount)
            msg += "You're probably going to lose.";
        else
            msg += "Bet the minimum.";

        return msg;
    }





}
