package part1;

public class Heuristic {
	double heuristic;
	public Heuristic(Maze maze,MazeNode node){
		heuristic=Math.abs(maze.end.i-node.i)+Math.abs(maze.end.j-node.j);		
	}
}
