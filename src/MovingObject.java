//An object on the map that is going to be moving
//3-7-19
//Dr. G


import java.awt.Graphics;
import java.awt.image.BufferedImage;


public class MovingObject extends MapObject {
	
	private int vx;
	private int vy;
	private int tilex;
	private int tiley;
	private String tileLocation [][] = MapLoader.getTileSet();
	private int counter;
	private int turnx;
	private int turny;
	
	public MovingObject(int posx, int posy, BufferedImage bi, int imageW, int imageH, int vx, int vy)
	{
		super(posx, posy, bi,  imageW, imageH);
		this.vx=vx/2;
		this.vy=vy/2;
	}
	
	public int getVx() {
		return vx;
	}
	
	public int getVy() {
		return vy;
	}
	public void checkUp() {
			if(tileLocation[tilex-1][tiley].equals("P") && (tilex != turnx || tiley !=turny)) {
				vy = vx;
				if(vy > 0) {
					vy = vy *-1;
				}
				turnx = tilex;
				turny = tiley;
				vx = 0;
			}
		}
	
	
	public void checkDown() {
		if(tileLocation[tilex+1][tiley].equals("P")&& (tilex != turnx || tiley !=turny)) {
			vy = vx;
			if(vy < 0) {
				vy = vy *-1;
			}
			turnx = tilex;
			turny = tiley;
			vx = 0;
		}
	}
		
	
	public void checkRight() {
		if(tileLocation[tilex][tiley+1].equals("P")&& (tilex != turnx || tiley !=turny)) {
			vx = vy;
			if(vx < 0) {
				vx = vx *-1;
			}
			turnx = tilex;
			turny = tiley;
			vy = 0;
		}
		}
	
	public void checkLeft() {

	}
	public void checkAround() {
		if (vx != 0) {
			checkUp();
			checkDown();
		}
		else if (vy != 0) {
			checkLeft();
			checkRight();
		}



	}
	
	public void drawImage(Graphics g)
	{
		tiley = Math.round(posx/MyCanvas.getTileWidth());
		tilex = Math.round(posy/MyCanvas.getTileHeight());
		if (counter == 6 && posx <580) {
			checkAround();
			counter = 0;
		}
		else {
			counter +=1;
		}
		g.drawImage(bi,posx+=vx, posy+=vy,imageW,imageH,null);
	}

}
