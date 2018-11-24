package ca.uqac.gomoku.aspect;

import java.util.ArrayList;
import java.util.List;

import ca.uqac.gomoku.core.Player;
import ca.uqac.gomoku.core.model.Grid;
import ca.uqac.gomoku.core.model.Spot;
import ca.uqac.gomoku.ui.Board;
import ca.uqac.gomoku.ui.App;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

privileged  public aspect FinJeu {
	 private Board b;
	 private List<Spot> winningStonesFinJeu = new ArrayList<>(0);
	private boolean gameOver = false;
	private GraphicsContext gc = null;
	  
	after(Player winner) : call(void notifyGameOver(Player)) && args(winner) {
		 double spotSize = 30; 
		System.out.println(winner.getName());
		 this.gc.setFill(Color.BLACK);
		 this.gc.fillRect(spotSize*2,spotSize*1,spotSize*11,spotSize*1);;
		 this.gc.setFill(Color.WHITE);
		 this.gc.setFont(new Font("comic sans", 20));
		 this.gc.fillText(winner.getName() + " gagne la partie !!!",spotSize*4 ,spotSize*1+ spotSize*2/3);
		 this.gc.setFill(Color.CYAN);
		
	        this.gameOver = true;
	        for (Spot stone : this.winningStonesFinJeu) {
	        	System.out.println("test : (" + stone.toString()+")");
	            double x = spotSize + stone.x * spotSize; // center x
				double y = spotSize + stone.y * spotSize; // center y
				double r = spotSize / 2; // radius
				gc.fillOval(x - r, y - r, r * 2, r * 2);
	        }
		 
	 }
	public List<Spot> Grid.getWinningStones(){
		return this.winningStones;
	}
	public GraphicsContext Board.getGraphicsContext(){
		return this.getGraphicsContext2D();
	}
	pointcut getWinningStones(List<Spot> list) : set(List<Spot> Grid.winningStones) && args(list);
	
	pointcut getCanvas(Canvas playGround) : set(Canvas App.playGround) && args(playGround);
	
	pointcut pointMakeMove(Spot place) : execution(void makeMove(Spot)) &&  args(place);
	void around(Spot place) : pointMakeMove(place){
		if(!gameOver) { 
		System.out.println("Game not over : game can continue;");
		proceed(place);
		}
		else {
			//BLOQUING
			System.out.println("Game over : game has been stopped;");
		}
	}
	after(List<Spot> list) : getWinningStones(list) {
        //this.gc = board.getGraphicsContext();
		this.winningStonesFinJeu = list;
        System.out.println("Whenever Grid.winningStones is set;");
    }
	after(Canvas playGround) : getCanvas(playGround) {
        //this.gc = board.getGraphicsContext();
		this.gc = playGround.getGraphicsContext2D();
        System.out.println("Whenever App.playGround is set;");
    }
	
	after(Player player) : call(boolean isWonBy(Player)) && args(player){
		//this.winningStonesFinJeu = grid.getWinningStones();
		System.out.println("After Grid.isWonBy("+player.getName()+");");
	}
	 
}
