package assignment2;

import java.awt.Color;
import java.io.Serializable;

public class Message implements Serializable {
    public int idNum = 0;
    public String name = "	";
    public double[] Position = new double[] {0.0,0.0,0.0};
    public int rotation = 0;
    public Long Time = new DateTimeService().calendar.getTimeInMillis();
    public Color Colour = Color.BLACK;
    public boolean Disconnect = false;

    public Message() {

    }

    public Message(int id) {
        this.idNum = id;
    }

    public Message(int id, String name, Color colour) {
        this.idNum = id;
        this.name = name;
        this.Colour = colour;
    }

    public String toString() {
        String s =	"-----------------\n" +
                "idNum: " + String.valueOf(idNum) + "\n" +
                "name: " + name + "\n" +
                "Pos: " + String.valueOf(Position[0]) + "," + String.valueOf(Position[1]) + "," + String.valueOf(Position[2]) + "\n" +
                "Rot: " + String.valueOf(rotation) + "\n" +
                "Date: " + String.valueOf(Time) + "\n" +
                "Color: " + Colour.toString() + "\n" +
                "-----------------";
        return s;
    }
}