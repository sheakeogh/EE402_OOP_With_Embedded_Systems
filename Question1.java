import java.awt.*;
import java.awt.event.*;

public class Question1 extends Frame implements WindowListener {
    public Question1() {
        super("Question1");
        this.addWindowListener(this);

        Label l = new Label("Hello World!");
        this.add(l);

        this.pack();
        this.setVisible(true);
    }

    public static void main(String[] args) {
        new Question1();
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
