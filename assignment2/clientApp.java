package assignment2;

import java.awt.Button;
import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class clientApp extends Frame implements ActionListener, WindowListener{
    int botSpeed = 100;
    String botName = "bot";
    Color color = Color.BLUE;
    int idNum = 0;
    double step = 10;

    Button incSpeed = new Button("+");
    Button decSpeed = new Button("-");

    TextField speedText = new TextField(String.valueOf(botSpeed));
    TextField nameText = new TextField(botName);

    Button up = new Button("UP");
    Button down = new Button("DWN");
    Button left = new Button("LFT");
    Button right = new Button("RGT");
    Button rightRot = new Button("ROTRGT");
    Button leftRot = new Button("ROTLFT");

    Long prevMesssageTimestamp = (long) 0;
    Message lastMessage = new Message();

    private static int portNumber = 5050;
    private Socket socket = null;
    private ObjectOutputStream os = null;
    private ObjectInputStream is = null;

    public Label ConnStatusLabel = new Label("None");
    public Label NameLabel = new Label(botName);
    public Label SpeedLabel = new Label(String.valueOf(botSpeed));
    public Label LastMsgLabel = new Label("None");
    public Label IdLabel = new Label(String.valueOf(idNum));

    public clientApp(String serverIP, String Name) {
        super("Client App");

        botName = Name;
        nameText.setText(botName);

        if (!connectToServer(serverIP)) {
            System.out.println("XX. Failed to open socket connection to: " + serverIP);
            System.exit(-1);
        }

        this.addWindowListener(this);
        this.setLayout(new GridLayout(0,2,5,5));

        Panel p1 = new Panel();
        p1.setBackground(Color.GRAY);
        p1.setLayout(new GridBagLayout());

        GridBagConstraints c1 = new GridBagConstraints();
        c1.fill = GridBagConstraints.BOTH;
        c1.weightx = 1.0;
        c1.weighty = 1.0;

        c1.gridx = 0;
        c1.gridy = 0;
        p1.add(leftRot,c1);

        c1.gridx = 1;
        c1.gridy = 0;
        p1.add(up,c1);

        c1.gridx = 2;
        c1.gridy = 0;
        p1.add(rightRot,c1);

        c1.gridx = 0;
        c1.gridy = 1;
        p1.add(left,c1);

        c1.gridx = 2;
        c1.gridy = 1;
        p1.add(right,c1);

        c1.gridx = 1;
        c1.gridy = 1;
        p1.add(down,c1);

        c1.gridx = 0;
        c1.gridy = 2;
        p1.add(new Label("Speed"),c1);

        c1.gridx = 1;
        c1.gridy = 2;
        p1.add(speedText, c1);

        c1.gridx = 2;
        c1.gridy = 2;
        p1.add(incSpeed,c1);

        c1.gridx = 3;
        c1.gridy = 2;
        p1.add(decSpeed,c1);

        this.add(p1);

        Panel p2 = new Panel();
        p2.setBackground(Color.BLUE);
        this.add(p2);

        p2.setLayout(new GridLayout(10,0,5,5));

        Panel ClientInfo = new Panel();
        ClientInfo.setLayout(new GridLayout(1,1));
        ClientInfo.add(new Label("Client Info", Label.CENTER));
        ClientInfo.setFont(new Font("Arial", Font.BOLD, 14));
        p2.add(ClientInfo);

        Panel statusPanel = new Panel();
        statusPanel.setLayout(new GridLayout(1,2));
        statusPanel.add(new Label("Status:"));
        statusPanel.add(ConnStatusLabel);
        p2.add(statusPanel);

        Panel idPanel = new Panel();
        idPanel.setLayout(new GridLayout(1,2));
        idPanel.add(new Label("idNum Number:"));
        idPanel.add(IdLabel);
        p2.add(idPanel);

        Panel namePanel = new Panel();
        namePanel.setLayout(new GridLayout(1,2));
        namePanel.add(new Label("name:"));
        namePanel.add(NameLabel);
        p2.add(namePanel);

        Panel speedPanel = new Panel();
        speedPanel.setLayout(new GridLayout(1,2));
        speedPanel.add(new Label("Speed:"));
        speedPanel.add(SpeedLabel);
        p2.add(speedPanel);

        Panel lastMessageTimestampPanel = new Panel();
        lastMessageTimestampPanel.setLayout(new GridLayout(1,2));
        lastMessageTimestampPanel.add(new Label("Last Message Time:"));
        lastMessageTimestampPanel.add(LastMsgLabel);
        p2.add(lastMessageTimestampPanel);

        incSpeed.addActionListener(this);
        decSpeed.addActionListener(this);
        speedText.addActionListener(this);
        nameText.addActionListener(this);
        up.addActionListener(this);
        down.addActionListener(this);
        left.addActionListener(this);
        right.addActionListener(this);
        rightRot.addActionListener(this);
        leftRot.addActionListener(this);

        this.setVisible(true);
        this.setSize(650, 250);
        this.setResizable(false);

        waitForID();

        send(new Message(idNum, botName, color));

        startThread();
    }

    public void waitForID() {
        while(true) {
            Object o = receive();
            if (o != null) {
                return;
            }
            System.out.println("Waiting for idNum...");
        }
    }

    public void startThread() {
        new Thread(new Runnable(){
            public void run(){
                while(true) {
                    if(new DateTimeService().calendar.getTimeInMillis() - prevMesssageTimestamp > 10*1000) {
                        Message temp = new Message(idNum, botName, color);
                        send(temp);
                    }

                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    public void windowActivated(WindowEvent arg0) {}
    public void windowClosed(WindowEvent arg0) {}
    public void windowClosing(WindowEvent arg0) {
        Message mes = new Message(idNum, botName, color);
        mes.Disconnect = true;
        send(mes);
        System.exit(0);
    }
    public void windowDeactivated(WindowEvent arg0) {}
    public void windowDeiconified(WindowEvent arg0) {}
    public void windowIconified(WindowEvent arg0) {}
    public void windowOpened(WindowEvent arg0) {}

    public void incrementSpeed() {
        if(botSpeed ==100)
            return;
        botSpeed++;
    }

    public void decrementSpeed() {
        if(botSpeed ==0)
            return;
        botSpeed--;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(incSpeed)) {
            System.out.println("Increment Speed Button has been pressed");
            incrementSpeed();
            speedText.setText(String.valueOf(botSpeed));
            SpeedLabel.setText(String.valueOf(botSpeed));
        }

        if(e.getSource().equals(decSpeed)) {
            System.out.println("Decrement Speed Button has been pressed");
            decrementSpeed();
            speedText.setText(String.valueOf(botSpeed));
            SpeedLabel.setText(String.valueOf(botSpeed));
        }

        if(e.getSource().equals(speedText)) {
            System.out.println("Speed Field has been modified");
            int temp = Integer.valueOf(speedText.getText());
            if(temp >= 0 && temp <= 100) {
                botSpeed = temp;
                SpeedLabel.setText(String.valueOf(botSpeed));
            }
            else
                speedText.setText(String.valueOf(botSpeed));
        }

        if(e.getSource().equals(right)) {
            Message mes = new Message(idNum, botName, color);
            mes.Position = new double[]{calcMagnitude(),0.0,0.0};
            send(mes);
        }

        if(e.getSource().equals(left)) {
            Message mes = new Message(idNum, botName, color);
            mes.Position = new double[]{-calcMagnitude(),0.0,0.0};
            send(mes);
        }

        if(e.getSource().equals(down)) {
            Message mes = new Message(idNum, botName, color);
            mes.Position = new double[]{0.0, calcMagnitude(),0.0};
            send(mes);
        }

        if(e.getSource().equals(up)) {
            Message mes = new Message(idNum, botName, color);
            mes.Position = new double[]{0.0, -calcMagnitude(),0.0};
            send(mes);
        }

        if(e.getSource().equals(rightRot)) {
            Message mes = new Message(idNum, botName, color);
            mes.rotation = (int) calcMagnitude();
            send(mes);
        }

        if(e.getSource().equals(leftRot)) {
            Message mes = new Message(idNum, botName, color);
            mes.rotation = (int) -calcMagnitude();
            send(mes);
        }

        return;
    }

    private double calcMagnitude() {
        return step * (botSpeed /100.0);
    }

    private boolean connectToServer(String serverIP) {
        try { // open a new socket to the server
            this.socket = new Socket(serverIP,portNumber);
            this.os = new ObjectOutputStream(this.socket.getOutputStream());
            this.is = new ObjectInputStream(this.socket.getInputStream());
            System.out.println("00. -> Connected to Server:" + this.socket.getInetAddress()
                    + " on port: " + this.socket.getPort());
            System.out.println("    -> from local address: " + this.socket.getLocalAddress()
                    + " and port: " + this.socket.getLocalPort());
        } catch (Exception e) {
            System.out.println("XX. Failed to Connect to the Server at port: " + portNumber);
            System.out.println("    Exception: " + e.toString());
            return false;
        }
        return true;
    }

    private void send(Object o) {
        lastMessage = (Message) o;
        prevMesssageTimestamp = lastMessage.Time;
        LastMsgLabel.setText(new DateTimeService(prevMesssageTimestamp).getTimeString());

        try {
            System.out.println("02. -> Sending an object...");
            System.out.println(o);
            os.writeObject(o);
            os.flush();
        } catch (Exception e) {
            System.out.println("XX. Exception Occurred on Sending:" +  e.toString());
            ConnStatusLabel.setText("Closed");
            return;
        }
        ConnStatusLabel.setText("Open");
    }

    private Object receive(){
        Object o = null;
        try {
            System.out.println("03. -- About to receive an object...");
            o = is.readObject();
            System.out.println("04. <- Object received...");
        } catch (Exception e) {
            System.out.println("XX. Exception Occurred on Receiving:" + e.toString());
            ConnStatusLabel.setText("Closed");
            return null;
        }

        Message m = (Message)o ;
        idNum = Integer.valueOf(m.idNum);
        System.out.println("Receiving idNum from Server " + String.valueOf(idNum));
        ConnStatusLabel.setText("Open");
        IdLabel.setText(String.valueOf(idNum));

        return o;
    }

    //Main Function that runs the client
    public static void main(String args[]){
        System.out.println("**. Java Client Application - EE402 OOP Module, DCU");
        if(args.length == 2){
            clientApp theApp = new clientApp(args[0], args[1]);
        }
        else{
            System.out.println("Error: you must provide the address of the server and name of Robot");
            System.out.println("Usage is: java clientApp hostname botName (e.g. java Client localhost lightning)");
        }
    }
}