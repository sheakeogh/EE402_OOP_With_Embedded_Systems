import java.awt.*;
import java.awt.event.*;
import java.util.regex.*;

@SuppressWarnings("serial")
public class Q4A extends Frame implements ActionListener, WindowListener {
	private Button calculate;
	private int[] countArr = new int[27];
	private TextField inputBox, a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t, u, v, w, x, y, z, others;
	private TextField[] TFArr = {a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t, u, v, w, x, y, z, others};
	
	public Q4A() {
		super("EE402 Q4 Application January 2020 Exam");
		this.addWindowListener(this);
		
		Panel left = new Panel(new GridLayout(2, 1));
		inputBox = new TextField();
		left.add(inputBox);
		calculate = new Button("Calculate");
		left.add(calculate);
		calculate.addActionListener(this);
		this.add("East", left);
		
		Panel right = new Panel(new GridLayout(14, 4));
		int count = 0;
		for(char alphabet = 'a'; alphabet <= 'z'; alphabet++, count++) {
			String tmp = String.valueOf(alphabet);
			right.add(new Label(tmp));
			TFArr[count] = new TextField();
			right.add(TFArr[count]);
		}
		right.add(new Label("others"));
		TFArr[TFArr.length - 1] = new TextField();
		right.add(TFArr[TFArr.length - 1]);		
		this.add("West", right);
		
		this.pack();
		this.setVisible(true);
	}
	
    private static int countChars(String str, char character) {
        Pattern pattern = Pattern.compile("[^" + character + "]*" + character + "");
        Matcher matcher = pattern.matcher(str);
        int count = 0;
        while (matcher.find()) {
            count++;
        }
        return count;
    }
	
	public void updateArr(String str) {
		int count = 0;
		int total = 0;
		for(char alphabet = 'a'; alphabet <= 'z'; alphabet++, count++) {
			int tmp = countChars(str, alphabet);
			countArr[count] = tmp;
			total += tmp;
		}
		countArr[26] = str.length() - total;
	}
	
	public static void main(String[] args) {
		new Q4A();
	}
	
    public void actionPerformed(ActionEvent e) {
    	if(e.getSource().equals(calculate)) {
    		updateArr(inputBox.getText());
    		for(int i = 0; i < countArr.length; i++) {
    			TFArr[i].setText(String.valueOf(countArr[i]));
    		}
    	}
    			
    }
    
    public void windowActivated(WindowEvent arg0) {}
    public void windowClosed(WindowEvent arg0) {}
    public void windowClosing(WindowEvent arg0) { System.exit(0); }
    public void windowDeactivated(WindowEvent arg0) {}
    public void windowDeiconified(WindowEvent arg0) {}
    public void windowIconified(WindowEvent arg0) {}
    public void windowOpened(WindowEvent arg0) {}
}
