package assignment2;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DroneCanvas extends Canvas implements MouseListener{

    public int width, height;

    public static List<DroneIcon> droneList = new ArrayList<DroneIcon>();

    public static int margin = 20;
    public static boolean marginEnabled = true;
    public static boolean historyEnabled = true;

    public DroneCanvas(int width, int height){
        this.setSize(width,height);
        this.width = width;
        this.height = height;
        this.update();
        this.addMouseListener(this);
        this.startThread();
    }

    public void startThread() {
        new Thread(new Runnable(){
            public void run(){
                while(true) {
                    update();
                    try {
                        Thread.sleep(100);
                    } catch(InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    public void update() {
        this.repaint();
    }

    public void drawAxis(Graphics g) {
        g.drawLine(Integer.valueOf(width/2), 0, Integer.valueOf(width/2), height);
        g.drawLine(0, Integer.valueOf(height/2), width, Integer.valueOf(height/2));
    }

    public Point getJavaPoint(Point p) {
        return new Point(width/2 + p.x, height/2 - p.y);
    }

    public Point getJavaPoint(int x, int y) {
        return new Point(width/2 + x, height/2 - y);
    }

    public Point getCartPoint(Point p) {
        return new Point(p.x - width/2, height/2 - p.y);
    }

    public Point getCartPoint(int x, int y) {
        return new Point(x - width/2, height/2 - y);
    }

    public void paint(Graphics g){
        drawAxis(g);
        g.setColor(Color.BLUE);

        for(DroneIcon icon : droneList) {
            icon.drawHistoryCheck = historyEnabled;
            icon.draw(g);
        }

        drawCollisionCircle(g);
    }

    public void drawCollisionCircle(Graphics g) {
        Set<DroneIcon> droneInDanger = new HashSet<DroneIcon>();

        if(marginEnabled == false)
            return;

        for(int i = 0; i< droneList.size(); i++) {
            for(int j = i+1; j< droneList.size(); j++) {
                if(droneList.get(i).getDistance(droneList.get(j)) < (droneList.get(i).rad + droneList.get(j).rad + this.margin)) {
                    droneInDanger.add(droneList.get(i));
                    droneInDanger.add(droneList.get(j));
                    droneList.get(i).collisionStatus = true;
                    droneList.get(j).collisionStatus = true;
                }
                else {
                    droneList.get(i).collisionStatus = false;
                    droneList.get(j).collisionStatus = false;
                }
            }
        }

        for(DroneIcon d: droneInDanger) {
            d.drawCircle(g, d.rad *2);
        }

    }

    public DroneIcon getDroneAssociatedWithPnt(Point p) {
        for(DroneIcon icon : droneList) {
            if(icon.contains(p)) {
                return icon;
            }
        }
        return null;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        Point p1 = new Point(e.getX(), e.getY());

        if(infoPanel.selectedDrone != null)
            infoPanel.selectedDrone.drawSelectionCheck = false;

        DroneIcon temp = getDroneAssociatedWithPnt(p1);
        if(temp != null) {
            infoPanel.selectedDrone = temp;
            infoPanel.selectedDrone.drawSelectionCheck = true;
        }
        else {
            infoPanel.selectedDrone = null;
        }

    }

    @Override
    public void mousePressed(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub

    }
}