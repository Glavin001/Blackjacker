import java.util.ArrayList;

/**
 *
 */

/**
 * @author glavin Faisal K Al Mazroa A.K.A ./~Saudi Coder 
 *
 */
public class Hand
{

    private ArrayList<Card> cards;

    public Hand()
    {
        cards = new ArrayList<Card>();
    }
    /**
     *
     */
    public void clearCards()
    {
        cards.clear();
    }

    /**
     *
     * @param card
     * @return
     */
    public void removeCard(Card theCard)
    {
        cards.remove(theCard);
    }

    /**
     *
     */
    public void addCard(Card theCard)
    {
        if (theCard != null)
        {
            cards.add(theCard);
        }
    }

    /**
     *
     * @return
     */
    public int cardsInHand()
    {
        return cards.size();
    }

    /**
     *
     */
    public void sortBySuite()
    {

        ArrayList<Card> temp = new ArrayList<Card>();
        while (cards.size() > 0)
        {
            // we target the minimum card.
            int position = 0;
            Card tempCard = (Card) cards.get(0); // to have the smallest card.
            for (int i = 1; i < cards.size(); i++)
            {
                Card card1 = (Card) cards.get(i);
                if (card1.getSuit() < tempCard.getSuit() ||
                        card1.getSuit() == tempCard.getSuit() &&
                        card1.getValue() < tempCard.getValue())
                {
                    position = i;
                    tempCard = card1;
                }
            }
            cards.remove(position);
            temp.add(tempCard);
        }
        cards = temp;
    }

    /**
     *
     */
    public void sortByValue()
    {
        ArrayList<Card> temp = new ArrayList<Card>();
        while (cards.size() > 0)
        {
            // we target the minimum card.
            int position = 0;
            Card tempCard = (Card) cards.get(0); // to have the smallest card.
            for (int i = 1; i < cards.size(); i++)
            {
                Card card1 = (Card) cards.get(i);
                if (card1.getValue() < tempCard.getValue() ||
                        card1.getValue() == tempCard.getValue() &&
                        card1.getSuit() < tempCard.getSuit())
                {
                    position = i;
                    tempCard = card1;
                }
            }
            cards.remove(position);
            temp.add(tempCard);
        }
        cards = temp;
    }

    /**
     * we do not need this because sorting both the values and suit will sort all the cards !!=
    public void sortCards()
    {

    }*/

}
