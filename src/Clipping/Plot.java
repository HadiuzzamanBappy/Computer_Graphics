package Clipping;

import java.awt.Color;
import java.awt.Graphics;

public class Plot
{
    private int startX;
    private int startY;
    private int endX;
    private int endY;
    private int method;

    public Plot(int method,int startX, int startY, int endX, int endY){
        this.startX = startX;
        this.startY = startY;
        this.endX = endX ;
        this.endY = endY ;
        this.method=method;
    }

	public int getStartX() {
		return startX;
	}

	public int getStartY() {
		return startY;
	}

	public int getEndX() {
		return endX;
	}
	

	public int getMethod() {
		return method;
	}

	public int getEndY() {
		return endY;
	}
    
}
