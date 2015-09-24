package part1;

import java.util.Comparator;

public class Newcomparator implements Comparator<MazeNode>{
	Maze maze;
	public Newcomparator(Maze maze){
		this.maze=maze;
	}

	
//	This part is for the original Astar search case
	@Override
	public int compare(MazeNode A,MazeNode B){
		if(A.calculateNewheuristic(maze)>B.calculateNewheuristic(maze))
			return 1;
		else if(A.calculateNewheuristic(maze)<B.calculateNewheuristic(maze))
			return -1;
		else
			return 0;
	}
	
	
//	This part is for different heuristic
//	@Override
//	public int compare(MazeNode A,MazeNode B){
//		if(A.calculateNewheuristic(maze)+A.costofdirection>B.calculateNewheuristic(maze)+B.costofdirection)
//			return 1;
//		else if(A.calculateNewheuristic(maze)+A.costofdirection<B.calculateNewheuristic(maze)+B.costofdirection)
//			return -1;
//		else
//			return 0;
//	}
}
