package maaaze;

import java.util.*;


public class Maze {
	
	private boolean[][] maze; 
	private boolean[][] visited; 
	private int w; 
	private int h; 
	private int visitedCount; 
	private ArrayList<Cell> path = new ArrayList<>(); 
	
	public Maze(int h, int w){
		this.w = w;
		this.h = h;
		visitedCount = w*h;
		maze = new boolean[h*2+1][w*2+1];
		visited = new boolean[h*2+1][w*2+1];
		
		for(int j = 1; j < w*2; j+=2){
			for(int i = 0; i < h*2+1; i++){
				maze[i][j] = true;
			}
		}
		for(int i = 1; i < h*2; i+=2){
			for(int j = 0; j < w*2+1; j++){
				maze[i][j] = true;
			}
		}
	}
	//Recursive backtracking for generating the maze
	public void generateRecursive(){
		ArrayDeque<Cell> q = new ArrayDeque<>();
		Cell c = new Cell(0, 0);
		q.push(c);
		visited[0][0] = true;
		int visits = 0;
		
		while(visits < visitedCount || !q.isEmpty()){
			
		while((c.row + 2 < 2*h+1    &&  !visited[c.row+2][c.column]) 
			  || (c.row - 2 >= 0    &&  !visited[c.row-2][c.column]) 
			  || (c.column + 2 < 2*w+1 &&  !visited[c.row][c.column+2]) 
		      || (c.column - 2 >= 0    &&  !visited[c.row][c.column-2])){
				
			    int rand = (int)(Math.random() * 4 + 1);
				// up
				if(rand == 1 && c.row - 2 >= 0 && !visited[c.row-2][c.column]){
					maze[c.row-1][c.column] = false;
					Cell x = new Cell(c.row-2, c.column);
					q.add(x);
					visited[c.row-2][c.column] = true;
					visits++;
					c = x;
				}
				// down
				if(rand == 2 && c.row + 2 < 2*h+1 && !visited[c.row+2][c.column]){
					maze[c.row+1][c.column] = false;
					Cell x = new Cell(c.row+2, c.column);
					q.add(x);
					visited[c.row+2][c.column] = true;
					visits++;
					c = x;
				}
				// left
				if(rand == 3 && c.column - 2 >= 0 && !visited[c.row][c.column-2]){
					maze[c.row][c.column-1] = false;
					Cell x = new Cell(c.row, c.column-2);
					q.add(x);
					visited[c.row][c.column-2] = true;
					visits++;
					c = x;
				}
				// right
				if(rand == 4 && c.column + 2 < 2*w+1 && !visited[c.row][c.column+2]){
					maze[c.row][c.column+1] = false;
					Cell x = new Cell(c.row, c.column+2);
					q.add(x);
					visited[c.row][c.column+2] = true;
					visits++;
					c = x;
				}
			}
			if(!q.isEmpty()){
				c = q.pop();
			}
		}
	}
	//BFS ALGORITHM 
	public void solve(){
		int[] yMove = {-2, 2};
		int[] xMove = {-2, 2};
		ArrayDeque<Cell> q = new ArrayDeque<>();
		visited = new boolean[h*2+1][w*2+1];
		visited[0][0] = true;
		Cell start = new Cell(0,0);
		start.path.add(start);
		q.add(start);
		while(!q.isEmpty()){
			Cell c = q.poll();
			if(c.row == 2*h && c.column == 2*w){
				path = c.path;
				break;
			}
			for(int i = 0; i < 2; i++){
				if(yMove[i] + c.row >= 0 && 
				   yMove[i] + c.row < 2*h+1 && 
				   !maze[c.row+(yMove[i]/2)][c.column] && 
				   !visited[c.row+yMove[i]][c.column]){
					
					visited[c.row+yMove[i]][c.column] = true;
					Cell x = new Cell(yMove[i] + c.row, c.column);
					x.path = new ArrayList<Cell>(c.path);
					x.path.add(new Cell(c.row+(yMove[i]/2), c.column));
					x.path.add(x);
					q.add(x);
				}
			}
			for(int i = 0; i < 2; i++){
				if(xMove[i] + c.column >= 0 && 
				   xMove[i] + c.column < 2*w+1 && 
				   !maze[c.row][c.column+(xMove[i]/2)] && 
				   !visited[c.row][c.column+xMove[i]]){
					
					visited[c.row][c.column+xMove[i]] = true;
					Cell x = new Cell(c.row, xMove[i] + c.column);
					x.path = new ArrayList<Cell>(c.path);
					x.path.add(new Cell(c.row, c.column+(xMove[i]/2)));
					x.path.add(x);
					q.add(x);
				}
			}
		}
	}
	
	public void reset(){
		maze = new boolean[h*2+1][w*2+1];
		visited = new boolean[h*2+1][w*2+1];
		path = new ArrayList<>();
		for(int j = 1; j < w*2; j+=2){
			for(int i = 0; i < h*2+1; i++){
				maze[i][j] = true;
			}
		}
		for(int i = 1; i < h*2; i+=2){
			for(int j = 0; j < w*2+1; j++){
				maze[i][j] = true;
			}
		}
	}
	public boolean[][] getMaze(){
		return maze;
	}
	public ArrayList<Cell> getPath(){
		return path;
	}
	public int getHeight(){
		return h;
	}
	public int getWidth(){
		return w;
	}
}