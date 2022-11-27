public class Question2 {
    static String firstTwo(String str) {
        String s = "";
        if(str.length() <= 2) {
            return str;
        }
        else {
            s = ("" + str.charAt(0) + str.charAt(1));
            return s;
        }
    }
    public static void main(String[] args) {
        System.out.println(firstTwo("Hello"));
        System.out.println(firstTwo("abcdefg"));
        System.out.println(firstTwo("ab"));
    }
}