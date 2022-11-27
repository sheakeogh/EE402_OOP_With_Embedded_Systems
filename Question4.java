public class Question4 {
    static String nTwice(String str, int num) {
        String top = str.substring(0, num);
        String btm = str.substring((str.length() - num), str.length());
        String s = ("" + top + btm);
        
        return s;
    }
    public static void main(String[] args) {
        System.out.println(nTwice("Hello", 2));
        System.out.println(nTwice("Chocolate", 3));
        System.out.println(nTwice("Chocolate", 1));
    }
}
