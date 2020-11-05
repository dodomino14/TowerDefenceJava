//Demo code to demonstrate GUI Building Part 2
//Tie in to semester long project
//Not necessarily meant to be copied and used directly
//3-9-19
//Dr. G

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon; //added
import java.awt.Image; //added
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GameDriver extends JFrame{
	
	static JLabel lblMoney;
	static JLabel lblLives;
	static JLabel lblRound;
	static String towerType;
	
	public GameDriver() {
		
		super("Tower Defense"); //added

		getContentPane().setLayout(null);
		
		JPanel Controls = new JPanel();
		Controls.setBounds(0, 0, 175, 600);
		getContentPane().add(Controls);
		Controls.setLayout(null);
		
		lblMoney = new JLabel("Money: 500");
		lblMoney.setBounds(6, 6, 80, 16);
		Controls.add(lblMoney);
		
		lblLives = new JLabel("Lives: 20" + MapLoader.getHealth());
		lblLives.setBounds(6, 21, 80, 16);
		Controls.add(lblLives);
		
		lblRound = new JLabel("Round: 0" + MapLoader.getRound());
		lblRound.setBounds(6, 37, 80, 14);
		Controls.add(lblRound);
		JPanel Map = new MapLoader();
		Map.setBounds(175, 0, 600, 600);
		getContentPane().add(Map);
		
		Map.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				((MapLoader)Map).createTower(e.getX(),e.getY());
			}
		});
		Map.setLayout(new GridLayout(1, 0, 0, 0));
		
		//added
		ImageIcon GIcon = new ImageIcon("Crystal.png");
			Image image = GIcon.getImage();
			Image newImage = image.getScaledInstance(40, 40,  java.awt.Image.SCALE_SMOOTH);
			GIcon = new ImageIcon(newImage);
			
			ImageIcon GIcon2 = new ImageIcon("Tower2.png");
			Image image2 = GIcon2.getImage();
			Image newImage2 = image2.getScaledInstance(40, 40,  java.awt.Image.SCALE_SMOOTH);
			GIcon2 = new ImageIcon(newImage2);
		
		

		
		
		JButton btnNewButton = new JButton("START");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				((MapLoader)Map).start();
			}
		});
		//btnNewButton.setBounds(6, 74, 117, 29);
		btnNewButton.setBounds(6, 500, 114, 29);
		Controls.add(btnNewButton);
		
		//JButton btnStart = new JButton("Tower 0");
		JButton btnStart = new JButton(GIcon);
		btnStart.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			towerType = "Normal";
			}
		});
		//btnStart.setBounds(6, 45, 117, 29);
		btnStart.setBounds(6, 300, 114, 65);
		Controls.add(btnStart);
		
		JButton button = new JButton(GIcon2);
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			towerType = "Heavy";
			}
		});
		button.setBounds(6, 225, 114, 65);
		Controls.add(button);
		
		JButton button_1 = new JButton("");
		button_1.setBounds(6, 150, 114, 65);
		Controls.add(button_1);
		

		
		JButton btnNewButton_1 = new JButton("");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnNewButton_1.setBounds(6, 75, 114, 65);
		Controls.add(btnNewButton_1);
	}
	
	public static String getTowerType() {
		return towerType;
	}
	public static JLabel getMoney() {
		return lblMoney;
	}
	public static JLabel getRound() {
		return lblRound;
	}
	public static JLabel getLives() {
		return lblLives;
	}

	public static void main(String[] args) {
	
		//1. Answer the questions found in Questions.txt

		GameDriver m = new GameDriver();
		m.setSize(850, 650);
		m.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		m.setVisible(true);
		

	}
}
