import edu.duke.FileResource;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.util.ArrayList;

public class FirstRatings {

    public FirstRatings() {

    }

    public ArrayList<Movie> loadMovie(String filename) {
        ArrayList<Movie> movieList = new ArrayList<Movie>();
        FileResource fr = new FileResource("data/" + filename);
        CSVParser Parser = fr.getCSVParser();
        for (CSVRecord record : Parser) {
            String ID = record.get("id");
            String Title = record.get("title");
            String Year = record.get("year");
            String Country = record.get("country");
            String Genre = record.get("genre");
            String Director = record.get("director");
            String Minutes = record.get("minutes");
            int min = Integer.parseInt(Minutes);
            String Poster = record.get("poster");
            Movie m = new Movie(ID, Title, Year, Genre, Director, Country, Poster, min);
            movieList.add(m);
        }
        return movieList;

    }

}
