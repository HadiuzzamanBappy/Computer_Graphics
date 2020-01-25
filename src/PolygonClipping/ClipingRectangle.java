package PolygonClipping;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.JPanel;

public class ClipingRectangle {
	
	private int xMin;
    private int xMax;
    private int yMin;
    private int yMax;
    
    Graphics g;
    private ArrayList<Plot> plots;
    
    MyPanel myPanel;
    
    List<double[]> subject, clipper, result;
	
	public ClipingRectangle(Graphics g,ArrayList<Plot> plots,int xMin, int yMin, int xMax, int yMax) {
    	this.g=g;
    	this.plots=plots;
        this.xMin = xMin;
        this.yMin = yMin;
        this.xMax = xMax;
        this.yMax = yMax;
        
        myPanel=new MyPanel();
        
        checkgreater();
    }
	private void checkgreater() {
		// TODO Auto-generated method stub
    	int xnewmin,ynewmin,xnewmax,ynewmax;
		xnewmin=Math.min(xMin, xMax);
		ynewmin=Math.min(yMin, yMax);
		xnewmax=Math.max(xMin, xMax);
		ynewmax=Math.max(yMin, yMax);
		xMin=xnewmin;
		yMin=ynewmin;
		xMax=xnewmax;
		yMax=ynewmax;
		
//		for(Plot plot : plots){
//    		PolygonLine polygonLine1=new PolygonLine(g,plot.getStartX(),plot.getStartY(),plot.getEndX(),plot.getEndY()); 
//    		polygonLine1.polygonLine();
//    	}
//		
//		g.setColor(Color.BLACK);
//		g.drawRect(xMin, yMin, xMax-xMin, yMax-yMin);
		
		myPanel.addClippingPlot(xMin, yMin);
		myPanel.addClippingPlot(xMax, yMin);
		myPanel.addClippingPlot(xMax, yMax);
		myPanel.addClippingPlot(xMin, yMax);
		
		//System.out.println(plots.size());
		
		double[][] subjPoints=new double[plots.size()][2],clipPoints=new double[4][2];
		
		for (int i = 0; i < plots.size(); i++) {
			subjPoints[i][0]=(double)plots.get(i).getStartX();
			subjPoints[i][1]=(double)plots.get(i).getStartY();
		}
		for (int i = 0; i < 4; i++) {
			clipPoints[i][0]=myPanel.getClippingPlote().get(i).getX();
			clipPoints[i][1]=myPanel.getClippingPlote().get(i).getY();
		}
		
		subject = new ArrayList<>(Arrays.asList(subjPoints));
        result  = new ArrayList<>(subject);
        clipper = new ArrayList<>(Arrays.asList(clipPoints));
		
		clipPolygon();
		//System.out.println(xMin+" "+yMin+" "+xMax+" "+yMax);
	}
	private void clipPolygon() {
		int len = clipper.size();
        for (int i = 0; i < len; i++) {
 
            int len2 = result.size();
            List<double[]> input = result;
            result = new ArrayList<>(len2);
 
            double[] A = clipper.get((i + len - 1) % len);
            double[] B = clipper.get(i);
 
            for (int j = 0; j < len2; j++) {
 
                double[] P = input.get((j + len2 - 1) % len2);
                double[] Q = input.get(j);
 
                if (isInside(A, B, Q)) {
                    if (!isInside(A, B, P))
                        result.add(intersection(A, B, P, Q));
                    result.add(Q);
                } else if (isInside(A, B, P))
                    result.add(intersection(A, B, P, Q));
            }
        }
        printall();
    }
 
    private boolean isInside(double[] a, double[] b, double[] c) {
        return (a[0] - c[0]) * (b[1] - c[1]) > (a[1] - c[1]) * (b[0] - c[0]);
    }
 
    private double[] intersection(double[] a, double[] b, double[] p, double[] q) {
        double A1 = b[1] - a[1];
        double B1 = a[0] - b[0];
        double C1 = A1 * a[0] + B1 * a[1];
 
        double A2 = q[1] - p[1];
        double B2 = p[0] - q[0];
        double C2 = A2 * p[0] + B2 * p[1];
 
        double det = A1 * B2 - A2 * B1;
        double x = (B2 * C1 - B1 * C2) / det;
        double y = (A1 * C2 - A2 * C1) / det;
 
        return new double[]{x, y};
    }
    public void printall() {
    	Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(3));
        drawPolygon(g2, subject, Color.green);
        drawPolygon(g2, clipper, Color.red);
        drawPolygon(g2, result, Color.BLUE);
	}
    private void drawPolygon(Graphics2D g2, List<double[]> points, Color color) {
        g2.setColor(color);
        int len = points.size();
        Line2D line = new Line2D.Double();
        for (int i = 0; i < len; i++) {
            double[] p1 = points.get(i);
            double[] p2 = points.get((i + 1) % len);
            line.setLine(p1[0], p1[1], p2[0], p2[1]);
            g2.draw(line);
        }
    }
}