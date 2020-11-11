import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class RecommendationRunner implements Recommender {
    @Override
    public ArrayList<String> getItemsToRate() {
        GenreFilter f = new GenreFilter("Comedy");
        ArrayList<String> allComedyMovieList = MovieDatabase.filterBy(f);
        ArrayList<String> randomSelectedList = new ArrayList<>();
        Random r = new Random();
        while (randomSelectedList.size() < 15) {
            int idx = r.nextInt(allComedyMovieList.size());
            if (!randomSelectedList.contains(allComedyMovieList.get(idx))) {
                randomSelectedList.add(allComedyMovieList.get(idx));
            }
        }

        return randomSelectedList;
    }

    @Override
    public void printRecommendationsFor(String webRaterID) {
        FourthRatings fourthRatings = new FourthRatings();
        ArrayList<Rating> recommendationList = fourthRatings.getSimilarRatings(webRaterID, 15, 3);
        try {
            Collections.sort(recommendationList, Collections.reverseOrder());
            System.out.print("<html>");
            System.out.print("<h1 class=\"heading\"> Top 10 Recommended Comedy Movies </h1>");
            System.out.print("<div id = 'd1'>\n" +
                    "The table below showed top 10 comedy movies recommended for you base on your ratings.\n" +
                    "</div>");
            System.out.print("<table class=\"movieTable\">");
            System.out.print("<tr class=\"tableRow\">\n" +
                    "<th>Ranking</th>\n" +
                    "<th>Movie name</th>\n" +
                    "<th>Average rating</th>\n" +
                    "<th>Year</th>\n" +
                    "</tr>");
            for (int i = 0; i < 10; i++) {
                String movieName = MovieDatabase.getTitle(recommendationList.get(i).getItem());
                double Rating = recommendationList.get(i).getValue();
                int year = MovieDatabase.getYear(recommendationList.get(i).getItem());
                int ranking = i + 1;
                System.out.print("<tr>\n" +
                        "<th>" + ranking + "</th>\n" +
                        "<th>" + movieName + "</th>\n" +
                        "<th>" + String.format("%.2f", Rating) + "</th>\n" +
                        "<th>" + year + "</th>\n" +
                        "</tr>");
            }
            System.out.print("</table>");
            System.out.print("</html>");
            System.out.println("<style>.heading{\n" +
                    "   color: Maroon;\n" +
                    "   font-family: arial;\n" +
                    "}");
            System.out.println(".movieTable{\n" +
                    "border: 6px solid #948473;\n" +
                    "background-color: #FFE3C6;\n" +
                    "width: 100%;\n" +
                    "text-align: center;\n" +
                    "}");
        } catch (
                Exception IndexOutOfBoundsException) {
            System.out.print("We could not provide 10 recommended movies base on your rating input, please modify " +
                    "your rating.");
        }

    }
}
