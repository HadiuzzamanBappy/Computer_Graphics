package Clipping;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;



/**
 *
 * @author Enrique Arango Lyons
 */
public class CohenSutherlandClipping extends JPanel {

    public static final int INSIDE = 0;//0000
    public static final int LEFT   = 1;//0001
    public static final int RIGHT  = 2;//0010
    public static final int BOTTOM = 4;//0100
    public static final int TOP    = 8;//1000
    
    private int xMin;
    private int xMax;
    private int yMin;
    private int yMax;
    
    Graphics g;
    private ArrayList<Plot> plots;
    
    private class LineSegment {
        public int x0;
        public int y0;
        public int x1;
        public int y1;

        public LineSegment(int x0, int y0, int x1, int y1) {
            this.x0 = x0;
            this.y0 = y0;
            this.x1 = x1;
            this.y1 = y1;
        }
    }

    public class CohenSutherland{

        /**
         * Computes OutCode for given point (x,y)
         * @param x Horizontal coordinate
         * @param y Vertical coordinate
         * @return Computed OutCode
         */
        private int computeOutCode(double x, double y) {
            int code = INSIDE;

            if (x < xMin) {
                code |= LEFT;
            } else if (x > xMax) {
                code |= RIGHT;
            }
            if (y < yMin) {
                code |= BOTTOM;
            } else if (y > yMax) {
                code |= TOP;
            }

            return code;
        }

        /**
         * Execute line clipping using Cohen-Sutherland
         * Taken from: http://en.wikipedia.org/wiki/Cohen-Sutherland
         * @param line LineSegment to work with
         * @return Clipped line
         */
        public LineSegment clip(LineSegment line) {
            int x0 = line.x0, x1 = line.x1, y0 = line.y0, y1 = line.y1;
            int outCode0 = computeOutCode(x0, y0);
            int outCode1 = computeOutCode(x1, y1);
           // System.out.println("OutCode0: " + outCode0 + ", OutCode1: " + outCode1);
            boolean accept = false;

            while (true) {
                if ((outCode0 | outCode1) == 0) { // Bitwise OR is 0. Trivially accept
                    accept = true;
                    break;
                } else if ((outCode0 & outCode1) != 0) { // Bitwise AND is not 0. Trivially reject
                    break;
                } else {
                    int x, y;

                    // Pick at least one point outside rectangle
                    int outCodeOut = (outCode0 != 0) ? outCode0 : outCode1;

                    // Now find the intersection point;
                    // use formulas y = y0 + slope * (x - x0), x = x0 + (1 / slope) * (y - y0)
                    if ((outCodeOut & TOP) != 0) {
                        x = x0 + (x1 - x0) * (yMax - y0) / (y1 - y0);
                        y = yMax;
                    } else if ((outCodeOut & BOTTOM) != 0) {
                        x = x0 + (x1 - x0) * (yMin - y0) / (y1 - y0);
                        y = yMin;
                    } else if ((outCodeOut & RIGHT) != 0) {
                        y = y0 + (y1 - y0) * (xMax - x0) / (x1 - x0);
                        x = xMax;
                    } else {
                        y = y0 + (y1 - y0) * (xMin - x0) / (x1 - x0);
                        x = xMin;
                    }

                    // Now we move outside point to intersection point to clip
                    if (outCodeOut == outCode0) {
                        x0 = x;
                        y0 = y;
                        outCode0 = computeOutCode(x0, y0);
                    } else {
                        x1 = x;
                        y1 = y;
                        outCode1 = computeOutCode(x1, y1);
                    }
                    accept=true;
                }
            }
            if (accept) {
                return new LineSegment(x0, y0, x1, y1);
            }
            return null;
        }
        
    }
    public CohenSutherlandClipping(Graphics g,ArrayList<Plot> plots,int xMin, int yMin, int xMax, int yMax) {
    	this.g=g;
    	this.plots=plots;
        this.xMin = xMin;
        this.yMin = yMin;
        this.xMax = xMax;
        this.yMax = yMax;
        g.setColor(Color.black);
        
        checkgreater();
        //g.drawRect(xMin, yMin, xMax-xMin, yMax-yMin);
    	//clippedliner();
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
			
			g.drawRect(xMin, yMin, xMax-xMin, yMax-yMin);
	    	clippedliner();
	}
    
	public void clippedliner() {
		// TODO Auto-generated method stub
		LineSegment line, clippedLine;
		for(Plot plot : plots){	
    		line = new LineSegment(plot.getStartX(),plot.getStartY(),plot.getEndX(),plot.getEndY());
            clippedLine = new CohenSutherland().clip(line);
            
            if (clippedLine != null) {                
            	if(plot.getMethod()==1)
        		{
            		DirectLine dl=new DirectLine(g, clippedLine.x0, clippedLine.y0, clippedLine.x1, clippedLine.y1); 
                	dl.directLine();
        		}
        		else if (plot.getMethod()==2) {
        			DDA dd=new DDA(g,clippedLine.x0, clippedLine.y0, clippedLine.x1, clippedLine.y1); 
	            	dd.dda();
				}
        		else if (plot.getMethod()==3) {
        			Bresenham bresenham=new Bresenham(g,clippedLine.x0, clippedLine.y0, clippedLine.x1, clippedLine.y1); 
	            	bresenham.bresenham();
				}
            }
		}
	}

}