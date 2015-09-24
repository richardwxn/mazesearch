package part1;

import java.util.Comparator;

public class StateComparator implements Comparator<State> {
	Maze maze;
	public StateComparator(Maze maze){
		this.maze=maze;
	}

	@Override
	public int compare(State A,State B){
		if(A.calculateheuristic(maze)>B.calculateheuristic(maze))
			return 1;
		else if(A.calculateheuristic(maze)<B.calculateheuristic(maze))
			return -1;
		else
			return 0;
	}
}
