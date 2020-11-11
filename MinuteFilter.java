public class MinuteFilter implements Filter {

    private int min;
    private int max;

    public MinuteFilter(int start, int end) {
        min = start;
        max = end;
    }

    @Override
    public boolean satisfies(String id) {
        return MovieDatabase.getMinutes(id) >= min && MovieDatabase.getMinutes(id) <= max;
    }
}
