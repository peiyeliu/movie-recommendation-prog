public class DirectorFilter implements Filter {

    private String name;

    public DirectorFilter(String director) {
        name = director;
    }

    @Override
    public boolean satisfies(String id) {
        return MovieDatabase.getDirector(id).contains(name);
    }
}
