package maaaze;

import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class Main {
	
	static JFrame frame;
	static DrawPanel panel;
	static boolean run = true;
	static int score = 0;
	static Maze m;
	static ArrayList<Cell> path = new ArrayList<>();
	static int size = 15;

	public static void show(){
		frame = new JFrame();
		panel = new DrawPanel();
		panel.setBackground(Color.black);
		panel.addKeyListener(panel);
		m = new Maze(20 , 20);
		m.generateRecursive();
		frame.setSize(690,710);
		frame.setUndecorated(false);
		frame.setResizable(false);
		panel.setFocusable(true);
		frame.add(panel);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	static class DrawPanel extends JPanel implements KeyListener{
		public void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.setColor(new Color(0x8739ed));
            boolean[][] maze = m.getMaze();
            for(int i = 0; i < maze.length+2; i++){
            	for(int j = 0; j < maze[0].length+2; j++){
            		if(i == 0 || j == 0 || i == maze.length+1 || j == maze[0].length+1){
            			g.fillRect(j*size+size, i*size+size, size, size);
            		} else if(maze[i-1][j-1]){
            			g.fillRect(j*size+size, i*size+size, size, size);
            		}
            	}
            }
            g.setColor(new Color(0xb6a3d9));
            path = m.getPath();
            for(Cell c : path){
            	g.fillRect(c.column * size + 2*size, c.row * size + 2*size, size, size);
            }
        }
      	public void keyPressed(KeyEvent e){
      		if(e.getKeyCode() == KeyEvent.VK_SPACE){
      			m.solve();
      			panel.repaint();
      		} else if(e.getKeyCode() == KeyEvent.VK_R){
      			m.reset();
      			m.generateRecursive();
      			panel.repaint();
      		}
      	}
	    public void keyReleased(KeyEvent e){}
	    public void keyTyped(KeyEvent e){}
	}
	public static void main(String args[]){
		show();
	}
}
