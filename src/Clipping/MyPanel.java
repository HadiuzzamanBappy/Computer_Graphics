package Clipping;

import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JPanel;

class MyPanel extends JPanel
{
    private ArrayList<Plot> plots;

    public MyPanel(){
        plots = new ArrayList<Plot>();
    }

    public void addPlot(int method,int x1, int y1, int x2, int y2){
         plots.add(new Plot(method,x1, y1, x2, y2));
    }
    public ArrayList<Plot> getPlote(){
    	return plots;
    }

    //Invoke addPlot() in your mouseListener after you get startXY & endXY
    //Invoke repaint() after addPlot()
    //Don't have to do this: "repaint(startX,startY,endX,endY);"
}
