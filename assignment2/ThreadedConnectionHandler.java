/* The Connection Handler Class - Written by Derek Molloy for the EE402 Module
 * See: ee402.eeng.dcu.ie
 */

package assignment2;

import java.net.*;
import java.awt.Point;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ThreadedConnectionHandler extends Thread{
    static List<Integer> idList = new ArrayList<Integer>();

    private Socket clientSocket = null;				// Client socket object
    private ObjectInputStream is = null;			// Input stream
    private ObjectOutputStream os = null;			// Output stream
    private DateTimeService theDateService;

    // The constructor for the connection handler
    public ThreadedConnectionHandler(Socket clientSocket) {
        this.clientSocket = clientSocket;
        //Set up a service object to get the current date and time
        theDateService = new DateTimeService();
    }


    // Runs the thread
    public void run() {
        try {
            this.is = new ObjectInputStream(clientSocket.getInputStream());
            this.os = new ObjectOutputStream(clientSocket.getOutputStream());
            sendID();
            while (this.readCommand()) {}
        }
        catch (IOException e)
        {
            System.out.println("XX. There was a problem with the Input/Output Communication:");
            e.printStackTrace();
        }
    }

    // Method for reading the command sent from the client
    private boolean readCommand() {
        Object o = null;
        Message m = null;
        try {
            o = is.readObject();

            m = (Message) o;

            if(m.Disconnect == true) {
                DroneCanvas.droneList.remove(m.idNum);
                idList.remove(m.idNum);
                infoPanel.connectionLabel.setText(String.valueOf(DroneCanvas.droneList.size()));
            }

            System.out.println("01. <- Received a Message object from the client");
            System.out.println(m);

            DroneCanvas.droneList.get(m.idNum).color = m.Colour;
            DroneCanvas.droneList.get(m.idNum).name = m.name;
            DroneCanvas.droneList.get(m.idNum).translate(m.Position[0], m.Position[1], m.Position[2]);
            DroneCanvas.droneList.get(m.idNum).rotateIcon(m.rotation);

            os.flush();
            return true;

        }
        catch (Exception e){    // catch a general exception
            this.closeSocket();
            return false;
        }


    }

    // Method for sending a generic object to the client
    private void send(Object o) {
        try {
            System.out.println("02. -> Sending (" + o +") to the client.");
            this.os.writeObject(o);
            this.os.flush();
        }
        catch (Exception e) {
            System.out.println("XX." + e.getStackTrace());
        }
    }

    //Method For sending a new unique idNum to the client
    public void sendID() {
        int id = getNewID();

        this.send(new Message(id));
        System.out.println("The idNum IS:" + String.valueOf(id));
    }

    //Method for generating new idNum and adding Drone to droneList
    public static int getNewID() {
        int i=0;
        Point position = DroneIcon.generatePosition(infoPanel.randomLocCheck);

        if(DroneCanvas.droneList.isEmpty()) {
            DroneCanvas.droneList.add(new DroneIcon(position, "Test"));
            infoPanel.connectionLabel.setText(String.valueOf(DroneCanvas.droneList.size()));
            idList.add(i);
            return i;
        }

        while(idList.contains(i)) {
            i++;
        }

        idList.add(i);
        DroneCanvas.droneList.add(new DroneIcon(position, "Test"));
        infoPanel.connectionLabel.setText(String.valueOf(DroneCanvas.droneList.size()));
        return i;
    }

    // Method for Closing the Socket
    public void closeSocket() {
        try {
            this.os.close();
            this.is.close();
            this.clientSocket.close();
        }
        catch (Exception e) {
            System.out.println("XX. " + e.getStackTrace());
        }
    }
}