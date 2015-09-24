package part1;

import java.util.Comparator;

public class MazeNodeComparator implements Comparator<MazeNode> {
	Maze maze;
	public MazeNodeComparator(Maze maze){
		this.maze=maze;
	}

	@Override
	public int compare(MazeNode A,MazeNode B){
		if(A.calculateheuristic(maze)>B.calculateheuristic(maze))
			return 1;
		else if(A.calculateheuristic(maze)<B.calculateheuristic(maze))
			return -1;
		else
			return 0;
	}
}
