//Simple black oval bullet with a set velocity
//3-7-19
//Dr. G

import java.awt.Graphics;
import java.awt.Color;

public class Bullet1 
{
	private double dx;
	private double dy;
	private int posX;
	private int posY;
	private int size;
	private double difference;

	public Bullet1(int posX, int posY, double distancex, double distancey, int speed, int size)
	{
		this.posX = posX;
		this.posY = posY;
		this.dx=distancex;
		this.dy=distancey;
		this.size = size;
		difference = Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2))/speed;
		dx = dx/difference;
		dy = dy/difference;
	}
	
	public int getSize() {
		return size;
	}
			
	//renders our object to the screen
	public void drawImage(Graphics g)
	{
		g.setColor(Color.BLACK);
		g.fillOval(posX += dx, posY+= dy, size,size);
	}

	//If we are going to be able to have enemies take damage, we need a way to know the bullet position
	public int getXpos()
	{return posX;}
	
	public int getYpos()
	{return posY;}
	
	
}