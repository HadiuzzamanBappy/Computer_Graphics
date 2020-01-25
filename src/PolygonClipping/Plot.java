package PolygonClipping;

import java.awt.Color;
import java.awt.Graphics;

public class Plot
{
    private int startX;
    private int startY;
    private int endX;
    private int endY;

    public Plot(int startX, int startY, int endX, int endY){
        this.startX = startX;
        this.startY = startY;
        this.endX = endX ;
        this.endY = endY ;
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

	public int getEndY() {
		return endY;
	}

	public void setStartX(int startX) {
		this.startX = startX;
	}

	public void setStartY(int startY) {
		this.startY = startY;
	}

	public void setEndX(int endX) {
		this.endX = endX;
	}

	public void setEndY(int endY) {
		this.endY = endY;
	}
    
}
