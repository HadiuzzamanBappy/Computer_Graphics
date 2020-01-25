package PolygonClipping;

import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JPanel;

class MyPanel extends JPanel
{
    private ArrayList<Plot> plots;
    private ArrayList<ClippingPlot> clippingPlots;

    public MyPanel(){
        plots = new ArrayList<Plot>();
        clippingPlots = new ArrayList<ClippingPlot>();
    }

    public void addPlot(int x1, int y1, int x2, int y2){
         plots.add(new Plot(x1, y1, x2, y2));
    }
    public void addClippingPlot(int x,int y){
    	clippingPlots.add(new ClippingPlot(x,y));
   }
    public void addPlotNotFirst(ArrayList<Plot> plotsn,int x1, int y1, int x2, int y2){
    	 Plot last = plotsn.get(plotsn.size() - 1); 
         plots.add(new Plot(last.getEndX(), last.getEndY(), x2, y2));
    }
    public void addStartWithEnd(){
    	if(plots.size()>0){
	    	Plot first = plots.get(0); 
	    	//Plot last = plots.get(plots.size() - 1);
	    	
	    	plots.get(plots.size()-1).setEndX(first.getStartX());
	    	plots.get(plots.size()-1).setEndY(first.getStartY());
    	}
    }
    public ArrayList<Plot> getPlote(){
    	return plots;
    }
    public ArrayList<ClippingPlot> getClippingPlote(){
    	return clippingPlots;
    }
    //Invoke addPlot() in your mouseListener after you get startXY & endXY
    //Invoke repaint() after addPlot()
    //Don't have to do this: "repaint(startX,startY,endX,endY);"
}
