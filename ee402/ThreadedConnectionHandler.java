/* The Connection Handler Class - Written by Derek Molloy for the EE402 Module
 * See: ee402.eeng.dcu.ie
 */

package ee402;

import java.net.*;
import java.io.*;

public class ThreadedConnectionHandler extends Thread
{
    private Socket clientSocket = null;				// Client socket object
    private ObjectInputStream is = null;			// Input stream
    private ObjectOutputStream os = null;			// Output stream
	private PollutionReadings pr;
    
	// The constructor for the connection handler
    public ThreadedConnectionHandler(Socket clientSocket) {
        this.clientSocket = clientSocket;
        //Set up a service object to get the current date and time
        //fish = new Fish();
    }

    // Will eventually be the thread execution method - can't pass the exception back
    public void run() {
         try {
            this.is = new ObjectInputStream(clientSocket.getInputStream());
            this.os = new ObjectOutputStream(clientSocket.getOutputStream());
            while (this.readCommand()) {}
         } 
         catch (IOException e) 
         {
        	System.out.println("XX. There was a problem with the Input/Output Communication:");
            e.printStackTrace();
         }
    }

    // Receive and process incoming string commands from client socket 
    private boolean readCommand() {
        //String s = null;
    	Object o = null;
        try {
            //s = (String) is.readObject();
        	o = (Object) is.readObject();
        } 
        catch (Exception e){    // catch a general exception
        	this.closeSocket();
            return false;
        }
        //System.out.println("01. <- Received a String object from the client (" + s + ").");
        System.out.println("01. <- Received an object from the client.");
        
        // At this point there is a valid String object
        // invoke the appropriate function based on the command 
        if(o.getClass().getName().equals("ee402.PollutionReadings")) {
        	pr = (PollutionReadings) o;
        	pr.triggerAlarm((float)500.0);
        	this.getAverage();
        }
        else { 
            //this.sendError("Invalid command: " + s); 
        	this.sendError("Invalid command");
        }
        return true;
    }
    
    private void getAverage() {
    	float avg = pr.findAverage();
    	String averageText = "The average pollution level is: " + String.valueOf(avg) + "ug/m3\n";
    	this.send(averageText);
    }

    // Send a generic object back to the client 
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
    
    // Send a pre-formatted error message to the client 
    public void sendError(String message) { 
        this.send("Error:" + message);	//remember a String IS-A Object!
    }
    
    // Close the client socket 
    public void closeSocket() { //gracefully close the socket connection
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