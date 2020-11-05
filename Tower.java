//Class object for towers
//Needs to contain all characteristics of the tower objects
//3-7-19
//Dr. G

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.security.SecureRandom;
import java.util.ArrayList;

import javax.swing.Timer;

public class Tower extends MapObject{
public ArrayList <Bullet1> bullets = new ArrayList<Bullet1>();
private ArrayList<Enemy> enemies;
private int distance;
private int closest;
private int closestx;
private int closesty;
private Enemy rando;
	
	public Tower(int posx, int posy, BufferedImage bi, int imageW, int imageH, String type)
	{
		super(posx, posy, bi, imageW, imageH);
		posx = Math.round((posx + 5)/MyCanvas.getTileWidth()) * MyCanvas.getTileWidth();
		posy = Math.round((posy + 5)/MyCanvas.getTileHeight()) * MyCanvas.getTileHeight();
		if (type == "Normal") {
			new Timer(1500, createBullet).start();
		}
		else {
			new Timer(750, Destroy).start();
		}
		this.bi = bi;
		closest = 0;
		
	}

	//Added this to override parent
	//Wanted it to draw a bullet when the tower was drawn
	public void drawImage(Graphics g)
	{
		g.drawImage(bi,posx, posy,imageW,imageH,null);	
		for(int x = 0; x<bullets.size(); x++) {
			if (bullets.get(x) != null) {
				bullets.get(x).drawImage(g);
			}
		}
		
	}
	

	
    ActionListener createBullet = new ActionListener() {
        public void actionPerformed(ActionEvent evt) {
        	enemies = MapLoader.getEnemies();
        	
			SecureRandom random = new SecureRandom();
			if(enemies.size()>0) {
				rando = enemies.get(random.nextInt(enemies.size()));
				closestx = rando.getXpos() + rando.getVx() - posx;
				closesty = rando.getYpos() + rando.getVx() - posy;
				bullets.add(new Bullet1(posx + MyCanvas.getTileWidth()/2, posy + MyCanvas.getTileHeight()/2, closestx,closesty, 10, 10));
			}
        	closest = 0;
        }
    };
    
	ActionListener Destroy = new ActionListener() {
        public void actionPerformed(ActionEvent evt) {
        	enemies = MapLoader.getEnemies();
        	
			SecureRandom random = new SecureRandom();
			if(enemies.size()>0) {
				rando = enemies.get(random.nextInt(enemies.size()));
			}
			closestx = rando.getXpos() + rando.getVx() - posx;
			closesty = rando.getYpos() + rando.getVx() - posy;
        	if(enemies.size() > 0) {
        		bullets.add(new Bullet1(posx + MyCanvas.getTileWidth()/2, posy + MyCanvas.getTileHeight()/2, closestx,closesty, 10, 10));
        	}
        	
        	closest = 0;
        	}
        };
    
    public int getY() {
    	return posy;
    }
    
    public int getX() {
    	return posx;
    }
	
	//Got to return the bullet to check the position
	public ArrayList<Bullet1> getBullet()
	{
		return bullets;
	}

}

