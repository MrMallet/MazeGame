package ie.gmit.sw.ai;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import javax.imageio.*;
import javax.swing.*;
public class GameView extends JPanel implements ActionListener{
	private static final long serialVersionUID = 1L;
	public static final int DEFAULT_VIEW_SIZE = 800;	
	private static final int IMAGE_COUNT = 9;
	private int cellspan = 5;	
	private int cellpadding = 2;
	private Node[][] maze;
	private BufferedImage[] images;
	private int player_state = 5;
	private Timer timer;
	private int currentRow;
	private int currentCol;
	private boolean zoomOut = false;
	private int imageIndex = -1;
	
	public GameView(Node[][] maze) throws Exception{
		init();
		this.maze = maze;
		setBackground(Color.LIGHT_GRAY);
		setDoubleBuffered(true);
		timer = new Timer(300, this);
		timer.start();
	}
	
	public void setCurrentRow(int row) {
		if (row < cellpadding){
			currentRow = cellpadding;
		}else if (row > (maze.length - 1) - cellpadding){
			currentRow = (maze.length - 1) - cellpadding;
		}else{
			currentRow = row;
		}
	}

	public void setCurrentCol(int col) {
		if (col < cellpadding){
			currentCol = cellpadding;
		}else if (col > (maze[currentRow].length - 1) - cellpadding){
			currentCol = (maze[currentRow].length - 1) - cellpadding;
		}else{
			currentCol = col;
		}
	}

	public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g; 
        cellspan = zoomOut ? maze.length : 5;         
        final int size = DEFAULT_VIEW_SIZE/cellspan;
        g2.drawRect(0, 0, GameView.DEFAULT_VIEW_SIZE, GameView.DEFAULT_VIEW_SIZE);
              
        for(int row = 0; row < cellspan; row++) {
        	for (int col = 0; col < cellspan; col++){  
        		int x1 = col * size;
        		int y1 = row * size;
        		
        		char ch = 'X';
        		
        		if (maze[row][col].isVisited() && !maze[row][col].isGoalNode()){
        			g2.setColor(maze[row][col].getColor());
        			g2.fillRect(x1, y1, size, size);
        		}
        		
       			if (maze[row][col].isGoalNode()){
       				g2.setColor(Color.GREEN);
       				g2.fillRect(x1, y1, size, size);
       			}
        		
        		if (zoomOut){
        			ch = maze[row][col].getFeature();
        			
//        			if(maze[row][col].getFeature()== 'E'){ // enemy
//        				g2.setColor(Color.BLACK);
//        				g2.fillRect(x1, y1, size, size);
//        				continue;
//        			}

        			if(maze[row][col].getFeature()== 'H'){ //health
        				g2.setColor(Color.ORANGE);
        				g2.fillRect(x1, y1, size, size);
        				continue;
        			}
        			
        			if(maze[row][col].getFeature()== 'W'){ //sword
        				g2.setColor(Color.MAGENTA);
        				g2.fillRect(x1, y1, size, size);
        				continue;
        			}
        			
        			if(maze[row][col].isGoalNode()){ //girl/ Goal Node
        				g2.setColor(Color.RED);
        				g2.fillRect(x1,y1,size,size);
        				continue;
        			}
        			if (row == currentRow && col == currentCol){ //player
        				g2.setColor(Color.YELLOW);
        				g2.fillRect(x1, y1, size, size);
        				continue;
        			}
        			
        			if(maze[row][col].isVisited()){
        				g2.setColor(Color.PINK);
        				g2.fillRect(x1, y1, size, size);
        				continue;
        			}
        		}else{
        			ch = maze[currentRow - cellpadding + row][currentCol - cellpadding + col].getFeature();
        		}
        		
        		
        		if (ch == 'X'){        			
        			imageIndex = 0;
        		}else if (ch == 'W'){
        			imageIndex = 1;
        		}else if (ch == '?'){
        			imageIndex = 2;
        		}else if (ch == 'B'){ // left out of the maze 
        			imageIndex = 3;
        		}else if (ch == 'H'){
        			imageIndex = 4;
        		}else if (ch == 'P'){
        			imageIndex = player_state;
        		}else if (ch == 'E'){
        			imageIndex = 7;
        		}else if (ch == 'G'){
        			imageIndex = 8;
        		}else{
        			imageIndex = -1;
        		}
        		
        		if (imageIndex >= 0){
        			g2.drawImage(images[imageIndex], x1, y1, null);
        		}else{
        			g2.setColor(Color.LIGHT_GRAY);
        			g2.fillRect(x1, y1, size, size);
        		}      		
        	}
        }
	}
	
	public void toggleZoom(){
		zoomOut = !zoomOut;		
	}

	public void actionPerformed(ActionEvent e) {	
		if (player_state < 0 || player_state == 5){
			player_state = 6;
		}else{
			player_state = 5;
		}
		this.repaint();
	}
	
	private void init() throws Exception{
		images = new BufferedImage[IMAGE_COUNT];
		images[0] = ImageIO.read(new java.io.File("resources/hedge.png"));
		images[1] = ImageIO.read(new java.io.File("resources/sword.png"));		
		images[2] = ImageIO.read(new java.io.File("resources/help.png"));
		images[3] = ImageIO.read(new java.io.File("resources/bomb.png"));
		images[4] = ImageIO.read(new java.io.File("resources/banana.png"));
		images[5] = ImageIO.read(new java.io.File("resources/martinRight.png"));
		images[6] = ImageIO.read(new java.io.File("resources/martinLeft.png"));
		images[7] = ImageIO.read(new java.io.File("resources/gargoyle.png"));
		images[8] = ImageIO.read(new java.io.File("resources/dziewczyna.png"));
	}
}