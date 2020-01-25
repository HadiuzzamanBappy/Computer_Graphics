package PolygonClipping;

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

public class MainClass {
	static boolean rec=false;
	static boolean first=true;
	static Graphics graphics;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MyPanel myPanel=new MyPanel();
		
    	JFrame f = new JFrame("Draw a Line");
	    f.setSize(600, 600);
	    f.setLocation(300, 100);
	    f.setResizable(true);
	    
	    JPanel btPanel=new JPanel();
	    
	    JButton done=new JButton("Next");
	    done.setBounds(0, 0, 100, 25);
	    done.setBackground(Color.LIGHT_GRAY);
	    btPanel.add(done);
	    
	    JPanel txtPanel=new JPanel();
	    
//	    JTextField txtfd=new JTextField(10); 
//	    txtfd.setEditable(false);
//	    txtPanel.add(txtfd);
//	    txtfd.setText("Direct Line");
	    
	    done.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(done.getText().toString().equals("Next")){
					rec=true;
					done.setText("Rectangle Done!");
					done.setBackground(Color.ORANGE);
					done.setEnabled(false);
				}
//				else {
//					rec=false;
//					done.setText("Next");
//					done.setBackground(Color.LIGHT_GRAY);
//				}
			}
		});
	    
	    JPanel p = new JPanel() {
	        Point pointStart = null;
	        Point pointEnd   = null;
	        Point recentPointEnd   = null;
	        {
	            addMouseListener(new MouseAdapter() {
	                public void mousePressed(MouseEvent e) {
	                    pointStart = e.getPoint();
	                }

	                public void mouseReleased(MouseEvent e) {
	                    int startx,starty,endx,endy,recentPointEndx,recentPointEndy;
				        startx=pointStart.x;
				        starty=pointStart.y;
				        
				        endx=pointEnd.x;
				        endy=pointEnd.y;
				        
				        if(done.getText().toString().equals("Next")){
					        if(first){
					        	myPanel.addPlot(startx, starty, endx, endy);
					        	first=false;
					        	//System.out.println("true");
					        }
					        else{
					        	myPanel.addPlotNotFirst(myPanel.getPlote(),startx, starty, endx, endy);
					        	//System.out.println("false");
					        }
				        }
				        else {
							myPanel.addStartWithEnd();
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
	            		new ClipingRectangle(g,myPanel.getPlote(),pointStart.x,pointStart.y,pointEnd.x,pointEnd.y);
	            	}
	            	else{
	            		ArrayList<Plot> plots=myPanel.getPlote();
		            	if(plots.size()>0){
			            	Plot last = plots.get(plots.size() - 1); 
			            	
		            		PolygonLine polygonLine=new PolygonLine(g,last.getEndX(),last.getEndY(),pointEnd.x,pointEnd.y); 
		            		polygonLine.polygonLine();
		            	}
		            	else {
		            		PolygonLine polygonLine=new PolygonLine(g,pointStart.x,pointStart.y,pointEnd.x,pointEnd.y); 
		            		polygonLine.polygonLine();
						}
		            	for(Plot plot : myPanel.getPlote()){
		            		PolygonLine polygonLine1=new PolygonLine(g,plot.getStartX(),plot.getStartY(),plot.getEndX(),plot.getEndY()); 
		            		polygonLine1.polygonLine();
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
