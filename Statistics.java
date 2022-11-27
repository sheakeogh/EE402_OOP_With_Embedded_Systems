import java.util.*;

public class Statistics {
    private double[] arr;
    private int arrLength;

    public Statistics(double[] a) {
        this.arr = a;
        this.arrLength = a.length;
    }

    void display() {
        System.out.print("Array [");
        for(int i =0; i < arrLength; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println("]");
    }

    double average() {
        int sum = 0;
        for(int i = 0; i < arrLength; i++) {
            sum += arr[i];
        }
        return (sum/arrLength);
    }

    double max() {
        double[] tmp = sortArr();
        reverse(tmp);

        return (tmp[0]);
    }

    double min() {
        double[] tmp = sortArr();
        
        return (tmp[0]);
    }

    void reverse(double[] a) { 
        int last = a.length - 1; 
        int middle = a.length / 2; 
        for (int i = 0; i <= middle; i++) { 
            double temp = a[i]; 
            a[i] = a[last - i]; 
            a[last - i] = temp; 
        } 
    }

    double[] sortArr() {
        double[] tmp = arr;
        Arrays.sort(tmp);
        
        return tmp;
    }

    double median() {
        double[] tmp = sortArr();
        if(tmp.length % 2 == 0)  {
            return (((tmp[(tmp.length/2) - 1])+(tmp[(tmp.length/2)]))/(2));
        }           
        else {
            return (tmp[((tmp.length)/(2))]);
        }
    }
};