public class Question3 {
    static boolean hasBad(String str) {
        String s1 = str.substring(0, 3);
        String s2 = str.substring(1, 4);
        if(s1.equals("bad") || s2.equals("bad")) {
            return true;
        }
        else {
            return false;
        }
    }
    public static void main(String[] args) {
        System.out.println(hasBad("badxx"));
        System.out.println(hasBad("xbadxx"));
        System.out.println(hasBad("xxbadxx"));
    }
}
