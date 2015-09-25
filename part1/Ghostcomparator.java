package part1;

import java.util.Comparator;

public class Ghostcomparator implements Comparator<Ghost>{
	Maze maze;
	public Ghostcomparator(Maze maze){
		this.maze=maze;
	}
	@Override
	public int compare (Ghost A, Ghost B){
		if(A.calculateheuristic(maze)>B.calculateheuristic(maze))
			return 1;
		else if(A.calculateheuristic(maze)<B.calculateheuristic(maze))
			return -1;
		else
			return 0;
		
	}
}
