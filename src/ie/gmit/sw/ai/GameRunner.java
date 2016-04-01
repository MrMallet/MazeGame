package ie.gmit.sw.ai;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class GameRunner implements KeyListener{
	private static final int MAZE_DIMENSION = 100;
	private Node[][] model;
	private GameView view;
	private int currentRow;
	private int currentCol;
	
	public GameRunner() throws Exception{
		Maze m = new Maze(MAZE_DIMENSION, MAZE_DIMENSION);
		model = m.getMaze();
    	view = new GameView(model);
    	
    	placePlayer();
    	
    	Dimension d = new Dimension(GameView.DEFAULT_VIEW_SIZE, GameView.DEFAULT_VIEW_SIZE);
    	view.setPreferredSize(d);
    	view.setMinimumSize(d);
    	view.setMaximumSize(d);
    	
    	JFrame f = new JFrame("GMIT - B.Sc. in Computing (Software Development)");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.addKeyListener(this);
        f.getContentPane().setLayout(new FlowLayout());
        f.add(view);
        f.setSize(1000,1000);
        f.setLocation(100,100);
        f.pack();
        f.setVisible(true);
	}
	
	private void placePlayer(){   	
    	currentRow = (int) (MAZE_DIMENSION * Math.random());
    	currentCol = (int) (MAZE_DIMENSION * Math.random());
    	model[currentRow][currentCol].setFeature('E');
    	updateView(); 		
	}
	
	private void updateView(){
		view.setCurrentRow(currentRow);
		view.setCurrentCol(currentCol);
	}

    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT && currentCol < MAZE_DIMENSION - 1) {
        	if (isValidMove(currentRow, currentCol + 1)) currentCol++;   		
        }else if (e.getKeyCode() == KeyEvent.VK_LEFT && currentCol > 0) {
        	if (isValidMove(currentRow, currentCol - 1)) currentCol--;	
        }else if (e.getKeyCode() == KeyEvent.VK_UP && currentRow > 0) {
        	if (isValidMove(currentRow - 1, currentCol)) currentRow--;
        }else if (e.getKeyCode() == KeyEvent.VK_DOWN && currentRow < MAZE_DIMENSION - 1) {
        	if (isValidMove(currentRow + 1, currentCol)) currentRow++;        	  	
        }else if (e.getKeyCode() == KeyEvent.VK_Z){
        	view.toggleZoom();
        }else{
        	return;
        }
        
        updateView();       
    }
    public void keyReleased(KeyEvent e) {} //Ignore
	public void keyTyped(KeyEvent e) {} //Ignore

    
	
	
	
	private boolean isValidMove(int r, int c){
		if (r <= model.length - 1 && c <= model[r].length - 1 && model[r][c].getFeature() == ' '){
			model[currentRow][currentCol].setFeature(' '); 
			model[r][c].setFeature('E');
			return true;}
		else if (model[r][c].getFeature()=='B'){
			//model[currentRow][currentCol].setFeature(' ');
			model[r][c].setFeature('X');
			return false; //Can't move but player interacts with something and changes the state of node
		}else if (model[r][c].getFeature()=='H'){
			//model[currentRow][currentCol].setFeature(' ');
			model[r][c].setFeature('X');
			return false; //Can't move but player interacts with something and changes the state of node
		}else if (model[r][c].getFeature()=='?'){
			//model[currentRow][currentCol].setFeature(' ');
			model[r][c].setFeature('X');
			return false; //Can't move but player interacts with something and changes the state of node
		}else if (model[r][c].getFeature()=='W'){
			//model[currentRow][currentCol].setFeature(' ');
			model[r][c].setFeature('X');
			return false; //Can't move but player interacts with something and changes the state of node
		}
		else {
			return false;
		}
	}
	
	public static void main(String[] args) throws Exception{
		new GameRunner();
	}
}