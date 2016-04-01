package ie.gmit.sw.ai;

public class Maze {
	//private char[][] maze; //change the array to a node array 
	//making it possible to alter the state of the node. Twill be important to 
	//use when writing the enemy algorithm
	
	private Node[][] maze; 
	public Maze(int rows, int cols){
		maze = new Node[rows][cols];
		init();
		buildMaze();
		
		int featureNumber = (int)((rows * cols) * 0.01);
		addFeature('W', 'X', featureNumber);
		addFeature('?', 'X', featureNumber);
		addFeature('B', 'X', featureNumber);
		addFeature('H', 'X', featureNumber);
		addFeature('A', 'X', featureNumber);
		
	}
	
	private void init(){
		for (int row = 0; row < maze.length; row++){
			for (int col = 0; col < maze[row].length; col++){
				maze[row][col] = new Node(row,col);
				maze[row][col].setFeature('X'); 
			}
		}
	}
	
	private void addFeature(char feature, char replace, int number){
		int counter = 0;
		while (counter < feature){
			int row = (int) (maze.length * Math.random());
			int col = (int) (maze[0].length * Math.random());
			
			if (maze[row][col].getFeature() == replace){
				maze[row][col].setFeature(feature); 
				counter++;
			}
		}
	}
	
	
	private void buildMaze(){
		for (int row = 0; row < maze.length; row++){
			for (int col = 0; col < maze[row].length - 1; col++){
				int num = (int) (Math.random() * 10);
				if (num >= 5 && col + 1 < maze[row].length - 1){
					maze[row][col + 1].setFeature(' '); 
					continue;
				}
				if (row + 1 < maze.length){ //Check south
					maze[row + 1][col].setFeature(' ');
				}				
			}
		}	
	}
	
	public Node[][] getMaze(){
		return this.maze;
	}
	
	public String toString(){
		StringBuffer sb = new StringBuffer();
		for (int row = 0; row < maze.length; row++){
			for (int col = 0; col < maze[row].length; col++){
				sb.append(maze[row][col]);
				if (col < maze[row].length - 1) sb.append(",");
			}
			sb.append("\n");
		}
		return sb.toString();
	}
}