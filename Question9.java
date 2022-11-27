public class Question9 {
    public static void main(String[] args) {
        double[] anArray = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        Statistics s = new Statistics(anArray);
        s.display();
        System.out.println("Average = " + s.average());
        System.out.println("Max = " + s.max());
        System.out.println("Min = " + s.min());
    }
}
