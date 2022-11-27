import java.util.*;

public class Programme {
    private String title;
    private int year, calYear;
    private Vector<Student> students; 

    Programme(String ttl, int yr, int calYr, Vector<Student> vect) {
        this.title = ttl;
        this.year = yr;
        this.calYear = calYr;
        this.students = vect;
    }

    public void addStudent(Student s) {
        students.add(s);
    }
    
    public void display() {
        System.out.println("Programme Title: " + title);
        System.out.println("Year: " + year);
        System.out.println("Calendar Year: " + calYear);
    }

    public void displayStudents() {
        for(int i = 0; i < students.size(); i++) {
            students.get(i).display();
        }
    }

    public Student getStudent(int x) {
        return students.elementAt(x);
    }

    public String getTitle() {
        return title;
    }

    public int getYear() {
        return year;
    }

    public int getCalYear() {
        return calYear;
    }

    public int getNumStudents() {
        return students.size();
    }
};
