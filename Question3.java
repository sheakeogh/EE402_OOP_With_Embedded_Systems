import java.awt.*;
import java.awt.event.*;

public class Question3 extends Frame implements ActionListener, WindowListener {
    private Button multiply;
    private TextField a, b, res;
    
    public Question3() {
        super("Question 3");
        this.setLayout(new FlowLayout());
        this.addWindowListener(this);

        Label l1 = new Label("A:");
        this.add(l1);
        this.a = new TextField("Input Integer");
        this.add(a);
        Label l2 = new Label("B:");
        this.add(l2);
        this.b = new TextField("Input Integer");
        this.add(b);
        Label l3 = new Label("A * B=");
        this.add(l3);
        this.res = new TextField("Result");
        this.add(res);
        
        this.multiply = new Button("Multiply");
        this.add(multiply);
        this.multiply.addActionListener(this);

        this.pack();
        this.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        int x = Integer.parseInt(a.getText());
        int y = Integer.parseInt(b.getText());
        int ans = x*y;
        res.setText(String.valueOf(ans));
    }

    public static void main(String[] args) {
        new Question3();
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
