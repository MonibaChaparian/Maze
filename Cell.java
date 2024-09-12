package maaaze;

import java.util.*;
public class Cell{
	
	ArrayList<Cell> path = new ArrayList<>();
	
	int row;
	int column;
	
	public Cell(int r, int c){
		this.row = r;
		this.column = c;
	}
}
