package maze; 

import java.util.Random;
import java.util.Collections;
import java.util.Arrays;
import maze.MazeCell;
 
/*
 * recursive backtracking algorithm
 * shamelessly borrowed from the ruby at
 * http://weblog.jamisbuck.org/2010/12/27/maze-generation-recursive-backtracking
 */
public class MazeGenerator {
	private final int x;
	private final int y;
	private final int[][] maze;
        private MazeCell[][] mazeModel; 
	private static final Random rand = new Random();
 
	public MazeGenerator(MazeCell[][] mazeModel) {
		this.x = mazeModel.length;
		this.y = mazeModel.length;
		maze = new int[this.x][this.y];
                this.mazeModel = mazeModel;  
		generateMaze(0, 0);
	}
 
	public void display() {
		for (int i = 0; i < y; i++) {
			// draw the north edge
			for (int j = 0; j < x; j++) {
				System.out.print((maze[j][i] & 1) == 0 ? "+---" : "+   ");
                                if(maze[j][i]%2 ==0) {
                                 mazeModel[i][j] = MazeCell.NORTHW; 
                                  }
			}
			System.out.println("+");
			// draw the west edge
			for (int j = 0; j < x; j++) {
				System.out.print((maze[j][i] & 8) == 0 ? "|   " : "    ");
                             if((maze[j][i]&8) ==0 ) {
                                mazeModel[i][j] = (mazeModel[i][j]==MazeCell.NORTHW) ? MazeCell.NORTWESTW :MazeCell.WESTW; 
                                 }
			}
			System.out.println("|");
		}
		// draw the bottom line
		for (int j = 0; j < x; j++) {
			mazeModel[x-1][j].southW = true; 
		}
		System.out.println("+");
	}
 
	private void generateMaze(int cx, int cy) {
		MazeGenerator.DIR[] dirs = MazeGenerator.DIR.values();
                
		Collections.shuffle(Arrays.asList(dirs));
		for (MazeGenerator.DIR dir : dirs) {
			int nx = cx + dir.dx;
			int ny = cy + dir.dy;
			if (between(nx, x) && between(ny, y)
					&& (maze[nx][ny] == 0)) {
				maze[cx][cy] = dir.bit;
				maze[nx][ny] = dir.opposite.bit;
				generateMaze(nx, ny);
			}
		}
	}
 
	private static boolean between(int v, int upper) {
		return (v >= 0) && (v < upper);
	}
 
	private enum DIR {
		N(1, 0, -1), S(2, 0, 1), E(4, 1, 0), W(8, -1, 0);
		private final int bit;
		private final int dx;
		private final int dy;
		private MazeGenerator.DIR opposite;
 
		// use the static initializer to resolve forward references
		static {
			N.opposite = S;
			S.opposite = N;
			E.opposite = W;
			W.opposite = E;
		}
 
		private DIR(int bit, int dx, int dy) {
			this.bit = bit;
			this.dx = dx;
			this.dy = dy;
		}
	};
 
	/*public static void main(String[] args) {
		int x = args.length >= 1 ? (Integer.parseInt(args[0])) : 8;
		int y = args.length == 2 ? (Integer.parseInt(args[1])) : 8;
		MazeGenerator maze = new MazeGenerator(mazeModel);
		maze.display();
	}*/
 
}