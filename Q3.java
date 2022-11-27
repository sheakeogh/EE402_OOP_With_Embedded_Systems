import java.util.*;
import java.util.List;
import java.util.regex.*;
import java.awt.*;
import java.awt.event.*;
import java.util.concurrent.*;

@SuppressWarnings("serial")
public class Q3 extends Frame implements ActionListener, WindowListener {
	static private TextField inputBox, wordCount, longestWord, shortestWord;
	private Button wordTools, clear;
	private String[] words;
	
	public Q3() {
		super("Question 3 Text Applicaation Sem 1 2020/21");
		this.addWindowListener(this);
		
		Panel top = new Panel(new GridLayout(1, 1));
		top.add(new Label("Question 3 Word Tools: "));
		this.add("North", top);

		Panel ctr = new Panel(new GridLayout(1, 1));
		inputBox = new TextField();
		ctr.add(inputBox);
		this.add("Center", ctr);
		
		Panel btm = new Panel(new GridLayout(1, 8));
		wordTools = new Button("Word Tools");
		btm.add(wordTools);
		wordTools.addActionListener(this);
		btm.add(new Label("Word Count: "));
		wordCount = new TextField();
		btm.add(wordCount);
		btm.add(new Label("Longest Word: "));
		longestWord = new TextField();
		btm.add(longestWord);
		btm.add(new Label("Shortest Word: "));
		shortestWord = new TextField();
		btm.add(shortestWord);
		clear = new Button("Clear");
		btm.add(clear);
		clear.addActionListener(this);
		this.add("South", btm);
		
		this.pack();
		this.setVisible(true);
	}
	
	public static void main(String[] args) {
		new Q3();
	}
	
	public List<Object> findWordData(String[] str) {
		List<Object> list = new ArrayList<Object>();
		
		list.add(str.length);		
		Arrays.sort(str, Comparator.comparingInt(String::length));
		list.add(str[0]);
		list.add(str[str.length - 1]);

		return list;
	}
	
    public void actionPerformed(ActionEvent e) {
    	if(e.getSource().equals(wordTools)) {
    		Pattern p = Pattern.compile("\\s+");
    		words = p.split(inputBox.getText());
    		List<Object> list = findWordData(words);
    		wordCount.setText(String.valueOf(list.get(0)));
    		longestWord.setText((String)list.get(1));
    		shortestWord.setText((String)list.get(2));
    		
    		Runnable updateText = new Runnable() {
    		    public void run() {
    		        inputBox.setText(inputBox.getText().substring(0, inputBox.getText().length() - 1));
    		    }
    		};

    		ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
    		executor.scheduleAtFixedRate(updateText, 10, 10, TimeUnit.SECONDS);
    	}
    	
    	else if(e.getSource().equals(clear)) {
    		inputBox.setText("");
    		wordCount.setText("");
    		longestWord.setText("");
    		shortestWord.setText("");
    		
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
