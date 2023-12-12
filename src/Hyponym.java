// Name: Aviad Ravid
// ID: 209321108
public class Hyponym {
    private String name;
    private int shows;

    /**
     * This method used as the constructor to instance of Hyponym.
     *
     * @param name - a given string represent the name of the Hyponym.
     */
    public Hyponym(String name) {
        this.name = name;
        this.shows = 1;
    }

    /**
     * This method returns the name of the hyponym.
     *
     * @return - a string.
     */
    public String getName() {
        return name;
    }

    /**
     * This method adds one show to the shows of that instance of hyponym.
     */
    public void addShow() {
        this.shows++;
    }

    /**
     * This method returns the amount of shows of that instancr of hyponym.
     *
     * @return - int, represent the amount of shows.
     */
    public int getShows() {
        return shows;
    }

    /**
     * This method sets the name of the hyponym.
     *
     * @param name - a given string.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * This method gets an int and sets the shows variable to the given number.
     *
     * @param shows - a given number.
     */
    public void setShows(int shows) {
        this.shows = shows;
    }
}
