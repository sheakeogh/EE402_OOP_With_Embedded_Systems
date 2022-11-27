import java.util.*;

public class Question5 {

    public static void main(String[] args) {
        Vector<Student> sVector = new Vector<Student>(5);
        Programme programme = new Programme("ECE", 4, 2021, sVector);        
        sVector.add(new Student("Steve", 2386, 61313895, true, "10A Steamboat Quay"));
        sVector.add(new Student("Denis", 9008, 5191968, true, "Love La Tramore"));
        sVector.add(new Student("Damien", 3842, 5191374, true, "17 Richfield Park"));
        sVector.add(new Student("Claire", 6287, 2144654, false, "53 Blackstaff Way"));
        sVector.add(new Student("Hugh", 2998, 28713705, true, "31 Northland Road."));

        programme.display();
        programme.displayStudents();
    }
}
