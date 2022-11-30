package assignment2;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Polygon;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class DroneIcon extends Polygon{
    public int[] XCords = {0, 10, 20};
    public int[] YCords = {27, 0, 27};
    public int zCord = 0;

    public String name = "Drone";
    public Point posMap = null;
   // public int angle = 0;
   // public boolean showText = false;
    public Color color = Color.BLACK;
    public int rad = 18;
    public int totalRot = 0;
    public boolean collisionStatus = false;

    public List<Polygon> history = new ArrayList<>();

    public boolean drawHistoryCheck = true;
    public boolean drawSelectionCheck = false;

    public DroneIcon(Point p, String Name){
        calculateXYPoints(p);
        name = Name;
    }

    private Point getCentroid() {
        return new Point((XCords[0] + XCords[1] + XCords[2])/XCords.length, (YCords[0] + YCords[1] + YCords[2])/YCords.length);
    }

    private void calculateXYPoints(Point p) {
        int Xcentroid = getCentroid().x;
        int Ycentroid = getCentroid().y;

        this.xpoints = new int[] {XCords[0]+p.x-Xcentroid, XCords[1]+p.x-Xcentroid, XCords[2]+p.x-Xcentroid};
        this.ypoints = new int[] {YCords[0]+p.y-Ycentroid, YCords[1]+p.y-Ycentroid, YCords[2]+p.y-Ycentroid};
        this.npoints = 3;
        posMap = p;
    }

    public void updatePosCanvas() {
        posMap.x = (xpoints[0] + xpoints[1] + xpoints[2])/xpoints.length;
        posMap.y = (ypoints[0] + ypoints[1] + ypoints[2])/ypoints.length;
        recordPosition();
    }

    private Point rotatePoint(Point p, Point center, double angle) {
        double x = p.x*Math.cos(angle)-p.y*Math.sin(angle)+center.x*(1-Math.cos(angle))+center.y*Math.sin(angle);
        double y = p.x*Math.sin(angle)+p.y*Math.cos(angle)+center.y*(1-Math.cos(angle))-center.x*Math.sin(angle);
        return new Point( (int) x, (int) y );
    }

    private void rotate(Point center, double angle){
        int xpoints_new[] = new int[3];
        int ypoints_new[] = new int[3];

        for(int i=0;i<this.npoints;i++){
            Point point = rotatePoint(new Point(this.xpoints[i] ,this.ypoints[i]), center, angle);
            xpoints_new[i] = (int) point.x;
            ypoints_new[i] = (int) point.y;
        }
        this.xpoints = xpoints_new;
        this.ypoints = ypoints_new;
        updatePosCanvas();
    }

    public void translate(double x, double y, double z) {
        this.xpoints[0] += x;
        this.xpoints[1] += x;
        this.xpoints[2] += x;

        this.ypoints[0] += y;
        this.ypoints[1] += y;
        this.ypoints[2] += y;

        this.zCord += z;

        updatePosCanvas();
    }

    public void rotateIcon(int angle) {
        this.totalRot = this.totalRot + angle;
        this.rotate(posMap, Math.toRadians(angle));
        updatePosCanvas();
    }

    public void draw(Graphics g) {
        g.setColor(this.color);

        g.fillPolygon(this);
        g.drawPolygon(this);

        drawHistory(g);

        if(drawSelectionCheck == true) {
            g.setColor(Color.CYAN);
            g.drawPolygon(this);
        }

        g.setColor(this.color);
        g.drawString(String.valueOf(this.zCord), this.posMap.x+13, this.posMap.y+20);

    }

    public int getDistance(DroneIcon di) {
        return (int) Math.sqrt(Math.pow((di.posMap.x - this.posMap.x), 2) + Math.pow((di.posMap.y - this.posMap.y), 2) + Math.pow((di.zCord - this.zCord), 2)) ;
    }

    public void drawCircle(Graphics g, int radius) {
        g.setColor(this.color);
        g.drawOval(posMap.x-(radius/2), posMap.y-(radius/2), radius, radius);
    }

    public String getPositionString() {
        int x_temp = this.posMap.x;
        int y_temp = this.posMap.y;
        int z_temp = this.zCord;

        if(infoPanel.coOrdsCheck == true) {
            x_temp = x_temp - 600/2;
            y_temp = 450/2 - y_temp;
        }

        return String.valueOf(x_temp) + "," + String.valueOf(y_temp) + "," + String.valueOf(z_temp);
    }

    public void recordPosition() {
        if(history.isEmpty())
            history.add(new Polygon(this.xpoints, this.ypoints, this.xpoints.length));

        if(!Arrays.equals(this.xpoints, history.get(history.size()-1).xpoints) || !Arrays.equals(this.ypoints, history.get(history.size()-1).ypoints)) {
            history.add(new Polygon(this.xpoints, this.ypoints, this.xpoints.length));
        }
    }

    public void drawHistory(Graphics g) {
        if(drawHistoryCheck == false)
            return;

        int temp=0;
        if(history.size() >= 4)
            temp = history.size()-4;

        for(int i=temp; i<history.size()-1; i++) {
            g.setColor(Color.RED);
            g.drawPolygon(history.get(i));
        }
    }

    public String getDirection() {
        return String.valueOf(totalRot);
    }

    public String getStatus() {
        String result = "";

        int[] xP = new int[] {0,600,600,000};
        int[] yP = new int[] {0,000,450,450};

        if(!new Polygon(xP,yP,xP.length).contains(posMap)) {
            result += "OutBox ";
        }
        if(collisionStatus == true && infoPanel.safetyMarginCheck == true) {
            result += "Collision ";
        }
        if(result.isEmpty())
            result = "Normal";

        return result;
    }

    public static Point generatePosition(boolean b) {
        if(b == false)
            return new Point(300,225);
        else
            return new Point(randomInt(20,580), randomInt(20,430));
    }

    public static int randomInt(int a, int b) {
        return a+new Random().nextInt(b-a);
    }

}