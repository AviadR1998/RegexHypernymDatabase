// Name: Aviad Ravid
// ID: 209321108

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.List;

public class WriteToFile {

    /**
     * This method gets a list of Hypernyms and print them to a file by a given name and saves it to a given path.
     *
     * @param path         - a given dest path.
     * @param hypernymList - a given list of Hypernyms.
     */
    public void writeHypernymListToFile(String path, List<Hypernym> hypernymList) {
        PrintWriter os = null;
        try {
            if (hypernymList != null) {
                os = new PrintWriter(// wrapper with many ways of writing strings
                        new OutputStreamWriter(// wrapper that can write strings
                                new FileOutputStream(path)));
                boolean firstOne = false;
                for (Hypernym hyper : hypernymList) {
                    if (hyper.getHyponymMap().size() >= 3) {
                        if (!firstOne) {
                            firstOne = true;
                        } else {
                            os.write("\n");
                        }
                        hyper.sort();
                        List<Hyponym> hyponymList = hyper.getHyponymList();
                        os.write(hyper.getName());
                        boolean flag = false;
                        for (Hyponym hypo : hyponymList) {
                            if (!flag) {
                                os.write(":");
                                os.write(" " + hypo.getName() + " (" + hypo.getShows() + ")");
                                flag = true;
                            } else {
                                os.write(", " + hypo.getName() + " (" + hypo.getShows() + ")");
                            }
                        }
                    }
                }
            } else {
                System.out.println("There are no matches!");
            }
        } catch (IOException e) {
            System.out.println("Something went wrong while writing!");
        } finally {
            if (os != null) { // Exception might have happened at constructor
                os.close(); // closes fileOutputStream too
            }
        }
    }
}
