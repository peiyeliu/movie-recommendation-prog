import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class FourthRatings {


    public FourthRatings() {
        MovieDatabase.initialize("ratedmoviesfull.csv");
        RaterDatabase.initialize("ratings.csv");
        //System.out.println(MovieDatabase.size() + " movies and " + RaterDatabase.size() + " raters.");
    }

    public FourthRatings(String movieFilename, String raterFilename) {
        MovieDatabase.initialize(movieFilename);
        RaterDatabase.initialize(raterFilename);
        //System.out.println(MovieDatabase.size() + " movies and " + RaterDatabase.size() + " raters.");
    }


    public double getAverageByID(String id, int minimalRater) {
        int count = 0;
        double totalscore = 0.0;
        for (Rater x : RaterDatabase.getRaters()) {
            if (x.getRating(id) != -1) {
                count++;
                totalscore += x.getRating(id);
            }
        }
        if (count >= minimalRater) {
            return totalscore / count;
        } else {
            return 0.0;
        }
    }

    public double getWeightedAverageByID(String movieId, String raterID, int numSimilarRaters) {

        ArrayList<Rating> similaritiesList = getSimilarities(raterID, numSimilarRaters);

        double totalscore = 0.0;
        int count = 0;

        for (Rating x : similaritiesList) {
            if (RaterDatabase.getRater(x.getItem()).hasRating(movieId)) {
                totalscore += x.getValue() * RaterDatabase.getRater(x.getItem()).getRating(movieId);
                count++;
            }
        }
        return totalscore / count;
    }


    public ArrayList<Rating> getAverageRatings(int minimalRaters) {
        ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());
        ArrayList<Rating> RatingByMovie = new ArrayList<>();
        for (String movieID : movies) {
            if (getAverageByID(movieID, minimalRaters) != 0.0) {
                Rating r = new Rating(movieID, getAverageByID(movieID, minimalRaters));
                RatingByMovie.add(r);
            }
        }
        return RatingByMovie;
    }

    public ArrayList<Rating> getAverageByFilter(int minimalRater, Filter f) {
        ArrayList<Rating> result = new ArrayList<>();
        ArrayList<String> idList = MovieDatabase.filterBy(f);

        for (String movieID : idList) {
            if (this.getAverageByID(movieID, minimalRater) != 0.0) {
                Rating r = new Rating(movieID, this.getAverageByID(movieID, minimalRater));
                result.add(r);
            }
        }
        return result;
    }

    public double dotProduct(Rater r, Rater me) {
        double dotProductResult = 0.0;
        for (String ratedMovieId : r.getItemsRated()) {
            if (me.getItemsRated().contains(ratedMovieId)) {
                dotProductResult += (me.getRating(ratedMovieId) - 5.0) * (r.getRating(ratedMovieId) - 5.0);
            }
        }
        return dotProductResult;
    }

    public ArrayList<Rating> getSimilarities(String id, int numSimilarRaters) {
        ArrayList<Rating> similaritiesList = new ArrayList<>();
        ArrayList<Rating> similaritiesListOutPut = new ArrayList<>();
        Rater me = RaterDatabase.getRater(id);
        for (Rater r : RaterDatabase.getRaters()) {
            if (r != me) {
                similaritiesList.add(new Rating(r.getID(), dotProduct(r, RaterDatabase.getRater(id))));
            }
        }

        Collections.sort(similaritiesList, Collections.reverseOrder());

        for (int i = 0; i < numSimilarRaters; i++) {
            similaritiesListOutPut.add(similaritiesList.get(i));
        }

        return similaritiesListOutPut;
    }

    public ArrayList<Rating> getSimilarRatings(String id, int numSimilarRaters, int minimalRaters) {
        /**
         ArrayList<Rating> similaritiesMovies = new ArrayList<>();
         ArrayList<Rating> raterList = getSimilarities(id);
         ArrayList<String> raterIdList = new ArrayList<>();
         for (int i = 0; i < numSimilarRaters; i++) {
         raterIdList.add(raterList.get(i).getItem());
         }
         HashMap<String, Integer> movieWithRatingNum = new HashMap<>();
         for (String raterID : raterIdList) {
         for (String movieID : RaterDatabase.getRater(raterID).getItemsRated()) {
         if (!movieWithRatingNum.keySet().contains(movieID)) {
         movieWithRatingNum.put(movieID, 1);
         } else {
         int countNum = movieWithRatingNum.get(movieID);
         movieWithRatingNum.put(movieID, countNum + 1);
         }
         }
         }
         for (String movieId : movieWithRatingNum.keySet()) {
         if (movieWithRatingNum.get(movieId) >= minimalRaters) {
         similaritiesMovies.add(new Rating(movieId, getWeightedAverageByID(movieId, id)));
         }
         }
         Collections.sort(similaritiesMovies, Collections.reverseOrder());
         return similaritiesMovies;
         */
        AllFilters f = new AllFilters();
        return getSimilarRatingsByFilter(id, numSimilarRaters, minimalRaters, f);
    }

    public ArrayList<Rating> getSimilarRatingsByFilter(String id, int numSimilarRaters, int minimalRaters,
                                                       Filter f) {
        ArrayList<Rating> similaritiesMovies = new ArrayList<>();
        ArrayList<String> moviesFiltered = MovieDatabase.filterBy(f);
        ArrayList<Rating> raterList = getSimilarities(id, numSimilarRaters);
        ArrayList<String> raterIdList = new ArrayList<>();
        for (int i = 0; i < raterList.size(); i++) {
            raterIdList.add(raterList.get(i).getItem());
        }
        HashMap<String, Integer> movieWithRatingNum = new HashMap<>();
        for (String raterID : raterIdList) {
            for (String movieID : RaterDatabase.getRater(raterID).getItemsRated()) {
                if (!movieWithRatingNum.keySet().contains(movieID)) {
                    movieWithRatingNum.put(movieID, 1);
                } else {
                    int countNum = movieWithRatingNum.get(movieID);
                    movieWithRatingNum.put(movieID, countNum + 1);
                }
            }
        }
        for (String movieId : movieWithRatingNum.keySet()) {
            if (movieWithRatingNum.get(movieId) >= minimalRaters && moviesFiltered.contains(movieId)) {
                similaritiesMovies.add(new Rating(movieId, getWeightedAverageByID(movieId, id, numSimilarRaters)));
            }
        }
        Collections.sort(similaritiesMovies, Collections.reverseOrder());
        return similaritiesMovies;
    }

}
