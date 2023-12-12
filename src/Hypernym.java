// Name: Aviad Ravid
// ID: 209321108

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

public class Hypernym {
    private String name;
    private Map<String, Hyponym> hyponymMap;
    private List<Hyponym> hyponymList;
    private boolean isSorted;

    /**
     * This method used as the constructor to instance of Hypernym.
     *
     * @param name - a given string represent the name of the Hypernym.
     */
    public Hypernym(String name) {
        this.name = name;
        this.hyponymMap = new TreeMap<>();
        this.hyponymList = new ArrayList<>();
        this.isSorted = false;
    }

    /**
     * This method gets a hyponym and adds that hyponym to the Map of hyponyms of this hypernym.
     *
     * @param hyp - a given hyponym.
     */
    public void addHyponym(Hyponym hyp) {
        if (this.hyponymMap.containsKey(hyp.getName().toLowerCase())) {
            hyponymMap.get(hyp.getName().toLowerCase()).addShow();
        } else {
            this.hyponymMap.put(hyp.getName().toLowerCase(), hyp);
        }
    }

    /**
     * This method returns the list of hyponyms od that istance of hypernym.
     *
     * @return - a list of hyponyms.
     */
    public List<Hyponym> getHyponymList() {
        if (!isSorted) {
            this.hyponymList = new ArrayList<>();
            this.hyponymList.addAll(this.hyponymMap.values());
        }
        return this.hyponymList;
    }

    /**
     * This method returns the Map of hyponyms of that instace of hypernym.
     *
     * @return - a Map of string and hyponym.
     */
    public Map<String, Hyponym> getHyponymMap() {
        return this.hyponymMap;
    }

    /**
     * This method returns the name of the Hypernym.
     *
     * @return - a String.
     */
    public String getName() {
        return name;
    }

    /**
     * This method sets the list of hyponyms with the values of the map of hyponyms.
     */
    public void setHyponymList() {
        this.hyponymList.addAll(this.hyponymMap.values());
    }

    /**
     * This method gets 2 ints represent 2 indexes and replace between the hyponym in those indexes in the
     * hyponym list.
     *
     * @param i1 - a given int.
     * @param i2 - a given int.
     */
    private void swap(int i1, int i2) {
        Hyponym temp1 = this.hyponymList.get(i1);
        Hyponym temp2 = new Hyponym(temp1.getName());
        temp2.setShows(temp1.getShows());
        Hyponym temp3 = this.hyponymList.get(i2);
        temp1.setName(temp3.getName());
        temp1.setShows(temp3.getShows());
        temp3.setName(temp2.getName());
        temp3.setShows(temp2.getShows());
    }

    /**
     * This method sorts the hyponym list by their amount of shows.
     */
    public void sort() {
        this.bubbleSort();
    }

    /**
     * This method implements a bubble sort on the list of hypernyms and uses swap method to make to sort.
     */
    public void bubbleSort() {
        this.setHyponymList();
        isSorted = true;
        int size = this.hyponymList.size();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size - 1 - i; j++) {
                if (this.hyponymList.get(j).getShows() < this.hyponymList.get(j + 1).getShows()) {
                    swap(j, j + 1);
                }
            }
        }
    }

    /**
     * This method gets a string and sets the name of the hypernym to the given one.
     *
     * @param name - a given string.
     */
    public void setName(String name) {
        this.name = name;
    }
}
