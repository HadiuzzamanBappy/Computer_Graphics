package PolygonClipping;

import java.awt.Color;
import java.awt.Graphics;

public class PolygonLine {
	Graphics g;
	int x1,y1,x2,y2;
	
	public PolygonLine(Graphics g, int x1, int y1, int x2, int y2) {
		this.g = g;
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
	}
	public void polygonLine() {
		// TODO Auto-generated method stub
		g.setColor(Color.green);
		//System.out.println("dda");
        double m=0,b=0,newx,newy,startx,starty,endx,endy;
        startx=(double)x1;
        starty=(double)y1;
        
        newx=(double)x1;
        newy=(double)y1;
        
        endx=(double)x2;
        endy=(double)y2;
        
        m=(endy-starty)/(endx-startx);
        b=starty-(m*startx);
        if(Math.abs(m)<1)
        {
        	if(startx<endx)
        	{
        		while(newx!=endx){
        		newx++;
        		newy = newy+m;
        		g.fillOval((int)(newx+0.5), (int)(newy+0.5), 3, 3);
        		}
        	}
        	else
        	{
        		while(newx!=endx){
            		newx--;
            		newy = newy-m;
            		g.fillOval((int)(newx+0.5), (int)(newy+0.5), 3, 3);
            		}
        	}
        }
        else {
        	if(starty<endy)
        	{
        		while(newy!=endy){
        		newy++;
        		newx = newx+1/m;
        		g.fillOval((int)(newx+0.5), (int)(newy+0.5), 3, 3);
        		}
        	}
        	else
        	{
        		while(newy!=endy){
            		newy--;
            		newx = newx-1/m;
            		g.fillOval((int)(newx+0.5), (int)(newy+0.5), 3, 3);
            		}
        	}
        }
	}
}
