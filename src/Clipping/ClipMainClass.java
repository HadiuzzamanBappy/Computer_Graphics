package Clipping;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class ClipMainClass {
	static boolean rec=false;
    public static void main(String[] args) {
    	MyPanel myPanel=new MyPanel();
    	
    	JFrame f = new JFrame("Draw a Line");
	    f.setSize(600, 600);
	    f.setLocation(300, 100);
	    f.setResizable(true);
	    
	    JPanel btPanel=new JPanel();
	    JButton directLine=new JButton("Direct Line");
	    directLine.setBackground(Color.YELLOW);
	    directLine.setBounds(0, 0, 100, 25);
	    btPanel.add(directLine);
	    
	    JButton dda=new JButton("DDA");
	    directLine.setBounds(0, 0, 100, 25);
	    btPanel.add(dda);
	    
	    JButton bresenham=new JButton("Bresenham");
	    directLine.setBounds(0, 0, 100, 25);
	    btPanel.add(bresenham);
	    
	    JButton done=new JButton("Next");
	    done.setBounds(0, 0, 100, 25);
	    done.setBackground(Color.LIGHT_GRAY);
	    btPanel.add(done);
	    
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
				directLine.setBackground(Color.YELLOW);
				dda.setBackground(null);
				bresenham.setBackground(null);
			}
		});
	    
	    dda.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				txtfd.setText("DDA");
				directLine.setBackground(null);
				dda.setBackground(Color.YELLOW);
				bresenham.setBackground(null);
			}
		});
	    
	    bresenham.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				txtfd.setText("Bresenham");
				directLine.setBackground(null);
				dda.setBackground(null);
				bresenham.setBackground(Color.YELLOW);
			}
		});
	    
	    done.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						if(done.getText().toString().equals("Next")){
							rec=true;
							done.setText("Rectangle Done!");
							done.setBackground(Color.ORANGE);
						}
						else {
							rec=false;
							done.setText("Next");
							done.setBackground(Color.LIGHT_GRAY);
						}
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
	                    int startx,starty,endx,endy;
				        startx=pointStart.x;
				        starty=pointStart.y;
				        
				        endx=pointEnd.x;
				        endy=pointEnd.y;
				        
				        int method=0;
				        
				        if(done.getText().toString().equals("Next")){
				        	String gettext=txtfd.getText().toString();
				        	if(gettext.equals("Direct Line"))
			            	{
			            		method=1;
			            	}
			            	else if (gettext.equals("DDA")) {
			            		method=2;
							}
			            	else if (gettext.equals("Bresenham")) {
			            		method=3;
							}
				        	myPanel.addPlot(method,startx, starty, endx, endy);
				        }
				        pointStart=null;
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
	            if(pointStart!=null){
	            	if(rec){
	            		new CohenSutherlandClipping(g,myPanel.getPlote(),pointStart.x,pointStart.y,pointEnd.x,pointEnd.y);
	            	}
	            	else{
		            	String gettext=txtfd.getText().toString();
		            	if(gettext.equals("Direct Line"))
		            	{
		            		DirectLine dl=new DirectLine(g,pointStart.x,pointStart.y,pointEnd.x,pointEnd.y); 
			            	dl.directLine();
		            	}
		            	else if (gettext.equals("DDA")) {
		            		DDA dd=new DDA(g,pointStart.x,pointStart.y,pointEnd.x,pointEnd.y); 
			            	dd.dda();
						}
		            	else if (gettext.equals("Bresenham")) {
		            		Bresenham bresenham=new Bresenham(g,pointStart.x,pointStart.y,pointEnd.x,pointEnd.y); 
			            	bresenham.bresenham();
						}
		            	for(Plot plot : myPanel.getPlote()){
		            		if(plot.getMethod()==1)
		            		{
		            			DirectLine dl=new DirectLine(g,plot.getStartX(),plot.getStartY(),plot.getEndX(),plot.getEndY()); 
				            	dl.directLine();
		            		}
		            		else if (plot.getMethod()==2) {
		            			DDA dd=new DDA(g,plot.getStartX(),plot.getStartY(),plot.getEndX(),plot.getEndY()); 
				            	dd.dda();
							}
		            		else if (plot.getMethod()==3) {
		            			Bresenham bresenham=new Bresenham(g,plot.getStartX(),plot.getStartY(),plot.getEndX(),plot.getEndY()); 
				            	bresenham.bresenham();
							}
		            	}
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
