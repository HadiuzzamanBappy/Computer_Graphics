package Clipping;

import java.awt.Color;
import java.awt.Graphics;

public class Bresenham {
	Graphics g;
	int x1,y1,x2,y2;
	
	public Bresenham(Graphics g, int x1, int y1, int x2, int y2) {
		this.g = g;
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
	}
	public void bresenham() {
		// TODO Auto-generated method stub
		g.setColor(Color.red);
		System.out.println("bresenham");
        double m=0,b=0,startx,starty,endx,endy;
        startx=(double)x1;
        starty=(double)y1;
        
        //newx=(double)x1;
        //newy=(double)y1;
        
        endx=(double)x2;
        endy=(double)y2;
        
        m=(endy-starty)/(endx-startx);
        b=starty-(m*startx);
        if(Math.abs(m)<1)
        {
        	if(startx<endx)	
        	{
        		if(starty<endy)
        		{
        			int newx = (int)startx;
        			int newy = (int)starty;
        			int dx = (int)endx-(int)startx;
        			int dy = (int)endy-(int)starty;
        			int dt = 2*(dy-dx);
        			int ds = 2*dy;
        			int d = ds-dx;
        			g.fillOval((int)(newx+0.5), (int)(newy+0.5), 3, 3);
        			while(newx<endx)
        			{
        				newx++;
        				if(d<0)
        				{
        					d+=ds;
        				}
        				else
        				{
        					newy++;
        					d+=dt;
        				}
        				g.fillOval((int)(newx+0.5), (int)(newy+0.5), 3, 3);
        			}
        		}
        		else  //starty>endy
        		{
        			int newx = (int)startx;
        			int newy = (int)starty;
        			int dx = (int)endx-(int)startx;
        			int dy = (int)starty-(int)endy;
        			int dt = 2*(dy-dx);
        			int ds = 2*dy;
        			int d = ds-dx;
        			g.fillOval((int)(newx+0.5), (int)(newy+0.5), 3, 3);
        			while(newx<endx)
        			{
        				newx++;
        				if(d<0)
        				{
        					d+=ds;
        				}
        				else
        				{
        					newy--;
        					d+=dt;
        				}
        				g.fillOval((int)(newx+0.5), (int)(newy+0.5), 3, 3);
        			}
        		}
        	}
        	
        	else //startx>endx
        	{
        		if(starty>endy)
        		{	
        			int newx = (int)startx;
        			int newy = (int)starty;
        			int dx = (int)endx-(int)startx;
        			int dy = (int)endy-(int)starty;
        			int dt = 2*(dy-dx);
        			int ds = 2*dy;
        			int d = ds-dx;
        			g.fillOval((int)(newx+0.5), (int)(newy+0.5), 3, 3);
        			while(newx>endx)
        			{
        				newx--;
        				if(d<0)
        				{
        					d-=ds;
        				}
        				else
        				{
        					newy--;
        					d-=dt;
        				}
        				g.fillOval((int)(newx+0.5), (int)(newy+0.5), 3, 3);
        			}
        		}
        		else  //starty<endy  -kaj jahamela
        		{
        			int newx = (int)startx;
        			int newy = (int)starty;
        			int dx = (int)endx-(int)startx;
        			int dy = (int)starty-(int)endy;
        			int dt = 2*(dy-dx);
        			int ds = 2*dy;
        			int d = ds-dx;
        			g.fillOval((int)(newx+0.5), (int)(newy+0.5), 3, 3);
        			while(newx>endx)
        			{
        				newx--;
        				if(d>0)
        				{
        					d+=ds;
        				}
        				else
        				{
        					newy++;
        					d+=dt;
        				}
        				g.fillOval((int)(newx+0.5), (int)(newy+0.5), 3, 3);
        			}
        		}
        		
        	}
        }
        
        else //m>1
        {
        	if(starty<endy)	
        	{
        		if(startx<endx)
        		{
        			int newx = (int)startx;
        			int newy = (int)starty;
        			int dx = (int)endx-(int)startx;
        			int dy = (int)endy-(int)starty;
        			int dt = 2*(dx-dy);
        			int ds = 2*dx;
        			int d = ds-dy;
        			g.fillOval((int)(newx+0.5), (int)(newy+0.5), 3, 3);
        			while(newy<endy)
        			{
        				newy++;
        				if(d<0)
        				{
        					d+=ds;
        				}
        				else
        				{
        					newx++;
        					d+=dt;
        				}
        				g.fillOval((int)(newx+0.5), (int)(newy+0.5), 3, 3);
        			}
        		}
        		//--kaj jhamela ache
        		else  //startx>endx
        		{
        			int newx = (int)startx;
        			int newy = (int)starty;
        			int dx = (int)endx-(int)startx;
        			int dy = (int)starty-(int)endy;
        			int dt = 2*(dx-dy);
        			int ds = 2*dx;
        			int d = ds-dy;
        			g.fillOval((int)(newx+0.5), (int)(newy+0.5), 3, 3);
        			while(newy<endy)
        			{
        				newy++;
        				if(d<0)
        				{
        					d-=ds;
        				}
        				else
        				{
        					newx--;
        					d-=dt;
        				}
        				g.fillOval((int)(newx+0.5), (int)(newy+0.5), 3, 3);
        			}
        		}
        	}
        	
        	else //starty>endy
        	{
        		if(startx>endx)
        		{	
        			int newx = (int)startx;
        			int newy = (int)starty;
        			int dx = (int)endx-(int)startx;
        			int dy = (int)endy-(int)starty;
        			int dt = 2*(dx-dy);
        			int ds = 2*dx;
        			int d = ds-dy;
        			g.fillOval((int)(newx+0.5), (int)(newy+0.5), 3, 3);
        			while(newy>endy)
        			{
        				newy--;
        				if(d<0)
        				{
        					d-=ds;
        				}
        				else
        				{
        					newx--;
        					d-=dt;
        				}
        				g.fillOval((int)(newx+0.5), (int)(newy+0.5), 3, 3);
        			}
        		}
        		//---baki
        		else  //startx<endx  -kaj jahamela
        		{
        			int newx = (int)startx;
        			int newy = (int)starty;
        			int dx = (int)startx-(int)endx;
        			int dy = (int)endy-(int)starty;
        			int dt = 2*(dx-dy);
        			int ds = 2*dx;
        			int d = ds-dy;
        			g.fillOval((int)(newx+0.5), (int)(newy+0.5), 3, 3);
        			while(newy>endy)
        			{
        				newy--;
        				if(d>0)
        				{
        					d+=ds;
        				}
        				else
        				{
        					newx++;
        					d+=dt;
        				}
        				g.fillOval((int)(newx+0.5), (int)(newy+0.5), 3, 3);
        			}
        		}	
        	}    	
        }
	}
}
