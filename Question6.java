import java.util.Arrays;

public class Question6 {
    static int[] lotteryNumbers() {
        int[] arr = new int[7];
        for(int i = 0; i < 7; i++) {
            if(i < 5) {
                arr[i] = (int)((Math.random()) * (50));
            }
            else {
                arr[i] = (int)((Math.random()) * (11));
            }
        }

        return arr;
    }
    public static void main(String[] args) {
        System.out.println(Arrays.toString(lotteryNumbers()));
    }
    
}
