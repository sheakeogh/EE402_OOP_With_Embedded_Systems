import java.util.*;
import java.awt.*;
import java.awt.event.*;

public class Question6 extends Frame implements ActionListener, WindowListener {
    private int page = 0;
    private Button left, right;
    private static Programme programme;
    private static Vector<Student> students = new Vector<Student>(5);
    private TextField name, id, phone, gender, address; 

    public Question6() {
        super("Students Records Browser");
        this.addWindowListener(this);

        Panel panelTop = new Panel(new GridLayout(4, 2));
        panelTop.add(new Label("Program Title:"));
        panelTop.add(new TextField(programme.getTitle()));
        panelTop.add(new Label("Year:"));
        panelTop.add(new TextField(Integer.toString(programme.getYear())));
        panelTop.add(new Label("Calendar Year:"));
        panelTop.add(new TextField(Integer.toString(programme.getCalYear())));
        panelTop.add(new Label("Students:"));
        panelTop.add(new TextField(Integer.toString(programme.getNumStudents())));        
        this.add("North", panelTop);

        Panel panelCtr = new Panel(new GridLayout(5, 2));
        panelCtr.add(new Label("Name:"));
        name = new TextField();
        panelCtr.add(name);
        panelCtr.add(new Label("ID Number:"));
        id = new TextField();
        panelCtr.add(id);
        panelCtr.add(new Label("Phone Number:"));
        phone = new TextField();
        panelCtr.add(phone);
        panelCtr.add(new Label("Gender:"));
        gender = new TextField();
        panelCtr.add(gender);
        panelCtr.add(new Label("Address:"));
        address = new TextField();
        panelCtr.add(address);
        this.add("Center", panelCtr);

        if(programme.getNumStudents() > 0) {
            displayStudents();
        }

        Panel panelBtm = new Panel(new GridLayout(1, 2));
        left = new Button("<");
        right = new Button(">");
        panelBtm.add(left);
        panelBtm.add(right);
        this.left.addActionListener(this);
        this.right.addActionListener(this);
        this.add("South", panelBtm);

        this.pack();
        this.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals(">")) {
            if(page < programme.getNumStudents() - 1) {
                page++;
                displayStudents();
            }
        }
        else {
            if(page > 0) {
                page--;
                displayStudents();
            }
        }
    }

    public void displayStudents() {
        Student s = programme.getStudent(page);
        name.setText(s.getName());
        id.setText(Integer.toString(s.getIdnum()));
        phone.setText(Integer.toString(s.getPhone()));
        gender.setText(s.getGender());
        address.setText(s.getAddress());
    }

    public static void main(String[] args) {
        students.add(new Student("Steve", 2386, 61313895, true, "10A Steamboat Quay"));
        students.add(new Student("Denis", 9008, 5191968, true, "Love La Tramore"));
        students.add(new Student("Damien", 3842, 5191374, true, "17 Richfield Park"));
        students.add(new Student("Claire", 6287, 2144654, false, "53 Blackstaff Way"));
        students.add(new Student("Hugh", 2998, 28713705, true, "31 Northland Road."));
        programme = new Programme("ECE", 4, 2021, students);
        new Question6();
    }

    public void windowActivated(WindowEvent arg0) {}
    public void windowClosed(WindowEvent arg0) {}
    public void windowClosing(WindowEvent arg0) {
        System.exit(0);
    }
    public void windowDeactivated(WindowEvent arg0) {}
    public void windowDeiconified(WindowEvent arg0) {}
    public void windowIconified(WindowEvent arg0) {}
    public void windowOpened(WindowEvent arg0) {}
}
