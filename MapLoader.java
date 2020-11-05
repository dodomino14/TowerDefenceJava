//Class object for loading the basic map
//Contains the paint method for add and removing items from the map
//Base code taken from the file reader lab
//3-7-9
//Dr. G

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.TimerTask;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Timer;

import java.util.Scanner;

@SuppressWarnings({ "serial", "unused" })
public class MapLoader extends JPanel {

	//Instance variables
	static ArrayList<Tower> towers = new ArrayList<Tower>();
	static ArrayList<Enemy> enemies = new ArrayList<Enemy>();
	static int width;
	static int height;
	private static String[][] tileset;
	private String tile;
	public  static int round;
	public static int money;
	public static int health;
	private int spawnrate;
	Timer timer;
	BufferedImage bloon;
	private int startpos;
	JTextField myOutput;
	private int enemycount;
	

	
	
	public void generateImages() {
		try {
			bloon = ImageIO.read(new File("src//bloon.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	
	public static int getRound() {
		return round;
	}
	public static int getMoney() {
		return money;
	}
	public static int getHealth() {
		return health;
	}
	
	public MapLoader(){
		try {
			Scanner input = new Scanner(new File("src//Map1.txt"));
			int rows = input.nextInt();
			int cols = input.nextInt();
			height = rows;
			width = cols;
			System.out.println("Width " + width + "Height " + height);
			tileset = new String[height][width];
			
			MyCanvas myCanvas = null; 
			myCanvas = new MyCanvas(rows, cols);
			generateImages();

			//load tile images
			for (int x = 0; x< rows; x++)
				for (int y = 0; y < cols; y++) {
					tile = input.next();
					myCanvas.addPicture(x, y,tile + ".jpg");
					tileset[x][y] = tile;
				}
			
			if (myCanvas != null){
				this.add(myCanvas);
			}
			this.setVisible(true);
			
			input.close();
		}
		catch(FileNotFoundException e) {
			System.out.println("Your file didn't import dummy" + e);
		}
		
		//print out your map
		for(int x = 0;x <  tileset.length; x++) {
			System.out.println();
			for(int y = 0; y < tileset[x].length; y++) {
				System.out.print(tileset[x][y] + " ");
			}
		}
	}
	

	
	public int returnStart(String arr [][]) {
		int S = 1;
		for(int x = 0; x<arr.length; x++) {
			for(int y = 0; y<arr[x].length; y++) {
				if (arr[x][y].equals("S") ) {
					System.out.println(x);
					S = x;
				}
			}
		}
		return S;
		
	}
	

	
	//public void createTower(int x, int y, int style)
	public void createTower(int x, int y)
	{
		try {
			int ox = x;
			int oy = y;
			x = Math.round((x + 10)/MyCanvas.getTileWidth()) * MyCanvas.getTileWidth();
			y = Math.round((y + 15)/MyCanvas.getTileHeight()) * MyCanvas.getTileHeight();
			if(towers.size() > 0) {
				for(int z = 0; z <= towers.size(); z++) {
					System.out.println(Math.round(ox/MyCanvas.getTileWidth()));
					System.out.println(Math.round(oy/MyCanvas.getTileHeight()));
					System.out.println(tileset[Math.round(oy/MyCanvas.getTileHeight())][Math.round(ox/MyCanvas.getTileWidth())]);
	
//					if(tileset[Math.round(oy/MyCanvas.getTileHeight())][Math.round(ox/MyCanvas.getTileWidth())].equals("G")) {
//						
//					}
					if(z == towers.size()) {
						if(GameDriver.getTowerType() == "Normal") {
							if(money>=250) {
								towers.add(new Tower(x, y, ImageIO.read(new File("src//Crystal.png")), MyCanvas.getTileWidth(), MyCanvas.getTileHeight(), GameDriver.getTowerType()));
								money-=250;
								GameDriver.getMoney().setText("Money: " + (money));
							}
							else {
								System.out.println("You don't have enough money");
							}
						}
						if(GameDriver.getTowerType() == "Heavy") {
							if(money>=500) {
								towers.add(new Tower(x, y, ImageIO.read(new File("src//Tower2.png")), MyCanvas.getTileWidth(), MyCanvas.getTileHeight(), GameDriver.getTowerType()));
								money-=500;
								GameDriver.getMoney().setText("Money: " + (money));
							}
							else {
								System.out.println("You don't have enough money");
							}
						}
						
						z+=1;
						
					}
					
					else if(towers.get(z).getX() == x && towers.get(z).getY() == y) {
						System.out.println("There is already a tower present at this location");
						break;
					}
				}
			}
			else {
				if(money>=250) {
					towers.add(new Tower(x, y, ImageIO.read(new File("src//Crystal.png")), MyCanvas.getTileWidth(), MyCanvas.getTileHeight(), GameDriver.getTowerType()));
					money-=250;
					GameDriver.getMoney().setText("Money: " + (money));
				}
			}
			
			
		}
		catch (IOException e)
		{
		System.out.println("Unable to generate tower due to IO exception" + e);
		}
	}

	public void start()
	{
		if(round <=1) {
			round = 1;
			money = 500;
			health = 20;
		}
			startpos = returnStart(tileset) * MyCanvas.getTileHeight() + (MyCanvas.getTileHeight()/4);
			GameDriver.getRound().setText("Round: " + round);
			timer = new Timer(1000 - (200*round), createEnemies);
			timer.start();
	}
	
    ActionListener createEnemies = new ActionListener() {
        public void actionPerformed(ActionEvent evt) {
        	if(enemycount < (25 + Math.pow(round, 2) * 5)) {
        		enemies.add(new Enemy(0,startpos, bloon, MyCanvas.getTileHeight()/2, MyCanvas.getTileWidth()/2, 5, 0, 20));
        		enemycount +=1;
            }
        	else {
        		if(enemies.size() < 1) {
        			System.out.println("You beat the round! Woohoo!");
        			round+=1;
        			timer.stop();
        			enemycount = 0;
        			start();
        		}
        		
        	}
        	}
        
        	
    };
	
	public void paint(Graphics g){
		super.paint(g);
		try{
			
			
		//bullet and enemy position
		int bx = 0;
		int by = 0;
		int ex = 999;
		int ey = 999;
			

			
		for(int x = 0; x<towers.size(); x++) {
 
			if(towers.get(x) != null) 
			{
				towers.get(x).drawImage(g);
				for(int y = 0; y < towers.get(x).getBullet().size(); y++) {
					if (towers.get(x).getBullet().get(y).getXpos() > 600) {
						towers.get(x).getBullet().remove(y);
					}
					else if(towers.get(x).getBullet().get(y).getYpos() > 600 || towers.get(x).getBullet().get(y).getXpos() <= 0) {
						towers.get(x).getBullet().remove(y);
					}
				}
			}
		}
		
		
				try {
					for(int x = 0; x<enemies.size(); x++) {
						if (enemies.get(x) != null)
						{
							if(enemies.get(x).getXpos() >= 580) {
								health -=1;
								GameDriver.getLives().setText("Health: " + health);
								enemies.remove(x);
								System.out.println("deleted enemy");
							}
							
							if(health<= 0) {
								System.exit(0);
							}
							enemies.get(x).drawImage(g);
						}
					}
				}
				catch(IndexOutOfBoundsException e) {
					
				}

				
				
				//check bullet and enemy position
				for(int x = 0; x < towers.size() ; x++) {
					for(int y = 0; y <towers.get(x).getBullet().size(); y++ ) {
						bx = towers.get(x).getBullet().get(y).getXpos();
						by = towers.get(x).getBullet().get(y).getYpos();
						
						for(int z = 0; z < enemies.size(); z++) {
							ex = enemies.get(z).getXpos();
							ey = enemies.get(z).getYpos();
//							int size = towers.get(x).getBullet().get(y).getSize()/2;
							if (((ex >= (bx - 20)) && (ex <= (bx + 20))) && ((ey >= (by - 20)) && (ey <= (by + 20))))
							{
								enemies.remove(z);
								GameDriver.getMoney().setText("Money: " + (money+=10));
								try {
									towers.get(x).getBullet().remove(y);
								}
								catch(IndexOutOfBoundsException e) {
									try {
										towers.get(x).getBullet().remove(towers.get(x).getBullet().size()-1);
									}
									catch(IndexOutOfBoundsException a) {
										
									}
									
								}
							}
						}
					}


					
				}

				
				Thread.sleep(30);
				repaint();
		}
		catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	
	public static ArrayList<Tower> getTowers() {
		return towers;
	}
	public static int getLength() {
		return width;
	}
	
	public static int getHeights() {
		return height;
	}
	
	public static String[][] getTileSet() {
		return tileset;
	}
	public static ArrayList<Enemy> getEnemies() {
		return enemies;
	}
}


//MyCanvas taken from file reader lab
//Notice that this too is a panel
@SuppressWarnings("serial")
class MyCanvas extends JPanel{
	private BufferedImage[][] imgs;
	private int rows;
	private int cols;
	private final static int tileWidth = 600/ MapLoader.getLength();
	private final static int tileHeight = 600/MapLoader.getHeights();

	public MyCanvas(int rows, int cols){
		super();
		this.rows = rows;
		this.cols = cols;
		imgs = new BufferedImage[rows][cols];
	}
	
	public static int getTileWidth() {
		return tileWidth;
	}
	
	public static int getTileHeight() {
		return tileHeight;
	}
	public void addPicture(int x, int y, String filename){
		if (x < 0 || x >= rows){
			System.err.println("There is no row " + x);
		}
		else if (y < 0 || y >= cols){
			System.err.println("There is no col " + y);
		}
		else{
				try {
					imgs[x][y] = ImageIO.read(new File("src//" + filename));
				} catch (IOException e) {
					System.err.println("Unable to read the file: " + filename);
				}
		}
	}
	public void paint(Graphics g){
		for (int i = 0; i < rows; i++){
			for (int j = 0; j < cols; j++){
				g.drawImage(imgs[i][j], j*tileWidth, i*tileHeight,null);
			}
		}
	}
}