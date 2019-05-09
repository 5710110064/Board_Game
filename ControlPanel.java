import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class ControlPanel extends JPanel implements GameControlListener{
  private GameController gc;
  private JButton playButton;
  private JLabel playerLabel;
  private JLabel DiceLabel;
  private Image DicePic1;
  private Image DicePic2;
  

  public ControlPanel(int numofplayer){
    gc = GameController.getInstance(numofplayer);
    gc.addControlListener(this);

    JButton exitButton = new JButton("Exit");
    exitButton.addActionListener(new ActionListener(){    
      @Override
      public void actionPerformed(ActionEvent e) {
        System.exit(1);
      }
    });

    playButton = new JButton("Play");
    playButton.addActionListener(new ActionListener(){
      @Override
      public void actionPerformed(ActionEvent e) {
        gc.takeTurn();
      }
    });

    playerLabel = new JLabel("", SwingConstants.CENTER);
    playerLabel.setFont(playerLabel.getFont().deriveFont(24.0f));
    
    DiceLabel = new JLabel("",SwingConstants.CENTER);
    DiceLabel.setFont(playerLabel.getFont().deriveFont(24.0f));
    updatePlayerLabel();
    setLayout(new GridLayout(0, 1));
    add(exitButton);
    add(playerLabel);
    add(DiceLabel);
    add(playButton);    
  }

  public void changeGamePlayEnabled(boolean enabled){
    playButton.setEnabled(enabled);
    updatePlayerLabel();
    updateDice();
  }

  private void updatePlayerLabel(){
    playerLabel.setText(gc.getCurrentPlayer().toString());
  }
  
  public void updateDice()
  {
	  DiceLabel.setText(Integer.toString(gc.d1.getFace() + gc.d2.getFace()));
  }
}