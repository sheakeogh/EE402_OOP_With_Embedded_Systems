/* The Date Time Service Class - Written by Derek Molloy for the EE402 Module
 * See: ee402.eeng.dcu.ie
 */

package ee402;

import java.util.*;

public class randomNumberGenerator {
   private static Vector<Integer> vec = new Vector<Integer>();
   private static double sum, avg, med;

   private static void myVectorData(Vector<Integer> vec) {
   	Collections.sort(vec);
   	for(int i = 0; i < vec.size(); i++) {
   		sum += vec.get(i);
   	}
   	avg = sum/vec.size();
       if (vec.size() % 2 == 0) {
           int sumOfMiddleElements = vec.get(vec.size() / 2) + vec.get(vec.size() / 2 - 1);
           med = ((double) sumOfMiddleElements) / 2;
        } else {
           med = (double) vec.get(vec.size() / 2);
     }
   }
   
   public String getVector(int size, int min, int max) {
   	for(int i = 0; i < size; i++) {
   		vec.add((int)(Math.random() * (max - min)) + min);
   	}
   	
   	myVectorData(vec);
   	
   	String vecInfo = "The returned values are ...\n" + "Array " + vec.toString() + "\n" + "The sum is: " + sum + "\nThe average is: " + avg + "\nThe median is: " + med;

   	return vecInfo;
   }
}