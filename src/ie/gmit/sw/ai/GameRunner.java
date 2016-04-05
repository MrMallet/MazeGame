package ie.gmit.sw.ai;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import ie.gmit.sw.ai.traversers.*;

public class GameRunner implements KeyListener{
	private static final int MAZE_DIMENSION = 70;
	private Node[][] model;
	private GameView view;
	private int currentRow;
	private int currentCol;
	private int goalRow;
	private int goalCol;
	
	public GameRunner() throws Exception{
		Maze m = new Maze(MAZE_DIMENSION, MAZE_DIMENSION);
		model = m.getMaze();
    	view = new GameView(model);
    	
    	placePlayer();
    	placeGoalNode();
    	for(int i=0;i<=20;i++){
    		placeEnemies();
    	}
    	System.out.println("got here at least");
    	Traversator t = new BeamTraversator(model[goalRow][goalCol], 3);
    	t.traverse(model, model[0][0], view);
    	

    	/*
    	 * list:
    	 * called the visited and change color of the node in both views
    	 * 
    	 * 
    	 * create player objects
    	 * create enemy objects
    	 * Enemy e1 = new Enemy();
    	 * Enemy e2 = new Enemy();
    	 * Enemy e3 = new Enemy();
    	 * Enemy e4 = new Enemy();
    	 * Enemy e5 = new Enemy();    
    	 *
    	 *
    	 * threading: use swing utilities
    	 * asynchronous..
    	 * 
    	 */
    	
    	
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
	
	private void placeEnemies(){
		int enemyRow = (int) (MAZE_DIMENSION * Math.random());
    	int enemyCol = (int) (MAZE_DIMENSION * Math.random());
    	model[enemyRow][enemyCol].setFeature('E');
    	//updateView();
	}
	
	private void placeGoalNode(){
		goalRow = (int) (MAZE_DIMENSION * Math.random());
		goalCol = (int) (MAZE_DIMENSION * Math.random()); 
    	model[goalRow][goalCol].setGoalNode(true);
    	model[goalRow][goalCol].setFeature('G');
    	//updateView();
	}
	
	private void placePlayer(){   	
    	currentRow = (int) (MAZE_DIMENSION * Math.random());
    	currentCol = (int) (MAZE_DIMENSION * Math.random());
    	model[currentRow][currentCol].setFeature('P');
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
			model[r][c].setFeature('P');
			model[currentRow][currentCol].setVisited(true);
			System.out.println("current row: " + currentRow + " current col: "+currentCol+ " isVisited() returns: " + model[currentRow][currentCol].isVisited());
//			for(int i =0; i<40;i++){
//				for(int j =0; j<40; j++){
//					System.out.print(model[i][j].getFeature());
//					System.out.print(model[i][j].isVisited());
//				}
//				System.out.println();
//			}
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
		}else if (model[r][c].getFeature()=='G'){
			//model[currentRow][currentCol].setFeature(' ');
			model[r][c].setFeature('G');
			return false; //Can't move but player interacts with something and changes the state of node
		}else if (model[r][c].isGoalNode()){
			//model[currentRow][currentCol].setFeature(' ');
			model[r][c].setFeature('P');
			return true; //Can't move but player interacts with something and changes the state of node
		}
		else {
			return false;
		}
	}
	
	public static void main(String[] args) throws Exception{
		new GameRunner();
		
//		Traversator t = new IDDFSTraversator();
//    	t.traverse(model, model[0][0], view);
	}
}