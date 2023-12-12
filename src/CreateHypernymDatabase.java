// Name: Aviad Ravid
// ID: 209321108
import java.util.List;
import java.util.regex.Pattern;

public class CreateHypernymDatabase {

    /**
     * The main method creates 5 types of Patterns, gets a path to a corpus and a destination path.
     * the mehod creates a big data base of Hypernyms and their hyponyms by alphabetic order.
     * the hyponym itself order by thier shows number.
     *
     * @param args - a given 2 strings represent two paths.
     */
    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("There is some missing data!");
        } else {
            String corpusPath = args[0];
            String filePath = args[1];
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
            NpRegex leftNpReg = new NpRegex(patterns, leftRight);
            leftNpReg.operate(corpusPath, addOns);
            List<Hypernym> dataList = leftNpReg.getHypernymList();
            WriteToFile write = new WriteToFile();
            write.writeHypernymListToFile(filePath, dataList);
        }
    }
}
