package Clipping;

import java.awt.Color;
import java.awt.Graphics;

public class DirectLine {
	Graphics g;
	int x1,y1,x2,y2;
	String color;
	
	public DirectLine(Graphics g, int x1, int y1, int x2, int y2) {
		this.g = g;
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
	}

	public void directLine() {
		// TODO Auto-generated method stub
		g.setColor(Color.blue);
        double m=0,b=0,newx,newy,startx,starty,endx,endy;
        startx=(double)x1;
        starty=(double)y1;
        
        newx=(double)x1;
        newy=(double)y1;
        
        endx=(double)x2;
        endy=(double)y2;
        
        m=(endy-starty)/(endx-startx);
        b=starty-(m*startx);
        if(m<1)
        {
        	while(newx!=endx){
        		if(startx>endx)
        			newx--;
        		else
        			newx++;
        		newy=(m*newx)+b;
        		g.fillOval((int)(newx+0.5), (int)(newy+0.5), 3, 3);
        	}
        }
        else {
        	while(newy!=endy){
        		if(starty>endy)
        			newy--;
        		else
        			newy++;
        		newx=(newy/m)-(b/m);
        		g.fillOval((int)(newx+0.5), (int)(newy+0.5), 3, 3);
        	}
		}
	}
}
