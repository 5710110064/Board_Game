import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class Main {
	
	private static int CountPlayer = 2; // Default to play this game is 2 people
	public static void main(String[] args) 
	{
	  
		JFrame frame = new JFrame("Snake");
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(480, 240);
		frame.getContentPane().setLayout(new BorderLayout());
		JPanel Panel = new JPanel();
		GridLayout layout = new GridLayout(3,0);
		Panel.setLayout(layout);
		
		/*
		 *  Create LAbel of name " Snake board game "
		 */
		JLabel GameName = new JLabel("",SwingConstants.CENTER);
		GameName.setFont(GameName.getFont().deriveFont(24.0f));
		frame.add(GameName, BorderLayout.PAGE_START);
		JButton Start_btn = new JButton("START");
		Start_btn.setBounds(frame.getWidth()/2 - 50, frame.getHeight()/2 - 50, 100, 40);
		JButton Setting_btn = new JButton("SETTING");
		Setting_btn.setBounds(frame.getWidth()/2 - 50, frame.getHeight()/2, 100, 40);
		
		
		
		frame.add(Start_btn);
		Start_btn.addActionListener(new ActionListener() {
			 public void actionPerformed(ActionEvent arg0) {
				 GameController gc = GameController.getInstance(CountPlayer);
				 new GameFrame(CountPlayer);
			 }
		 });
		
		frame.add(Setting_btn);
		Setting_btn.addActionListener(new ActionListener() {
			 public void actionPerformed(ActionEvent arg0) {
				JFrame Setting_frame = new JFrame("SETTING");
				Setting_frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				Setting_frame.setSize(480, 150);
				Setting_frame.getContentPane().setLayout(new BorderLayout());
				JPanel Setting_Panel = new JPanel();
				//GridLayout Setting_layout = new GridLayout(0,2);
				Setting_Panel.setLayout(null);
				Setting_frame.add(Setting_Panel);
				JLabel SettingLabel = new JLabel();
				JLabel PlayerLabel = new JLabel();
				JTextField NumofPlayer = new JTextField();
				SettingLabel.setFont(SettingLabel.getFont().deriveFont(20.0f));
				Setting_Panel.add(SettingLabel);
				PlayerLabel.setFont(PlayerLabel.getFont().deriveFont(16.0f));
				Setting_Panel.add(PlayerLabel);
				Setting_Panel.add(NumofPlayer);
				PlayerLabel.setText("Number of Player : "); //Setting_frame.getWidth()/2)-(SettingLabel.getWidth()/2)
				SettingLabel.setBounds((Setting_frame.getWidth()/2)-50,10,100,30);;
				PlayerLabel.setBounds(20,SettingLabel.getHeight()+20,160,30);
				NumofPlayer.setBounds(PlayerLabel.getWidth()+10,SettingLabel.getHeight()+20,30,30);
				SettingLabel.setText("SETTING");
				
				JButton Apply_btn = new JButton("APPLY");
				Setting_Panel.add(Apply_btn);
				Apply_btn.setBounds(NumofPlayer.getLocation().x + 30 + 20,SettingLabel.getHeight()+20, 160, 30);
				Apply_btn.addActionListener(new ActionListener(){
					   public void actionPerformed(ActionEvent ae){
					      CountPlayer = Integer.parseInt(NumofPlayer.getText());
					      Setting_frame.dispose();
					   }
					});
				Setting_frame.setVisible(true);
			 }
		 });
		frame.add(Panel,BorderLayout.CENTER);
		
		
		
		GameName.setText("SNAKE BOARD GAME");
		frame.setVisible(true);
	//    GameController gc = GameController.getInstance();
	//    /*while (gc.getWinner() == null) {
	//      gc.takeTurn();
	//    }
	//    System.out.println(gc.getWinner() + " is a winner");*/
	//    new GameFrame();
	  }
	}