import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

public class GameController implements PlayerListener {
  private Board board;
  public static Dice d1 = new Dice();
  public static Dice d2 = new Dice();

  private Player p[] = new Player[4]; // size of array is 4 cause this game is limit number of player to 4 player
  //private Player p2;
  private Player currentPlayer;
  private Player winner = null;
  private List<Sprite> players = new ArrayList<>();

  private List<GameSpriteListener> spriteListeners = new ArrayList<>();
  private List<GameControlListener> controlListeners = new ArrayList<>();
  
  private int CountPlayer = 0; // initial Count player to 0
  private static int numberofplayer_global;
  private ControlPanel controlPanel2;

  private static GameController instance;
  public static GameController getInstance(int numberofplayer){
    if(instance == null){
    	numberofplayer_global = numberofplayer;
    	instance = new GameController(numberofplayer);
    }
    return instance;
  }

  private GameController(int numberofplayer) {
    board = new Board(80);
    for(CountPlayer = 0; CountPlayer < numberofplayer; CountPlayer++) 
    {
    	p[CountPlayer] = new Player("P" + Integer.toString(CountPlayer + 1), CountPlayer, board, this);
        players.add(p[CountPlayer]);
    }
    
    currentPlayer = p[0];  
  }

  private void rollDice() {
    d1.roll();
    d2.roll();
  }

  public void takeTurn() {
    if(winner != null)
      return;

    rollDice();
    controlPanel2 = new ControlPanel(numberofplayer_global);
    currentPlayer.walk(d1.getFace() + d2.getFace());
  }

  private void changeTurn() {
	  int player = 0;
	  boolean update_flg = true;
	  for(player = 0; player < numberofplayer_global; player++)
	  {
		  if ((currentPlayer == p[numberofplayer_global - 1]) && (update_flg == true)) 
		  {
			  currentPlayer = p[0];
			  update_flg = false;
			  //break;
		  }
		  if ((currentPlayer == p[player]) && (update_flg == true))
		  {
			  currentPlayer = p[player + 1]; // if current player is p[2] next player is p[2+1]
			  update_flg = false;
			  //break;
		  }
	  }
//    if (currentPlayer == p1)
//      currentPlayer = p2;
//    else
//      currentPlayer = p1;
  }

  @Override
  public void walking(Player owner){
    notifyGamePlayEnabled(false);
    for(GameSpriteListener listener : spriteListeners){
      listener.spriteUpdated();
    }
  }

  @Override
  public void walkCompleted(Player onwer) {        
    changeTurn();
    notifyGamePlayEnabled(true);
  }

  @Override
  public void hasWon(Player owner) {
    if (winner == null) { // first winner only
      winner = owner;
    }
  }

  public Player getWinner() {
    return winner;
  }

  public Player getCurrentPlayer() {
    return currentPlayer;
  }

  public List<Sprite> getSprites(){
    return players;
  }

  public void addSpriteListener(GameSpriteListener listener){
    spriteListeners.add(listener);
  }

  public void addControlListener(GameControlListener listener){
    controlListeners.add(listener);
  }

  public void notifyGamePlayEnabled(boolean enabled){
    for(GameControlListener listener : controlListeners){
      listener.changeGamePlayEnabled(enabled);
    }
  }

}