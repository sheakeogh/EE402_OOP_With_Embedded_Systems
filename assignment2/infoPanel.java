package assignment2;

import java.awt.Checkbox;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class infoPanel extends Panel implements ActionListener, ItemListener{
    public static boolean safetyMarginCheck = true;
    public static int safetyMargin = 10;
    public static boolean historyCheck = true;
    public static boolean coOrdsCheck = false;
    public static boolean randomLocCheck = false;

    public static DroneIcon selectedDrone = null;

    public static Label connectionLabel = new Label("0");
    public static Checkbox safetyCheckBox = new Checkbox("Safety Margin",safetyMarginCheck);
    public static TextField safetyTextField = new TextField(String.valueOf(safetyMargin));
    public static Checkbox historyCheckbox = new Checkbox("Last 3 Positions", historyCheck);
    public static Checkbox coOrdsCheckbox = new Checkbox("Cartesian Co-Ords", coOrdsCheck);
    public static Checkbox randomLocCheckBox = new Checkbox("Spawn Randomly", randomLocCheck);

    public static Label nameLabel = new Label("None");
    public static Label coOrdLabel = new Label("None,None,None");
    public static Label dirLabel = new Label("None");
    public static Label statusLabel = new Label("None");

    public infoPanel() {
        this.setBackground(Color.BLUE);
        this.setLayout(new GridLayout(0,1,5,5));

        Panel headerPanel = new Panel();
        headerPanel.setLayout(new GridLayout(1,1));
        Label l1 = new Label("Server Settings", Label.CENTER);
        l1.setFont(new Font("Arial", Font.BOLD, 14));
        headerPanel.add(l1);
        this.add(headerPanel);

        Panel connnectionsPanel = new Panel();
        connnectionsPanel.setLayout(new GridLayout(1,2));
        connnectionsPanel.add(new Label("Connections:"));
        connnectionsPanel.add(connectionLabel);
        this.add(connnectionsPanel);

        Panel safetyMarginPanel = new Panel();
        safetyMarginPanel.setLayout(new GridLayout(1,2));
        safetyMarginPanel.add(safetyCheckBox);
        safetyMarginPanel.add(safetyTextField);
        this.add(safetyMarginPanel);

        Panel historyPanel = new Panel();
        historyPanel.setLayout(new GridLayout(1,2));
        historyPanel.add(historyCheckbox);
        this.add(historyPanel);

        Panel randomLocPanel = new Panel();
        randomLocPanel.setLayout(new GridLayout(1,2));
        randomLocPanel.add(randomLocCheckBox);
        this.add(randomLocPanel);

        Panel coOrdsPanel = new Panel();
        coOrdsPanel.setLayout(new GridLayout(1,2));
        coOrdsPanel.add(coOrdsCheckbox);
        this.add(coOrdsPanel);

        Panel selectedDronePanel = new Panel();
        selectedDronePanel.setLayout(new GridLayout(1,1));
        Label label = new Label("Selected Drone", Label.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 14));
        selectedDronePanel.add(label);
        this.add(selectedDronePanel);

        Panel namePanel = new Panel();
        namePanel.setLayout(new GridLayout(1,2));
        namePanel.add(new Label("name:"));
        namePanel.add(nameLabel);
        this.add(namePanel);

        Panel dispCoOrdsPanel = new Panel();
        dispCoOrdsPanel.setLayout(new GridLayout(1,2));
        dispCoOrdsPanel.add(new Label("Co-Ord:"));
        dispCoOrdsPanel.add(coOrdLabel);
        this.add(dispCoOrdsPanel);

        Panel anglePanel = new Panel();
        anglePanel.setLayout(new GridLayout(1,2));
        anglePanel.add(new Label("Dir:"));
        anglePanel.add(dirLabel);
        this.add(anglePanel);

        Panel statusPanel = new Panel();
        statusPanel.setLayout(new GridLayout(1,2));
        statusPanel.add(new Label("Status:"));
        statusPanel.add(statusLabel);
        this.add(statusPanel);

        this.add(new Label());
        this.add(new Label());
        this.add(new Label());
        this.add(new Label());

        safetyTextField.addActionListener(this);
        safetyCheckBox.addItemListener(this);
        historyCheckbox.addItemListener(this);
        coOrdsCheckbox.addItemListener(this);
        randomLocCheckBox.addItemListener(this);

        this.startThread();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(safetyTextField)) {
            int temp = Integer.valueOf(safetyTextField.getText());
            if(temp > -1 && temp < 101) {
                safetyTextField.setText(String.valueOf(temp));
                safetyMargin = temp;
                DroneCanvas.margin = safetyMargin;
            }
            else {
                safetyTextField.setText(String.valueOf(safetyMargin));
            }
        }
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        if(e.getSource().equals(safetyCheckBox)) {
            safetyMarginCheck = safetyCheckBox.getState();
            DroneCanvas.marginEnabled = safetyMarginCheck;
            System.out.println("Safety Box Triggered");
        }
        if(e.getSource().equals(historyCheckbox)) {
            historyCheck = historyCheckbox.getState();
            DroneCanvas.historyEnabled = historyCheck;
            System.out.println("Last 3 Pos Triggered");
        }
        if(e.getSource().equals(coOrdsCheckbox)) {
            coOrdsCheck = coOrdsCheckbox.getState();
            System.out.println("Java CoOrds Triggered");
        }

        if(e.getSource().equals(randomLocCheckBox)) {
            randomLocCheck = randomLocCheckBox.getState();
            System.out.println("Random Checkbox Triggered");
        }

    }

    public void startThread() {
        new Thread(new Runnable(){
            public void run(){
                while(true) {
                    if(selectedDrone != null && DroneCanvas.droneList.contains(selectedDrone)) {
                        nameLabel.setText(selectedDrone.name);
                        coOrdLabel.setText(selectedDrone.getPositionString());
                        dirLabel.setText(selectedDrone.getDirection());
                        statusLabel.setText(selectedDrone.getStatus());
                    }
                    else {
                        nameLabel.setText("None");
                        coOrdLabel.setText("None");
                        //colorLabel.setText("None");
                        dirLabel.setText("None");
                        statusLabel.setText("None");
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
}