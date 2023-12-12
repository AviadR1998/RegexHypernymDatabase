// Name: Aviad Ravid
// ID: 209321108

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NpRegex {
    private List<Hypernym> hypernymList;
    private Pattern[] patt;
    private Map<String, Hypernym> hypernymMap;
    private int[] sides;

    /**
     * This method used as the constructor to instance of NpRegex.
     * every instance holds an arrau of Patterns and an array of ints contain 1's and 2's. 1, means this pattern include
     * multiply number of hypernyms(in potentila) in every relation.
     * 2, means only one hypernym in a relation.
     *
     * @param patt  - a given Pattern.
     * @param sides - an array of ints.
     */
    public NpRegex(Pattern[] patt, int[] sides) {
        this.hypernymMap = new TreeMap<>();
        this.patt = patt;
        this.hypernymList = new ArrayList<>();
        this.sides = sides;
    }

    /**
     * This method returns list of Hypernyms which found a given files.
     *
     * @return - a list of hypernyms.
     */
    public List<Hypernym> getHypernymList() {
        if (this.hypernymMap == null) {
            return null;
        } else {
            this.hypernymList.addAll(this.hypernymMap.values());
            return this.hypernymList;
        }
    }

    /**
     * This method make a list of hypernyms and their hyponyms that their relation matches the given patterns
     * and they are found in the given files that locates in the given path..
     *
     * @param path   - a given String with the path to the corpus files.
     * @param addOns - an array conatain the addOn ones should add to a match string to get the first word.
     */
    public void operate(String path, int[] addOns) {
        File f = new File(path);
        if (f.isDirectory()) {
            File[] files = f.listFiles();
            BufferedReader is = null;
            if (files != null) {
                for (File currFile : files) {
                    try {
                        is = new BufferedReader(
                                new InputStreamReader(
                                        new FileInputStream(currFile.getAbsolutePath())));
                        String line;
                        int cnt;
                        while ((line = is.readLine()) != null) {
                            line = line.toLowerCase();
                            cnt = 0;
                            while (cnt < this.patt.length) {
                                Matcher matcher = this.patt[cnt].matcher(line);
                                while (matcher.find()) {
                                    int matcherStart = matcher.start();
                                    int matcherEnd = matcher.end();
                                    String currMatch = line.substring(matcherStart + addOns[cnt], matcherEnd);
                                    Pattern endNp = Pattern.compile("</np>");
                                    Pattern startNp = Pattern.compile("<np>");
                                    Matcher endNpMatch = endNp.matcher(currMatch);
                                    endNpMatch.find();
                                    currMatch = currMatch.substring(0, endNpMatch.start());
                                    Hypernym currHyper;
                                    if (this.sides[cnt] == 1) {
                                        if (this.hypernymMap.containsKey(currMatch)) {
                                            currHyper = this.hypernymMap.get(currMatch);
                                        } else {
                                            currHyper = new Hypernym(currMatch);
                                            this.hypernymMap.put(currMatch, currHyper);
                                        }
                                        currMatch = line.substring(matcherStart + addOns[cnt], matcherEnd);
                                        Matcher startNpMatch = startNp.matcher(currMatch);
                                        while (startNpMatch.find() && endNpMatch.find()) {
                                            currHyper.addHyponym(
                                                    new Hyponym(
                                                            currMatch.substring(startNpMatch.start() + 4,
                                                                    endNpMatch.end() - 5)));
                                        }
                                    } else {
                                        Hyponym currHypo = new Hyponym(currMatch);
                                        currMatch = line.substring(matcherStart + addOns[cnt], matcherEnd);
                                        Matcher startNpMatch = startNp.matcher(currMatch);
                                        if (startNpMatch.find() && endNpMatch.find()) {
                                            currMatch =
                                                    currMatch.substring(startNpMatch.start() + 4, endNpMatch.end() - 5);
                                            if (this.hypernymMap.containsKey(currMatch)) {
                                                currHyper = this.hypernymMap.get(currMatch);
                                            } else {
                                                currHyper = new Hypernym(currMatch);
                                                this.hypernymMap.put(currMatch, currHyper);
                                            }
                                            currHyper.addHyponym(currHypo);
                                        }
                                    }
                                }
                                cnt++;
                            }
                        }
                    } catch (IOException e) {
                        System.out.println("Something went wrong while reading !");
                        this.hypernymMap = null;
                    } finally {
                        if (is != null) { // Exception might have happened at constructor
                            try {
                                is.close(); // closes FileInputStream too
                            } catch (IOException e) {
                                System.out.println(" Failed closing the file !");
                                this.hypernymMap = null;
                            }
                        }
                    }
                }
            } else {
                System.out.println("Something went wrong while reading !");
                this.hypernymMap = null;
            }
        } else {
            System.out.println("Something went wrong while reading !");
            this.hypernymMap = null;
        }
    }
}
