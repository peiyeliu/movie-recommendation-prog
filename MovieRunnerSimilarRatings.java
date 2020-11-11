import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class MovieRunnerSimilarRatings {

    private FourthRatings fourthRatings = new FourthRatings();

    public void printAverageRatings(String ID, int numOfSimilarRaters, int minimalRater) {
        ArrayList<Rating> result = fourthRatings.getSimilarRatings(ID, numOfSimilarRaters, minimalRater);
        Collections.sort(result, Collections.reverseOrder());
        System.out.println("The movie on the top is :" + MovieDatabase.getTitle(result.get(0).getItem()));
    }

    public void printAverageRatingsByYearAfterAndMinutes(String ID, int numOfSimilarRaters, int minimalRater, int year,
                                                         int start, int end) {
        Filter yearAfterFilter = new YearAfterFilter(year);
        Filter minuteFilter = new MinuteFilter(start, end);
        AllFilters f = new AllFilters();
        f.addFilter(yearAfterFilter);
        f.addFilter(minuteFilter);
        ArrayList<Rating> result = fourthRatings.getSimilarRatingsByFilter(ID, numOfSimilarRaters, minimalRater, f);
        Collections.sort(result, Collections.reverseOrder());
        System.out.println("The movie on the top is :" + MovieDatabase.getTitle(result.get(0).getItem()));
        /**
         System.out.println("There are " + result.size() + " movies found.");

         for (Rating r : result) {
         System.out.println("Movie name: " + MovieDatabase.getTitle(r.getItem()) +
         "; Year: " + MovieDatabase.getYear(r.getItem()) +
         "; Genre: " + MovieDatabase.getGenres(r.getItem()) +
         "; rating = " + r.getValue());
         }
         */

    }

    public void printAverageRatingsByGenre(String ID, int numOfSimilarRaters, int minimalRater, String genre) {
        Filter f = new GenreFilter(genre);
        ArrayList<Rating> result = fourthRatings.getSimilarRatingsByFilter(ID, numOfSimilarRaters, minimalRater, f);
        //System.out.println("There are " + result.size() + " movies found.");
        Collections.sort(result, Collections.reverseOrder());
        for (int i = 0; i < 15; i++) {
            System.out.println(MovieDatabase.getTitle(result.get(i).getItem()));
        }

    }

    public void printAverageRatingsByGenreAndMinutes(String ID, int numOfSimilarRaters, int minimalRater,
                                                     String genre, int start, int end) {
        Filter genreFilter = new GenreFilter(genre);
        Filter minuteFilter = new MinuteFilter(start, end);
        AllFilters f = new AllFilters();
        f.addFilter(genreFilter);
        f.addFilter(minuteFilter);
        ArrayList<Rating> result = fourthRatings.getSimilarRatingsByFilter(ID, numOfSimilarRaters, minimalRater, f);
        //System.out.println("There are " + result.size() + " movies found.");
        Collections.sort(result, Collections.reverseOrder());
        //System.out.println("The movie on the top is :" + MovieDatabase.getTitle(result.get(0).getItem()));


    }

    public void printAverageByDirector(String ID, int numOfSimilarRaters, int minimalRater,
                                       ArrayList<String> director) {
        ArrayList<Rating> result = new ArrayList<Rating>();
        HashMap<String, Rating> r = new HashMap<String, Rating>();
        for (String d : director) {
            Filter f = new DirectorFilter(d);
            ArrayList<Rating> FilterResult = fourthRatings.getSimilarRatingsByFilter(ID, numOfSimilarRaters,
                    minimalRater, f);
            for (Rating rating : FilterResult) {
                if (!r.keySet().contains(rating.getItem())) {
                    r.put(rating.getItem(), rating);
                }
            }
        }
        for (String k : r.keySet()) {
            result.add(r.get(k));
        }

        //System.out.println("There are " + r.size() + " movies found");
        Collections.sort(result, Collections.reverseOrder());
        System.out.println("The movie on the top is :" + MovieDatabase.getTitle(result.get(0).getItem()));

        //printAverageRatings(Result);
    }


}
