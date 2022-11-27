import java.awt.*;
import java.awt.event.*;

public class Question2 extends Frame implements ActionListener, WindowListener {
    private Button upper, lower;
    private TextField tf;
    private String str;
    
    public Question2() {
        super("Question 2");
        this.setLayout(new FlowLayout());
        this.addWindowListener(this);

        this.tf = new TextField("Input Text");
        this.add(tf);
        
        this.upper = new Button("Uppercase");
        this.add(upper);
        this.lower = new Button("Lowercase");
        this.add(lower);
        this.upper.addActionListener(this);
        this.lower.addActionListener(this);

        this.pack();
        this.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        str = tf.getText();
        if (e.getActionCommand().equals("Uppercase")) {
            tf.setText(str.toUpperCase());
        }
        else {
            tf.setText(str.toLowerCase());
        }
    }

    public static void main(String[] args) {
        new Question2();
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
