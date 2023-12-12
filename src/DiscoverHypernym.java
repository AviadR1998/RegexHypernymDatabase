// Name: Aviad Ravid
// ID: 209321108

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

public class DiscoverHypernym {
    private final String lemma;

    /**
     * This method used as the constructor to instance of DiscoverHypernym.
     *
     * @param lemma - a given string.
     */
    public DiscoverHypernym(String lemma) {
        this.lemma = lemma;
    }

    /**
     * The main method of this class.
     * This method gets 2 arguments: a path to the corpus directory and a string represent a given lemma to look for.
     * the method prints all the hypernyms in the corpus that in a relation with the given lemma.
     * If there are no hypernyms or there are any problem with the arguments a nice message will show.
     *
     * @param args - a given array of strings contains 2 arguments.
     */
    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("There is some missing data!");
        } else {
            String corpusPath = args[0];
            String lemma = args[1].toLowerCase();
            DiscoverHypernym dh = new DiscoverHypernym(lemma);
            String regexStr1 = Defines.np() + "( ,)?" + " such as " + Defines.np() + "(( , |, )" + Defines.np() + ")*"
                    + "(( ,)?( and | or )" + Defines.np() + ")?";
            String regexStr2 = Defines.np() + "( ,)?" + " including " + Defines.np() + "(( , |, )" + Defines.np() + ")*"
                    + "(( ,)?( and | or )" + Defines.np() + ")?";
            String regexStr3 =
                    Defines.np() + "( ,)?" + " especially " + Defines.np() + "(( , |, )" + Defines.np() + ")*"
                            + "(( ,)?( and | or )" + Defines.np() + ")?";
            String regexStr4 =
                    Defines.np() + "( ,)?" + " which is " + "((an example |a kind |a class )?of )?" + Defines.np();
            String regexStr5 =
                    "such " + Defines.np() + "( ,)?" + " as " + Defines.np() + "(( , |, )" + Defines.np() + ")*"
                            + "(( ,)?( and | or )" + Defines.np() + ")?";
            Pattern[] patterns = {Pattern.compile(regexStr1), Pattern.compile(regexStr2), Pattern.compile(regexStr3),
                    Pattern.compile(regexStr4), Pattern.compile(regexStr5)};
            int[] leftRight = {1, 1, 1, 2, 1};
            int[] addOns = {4, 4, 4, 4, 9};
            NpRegex npReg = new NpRegex(patterns, leftRight);
            npReg.operate(corpusPath, addOns);
            List<Hypernym> hypernyms = dh.createLemmasList(npReg.getHypernymList());
            if (hypernyms != null) {
                if (hypernyms.size() == 0) {
                    System.out.println("The lemma doesn't appear in the corpus");
                } else {
                    dh.sort(hypernyms);
                    dh.printHyperOfLemma(hypernyms);
                }
            }
        }
    }

    /**
     * This method gets a list of hypernyms and return an updated list with only the hypernyms
     * that have a relation with that lemmas instance.
     *
     * @param hypernymList - a given list.
     * @return a list of hypernyms.
     */
    private List<Hypernym> createLemmasList(List<Hypernym> hypernymList) {
        if (hypernymList == null) {
            return null;
        }
        List<Hypernym> finalList = new ArrayList<>();
        for (Hypernym hyper : hypernymList) {
            Map<String, Hyponym> hyponymMap = hyper.getHyponymMap();
            if (hyponymMap.containsKey(lemma)) {
                finalList.add(hyper);
            }
        }
        return finalList;
    }

    private void swap(List<Hypernym> hypernymList, int i1, int i2, int shows1, int shows2) {
        Hypernym tempHyper1 = hypernymList.get(i1);
        String hyperName = tempHyper1.getName();
        Hypernym tempHyper2 = hypernymList.get(i2);
        tempHyper1.setName(tempHyper2.getName());
        tempHyper1.getHyponymMap().get(lemma).setShows(shows2);
        tempHyper2.setName(hyperName);
        tempHyper2.getHyponymMap().get(lemma).setShows(shows1);
    }

    /**
     * A sort method. this method gets a list of hypernyms and sorts the list by the amount of shows of this
     * lemmas instance in every Hypernym relation.
     *
     * @param hypernymList - a given list.
     */
    private void sort(List<Hypernym> hypernymList) {
        int size = hypernymList.size();
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = -1;
        }
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size - 1 - i; j++) {
                if (arr[j] == -1) {
                    arr[j] = hypernymList.get(j).getHyponymMap().get(lemma).getShows();
                }
                if (arr[j + 1] == -1) {
                    arr[j + 1] = hypernymList.get(j + 1).getHyponymMap().get(lemma).getShows();
                }
                if (arr[j] < arr[j + 1]) {
                    int temp = arr[j];
                    swap(hypernymList, j, j + 1, temp, arr[j + 1]);
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }

    /**
     * This method prints every hypernym in a given list and the amount of shows of the lemma
     * in the relation with the hypernym.
     *
     * @param hypernymList - a given list of hypernyms.
     */
    private void printHyperOfLemma(List<Hypernym> hypernymList) {
        for (Hypernym hyper : hypernymList) {
            Map<String, Hyponym> hyponymMap = hyper.getHyponymMap();
            if (hyponymMap.containsKey(lemma)) {
                int lemmaShows = hyponymMap.get(lemma).getShows();
                System.out.println(hyper.getName() + ": (" + lemmaShows + ")");
            }
        }
    }
}
