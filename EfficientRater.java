import java.util.ArrayList;
import java.util.HashMap;

public class EfficientRater implements Rater {

    private HashMap<String, Rating> myRatings;
    private String myID;

    public EfficientRater(String id) {
        myRatings = new HashMap<String, Rating>();
        myID = id;
    }

    public void addRating(String item, double rating) {
        Rating r = new Rating(item, rating);
        myRatings.put(item, r);
    }

    public boolean hasRating(String item) {
        return myRatings.keySet().contains(item);
    }

    @Override
    public String getID() {
        return myID;
    }


    public double getRating(String item) {
        if (!hasRating(item)) {
            return -1;
        } else {
            return myRatings.get(item).getValue();
        }

    }

    public int numRatings() {
        return myRatings.size();
    }

    public ArrayList<String> getItemsRated() {
        ArrayList<String> itemRatedList = new ArrayList<>();
        for (String s : myRatings.keySet()) {
            itemRatedList.add(s);
        }
        return itemRatedList;
    }

    @Override
    public ArrayList<Rating> getRatingList() {
        ArrayList<Rating> ratingList = new ArrayList<>();
        for (String s : myRatings.keySet()) {
            ratingList.add(myRatings.get(s));
        }
        return ratingList;
    }

}
