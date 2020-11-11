import java.util.ArrayList;
import java.util.Arrays;

public class MovieRunnerTester {
    public static void main(String[] args) {
        /**
         MovieRunnerWithFilter m = new MovieRunnerWithFilter();
         m.printAverageByYearAfter(20, 2000);
         m.printAverageByGenre(20, "Comedy");
         m.printAverageByMinute(5, 105, 135);
         ArrayList<String> directorList = new ArrayList<>(Arrays.asList("Clint Eastwood", "Joel Coen", "Martin Scorsese",
         "Roman Polanski", "Nora Ephron", "Ridley Scott",
         "Sydney Pollack"));
         m.printAverageByDirector(4, directorList);
         m.printAverageRatingsByYearAfterAndGenre(8, 1990, "Drama");
         ArrayList<String> directorList2 = new ArrayList<>(Arrays.asList("Clint Eastwood", "Joel Coen", "Tim Burton", "Ron Howard", "Nora Ephron",
         "Sydney Pollack"));
         m.printAverageRatingsByDirectorsAndMinutes(3, directorList2, 90, 180);
         */

        MovieRunnerSimilarRatings ms = new MovieRunnerSimilarRatings();
        ms.printAverageRatings("337", 10, 3);
        ms.printAverageRatingsByGenre("964", 20, 5, "Mystery");
        ms.printAverageRatings("71", 20, 5);
        ArrayList<String> directorList = new ArrayList<>(Arrays.asList("Clint Eastwood", "J.J. Abrams", "Alfred " +
                "Hitchcock", "Sydney Pollack", "David Cronenberg", "Oliver Stone", "Mike Leigh"));
        ms.printAverageByDirector("120", 10, 2, directorList);
        ms.printAverageRatingsByGenreAndMinutes("168", 10, 3, "Drama", 80, 160);
        ms.printAverageRatingsByYearAfterAndMinutes("314", 10, 5, 1975, 70, 200);


    }
}
