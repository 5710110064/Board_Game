import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;

public class GameFrame extends JFrame {
  private BoardPanel boardPanel;
  private ControlPanel controlPanel;

  public GameFrame(int numofplayer){
    super("Game");
    boardPanel = new BoardPanel(820, 512,numofplayer);
    controlPanel = new ControlPanel(numofplayer);
    setUndecorated(true);
    setSize(1000, 512);

    add(boardPanel, BorderLayout.CENTER);
    controlPanel.setPreferredSize(new Dimension(180, 0));
    add(controlPanel, BorderLayout.LINE_END);
    
    setVisible(true);
  }
}