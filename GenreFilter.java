public class GenreFilter implements Filter {

    private String targetGenre;

    public GenreFilter(String genre) {
        targetGenre = genre;
    }

    @Override
    public boolean satisfies(String id) {
        return MovieDatabase.getGenres(id).indexOf(targetGenre) != -1;
    }
}
