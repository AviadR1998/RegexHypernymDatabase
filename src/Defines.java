// Name: Aviad Ravid
// ID: 209321108
public class Defines {
    /**
     * A whole file define like.
     *
     * @return - the string represent np regex.
     */
    public static String np() {
        return "<np>([^<>]+)</np>";
    }

    /**
     * A whole file define like.
     *
     * @return - the string represent the start of an np regex.
     */
    public static String startNp() {
        return "<np>";
    }

    /**
     * A whole file define like.
     *
     * @return - the string represent the end of an np regex.
     */
    public static String endNp() {
        return "</np>";
    }
}
