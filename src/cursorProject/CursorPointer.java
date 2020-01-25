package cursorProject;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class CursorPointer {
	public static void main(String args[]) throws Exception {
		
	    JFrame f = new JFrame("Draw a Red Line");
	    f.setSize(600, 600);
	    f.setLocation(300, 100);
	    f.setResizable(true);
	    JPanel btPanel=new JPanel();
	    JButton directLine=new JButton("Direct Line");
	    directLine.setBounds(0, 0, 100, 25);
	    btPanel.add(directLine);
	    
	    JButton dda=new JButton("DDA");
	    directLine.setBounds(0, 0, 100, 25);
	    btPanel.add(dda);
	    
	    JButton bresenham=new JButton("Bresenham");
	    directLine.setBounds(0, 0, 100, 25);
	    btPanel.add(bresenham);
	    
	    JButton bresenhamcircle=new JButton("Bresenham Circle");
	    directLine.setBounds(0, 0, 100, 25);
	    btPanel.add(bresenhamcircle);
	    
	    JButton midpointcircle=new JButton("Midpoint Circle");
	    directLine.setBounds(0, 0, 100, 25);
	    btPanel.add(midpointcircle);
	    
	    JPanel txtPanel=new JPanel();
	    
	    JTextField txtfd=new JTextField(10); 
	    txtfd.setEditable(false);
	    txtPanel.add(txtfd);
	    txtfd.setText("Direct Line");
	    
	    directLine.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				txtfd.setText("Direct Line");
			}
		});
	    
	    dda.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				txtfd.setText("DDA");
			}
		});
	    
	    bresenham.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				txtfd.setText("Bresenham");
			}
		});
	    
	    bresenhamcircle.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				txtfd.setText("Bresenham Circle");
			}
		});
	    
	    midpointcircle.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				txtfd.setText("Midpoint Circle");
			}
		});
	    
	    JPanel p = new JPanel() {
	        Point pointStart = null;
	        Point pointEnd   = null;
	        {
	            addMouseListener(new MouseAdapter() {
	                public void mousePressed(MouseEvent e) {
	                    pointStart = e.getPoint();
	                }

	                public void mouseReleased(MouseEvent e) {
	                    pointStart = null;
	                }
	            });
	            addMouseMotionListener(new MouseMotionAdapter() {
	                public void mouseMoved(MouseEvent e) {
	                    pointEnd = e.getPoint();
	                }

	                public void mouseDragged(MouseEvent e) {
	                    pointEnd = e.getPoint();
	                    repaint();
	                }
	            });
	        }	
	        @Override
	        public void paintComponent(Graphics g) {
	            super.paintComponent(g);
	            if (pointStart != null) {
	            	String gettext=txtfd.getText().toString();
	            	if(gettext.equals("Direct Line"))
	            	{
	            		this.directLine(g);
	            	}
	            	else if (gettext.equals("DDA")) {
	            		this.dda(g);
					}
	            	else if (gettext.equals("Bresenham")) {
						this.bresenham(g);
					}
	            	else if (gettext.equals("Bresenham Circle")) {
						this.bresenhamcircle(g);
					}
	            	else if (gettext.equals("Midpoint Circle")) {
						this.midpointcircle(g);
					}
	            }
	        }
			private void midpointcircle(Graphics g) {
				// TODO Auto-generated method stub
				g.setColor(Color.RED);
				System.out.println("Midpoint");
                double b=0,startx,starty,endx,endy;
                startx=(double)pointStart.x;
                starty=(double)pointStart.y;
                
                //newx=(double)pointStart.x;
                //newy=(double)pointStart.y;
                
                endx=(double)pointEnd.x;
                endy=(double)pointEnd.y;
                
                //int newx = (int)endx - (int)startx;
                //int newy = (int)endy - (int)starty;
                int newx = 0;
                double newyt = Math.sqrt((endx-startx)*(endx-startx)+(endy-starty)*(endy-starty));
                int newy = newx+(int)newyt;
                int p = 1-newy;
                while(newx<=newy)
                {
                	
                	g.fillOval((int)(startx+newx+0.5), (int)(starty+newy+0.5), 3, 3);
                	g.fillOval((int)(startx-newx+0.5), (int)(starty+newy+0.5), 3, 3);
                	g.fillOval((int)(startx-newx+0.5), (int)(starty-newy+0.5), 3, 3);
                	g.fillOval((int)(startx+newx+0.5), (int)(starty-newy+0.5), 3, 3);
                	g.fillOval((int)(startx+newy+0.5), (int)(starty+newx+0.5), 3, 3);
                	g.fillOval((int)(startx-newy+0.5), (int)(starty+newx+0.5), 3, 3);
                	g.fillOval((int)(startx-newy+0.5), (int)(starty-newx+0.5), 3, 3);
                	g.fillOval((int)(startx+newy+0.5), (int)(starty-newx+0.5), 3, 3);
                	if(p<0)
                	{
                		p = p+2*newx+3;
                	}
                	else
                	{
                		p = p + 2*(newx-newy)+5;
                		newy--;
                	}
                	newx++;
                	
                }
				
			}
			private void bresenhamcircle(Graphics g) {
				// TODO Auto-generated method stub
				g.setColor(Color.RED);
                double b=0,startx,starty,endx,endy;
                startx=(double)pointStart.x;
                starty=(double)pointStart.y;
                
                //newx=(double)pointStart.x;
                //newy=(double)pointStart.y;
                
                endx=(double)pointEnd.x;
                endy=(double)pointEnd.y;
                
                //int newx = (int)endx - (int)startx;
                //int newy = (int)endy - (int)starty;
                int newx = 0;
                double newyt = Math.sqrt((endx-startx)*(endx-startx)+(endy-starty)*(endy-starty));
                int newy = newx+(int)newyt;
                int d = 3-2*newy;
                while(newx<=newy)
                {
                	
                	g.fillOval((int)(startx+newx+0.5), (int)(starty+newy+0.5), 3, 3);
                	g.fillOval((int)(startx-newx+0.5), (int)(starty+newy+0.5), 3, 3);
                	g.fillOval((int)(startx-newx+0.5), (int)(starty-newy+0.5), 3, 3);
                	g.fillOval((int)(startx+newx+0.5), (int)(starty-newy+0.5), 3, 3);
                	g.fillOval((int)(startx+newy+0.5), (int)(starty+newx+0.5), 3, 3);
                	g.fillOval((int)(startx-newy+0.5), (int)(starty+newx+0.5), 3, 3);
                	g.fillOval((int)(startx-newy+0.5), (int)(starty-newx+0.5), 3, 3);
                	g.fillOval((int)(startx+newy+0.5), (int)(starty-newx+0.5), 3, 3);
                	if(d<0)
                	{
                		d = d+4*newx+6;
                	}
                	else
                	{
                		d = d + 4*(newx-newy)+10;
                		newy--;
                	}
                	newx++;
                	
                }
				
			}
			private void bresenham(Graphics g) {
				// TODO Auto-generated method stub
				g.setColor(Color.RED);
				System.out.println("bresenham");
                double m=0,b=0,startx,starty,endx,endy;
                startx=(double)pointStart.x;
                starty=(double)pointStart.y;
                
                //newx=(double)pointStart.x;
                //newy=(double)pointStart.y;
                
                endx=(double)pointEnd.x;
                endy=(double)pointEnd.y;
                
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
			private void dda(Graphics g) {
				// TODO Auto-generated method stub
				g.setColor(Color.RED);
				System.out.println("dda");
                double m=0,b=0,newx,newy,startx,starty,endx,endy;
                startx=(double)pointStart.x;
                starty=(double)pointStart.y;
                
                newx=(double)pointStart.x;
                newy=(double)pointStart.y;
                
                endx=(double)pointEnd.x;
                endy=(double)pointEnd.y;
                
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
			private void directLine(Graphics g) {
				// TODO Auto-generated method stub
				g.setColor(Color.RED);
				System.out.println("direct");
		        double m=0,b=0,newx,newy,startx,starty,endx,endy;
		        startx=(double)pointStart.x;
		        starty=(double)pointStart.y;
		        
		        newx=(double)pointStart.x;
		        newy=(double)pointStart.y;
		        
		        endx=(double)pointEnd.x;
		        endy=(double)pointEnd.y;
		        
		        m=(endy-starty)/(endx-startx);
		        b=starty-(m*startx);
		        if(m<1)
		        {
		        	while(newx!=endx){
		        		if(pointStart.x>pointEnd.x)
		        			newx--;
		        		else
		        			newx++;
		        		newy=(m*newx)+b;
		        		g.fillOval((int)(newx+0.5), (int)(newy+0.5), 3, 3);
		        	}
		        }
		        else {
		        	while(newy!=endy){
		        		if(pointStart.y>pointEnd.y)
		        			newy--;
		        		else
		        			newy++;
		        		newx=(newy/m)-(b/m);
		        		g.fillOval((int)(newx+0.5), (int)(newy+0.5), 3, 3);
		        	}
				}
			}
	    };
	    p.add(btPanel);
	    p.add(txtPanel);
	    f.add(p);
	    f.setVisible(true); 
	}

}
