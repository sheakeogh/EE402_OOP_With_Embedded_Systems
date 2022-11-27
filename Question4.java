import java.awt.*;
import java.awt.event.*;

public class Question4 extends Frame implements ActionListener, WindowListener {
    private Button draw;
    private Label l1, l2, l3, l4, l5, l6, l7;
    private Label[] l = {l1, l2, l3, l4, l5, l6, l7};
    private TextField tf1, tf2, tf3, tf4, tf5, tf6, tf7;
    private TextField[] tf = {tf1, tf2, tf3, tf4, tf5, tf6, tf7};
    
    public Question4() {
        super("Question 4");
        this.setLayout(new FlowLayout());
        this.addWindowListener(this);

        for(int i = 0; i < tf.length; i++) {
            this.l[i] = new Label(Integer.toString(i + 1));
            this.add(l[i]);
            this.tf[i] = new TextField("Number");
            this.add(tf[i]);
        }
       
        this.draw = new Button("Draw");
        this.add(draw);
        this.draw.addActionListener(this);

        this.pack();
        this.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        for(int i = 0; i < tf.length; i++) {
            if(i < 5) {
                tf[i].setText(Integer.toString((int)(((Math.random()) * 49) + 1)));
            }
            else {
                tf[i].setText(Integer.toString((int)(((Math.random()) * 10) + 1)));
            }
        }
    }

    public static void main(String[] args) {
        new Question4();
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
