package part1;

import java.io.IOException;

public class MazeNode{
	int i;
	int j;
	boolean empty;
	boolean dot=false;
// for part 2
//	True means north and south, false means west and east
	boolean direction=false;
//	public enum direction{
//		NORTH,SOUTH,WEST,EAST
//	}
//	double heuristic;
	double distancesofar=0;
	double costofdirection=0;
	MazeNode [] neighbour;
	Heuristic heuristic;
	public MazeNode(int xindex, int yindex,boolean type){
		this.i=xindex;
		this.j=yindex;
		this.empty=type;
	}
	public MazeNode(MazeNode node){
		this.i=node.i;
		this.j=node.j;
		this.empty=node.empty;
		this.dot=node.dot;
	}
	public boolean gettype(){
		return empty;
	}
	public boolean isdot(){
		return dot;
	}
	
//	For the part that has ghosts
	public MazeNode move(Maze maze){
		if(maze.mazenode[i][j+1].empty)
			return new MazeNode(i,j+1,true);
		else
			return new MazeNode(i,j-1,true);
	}
	public double calculateheuristic(Maze maze){	
		return Math.abs(maze.end.i-i)+Math.abs(maze.end.j-j);
	}
	
	public double calculateNewheuristic(Maze maze){
//		need to add the previous solution when calculate
		return calculateheuristic(maze)+distancesofar;	
	}
//	

//   To be decided later	
	public MazeNode [] setneighbour(Maze maze){
		neighbour=new MazeNode[4];
		neighbour[0]=maze.mazenode[i+1][j];
		neighbour[1]=maze.mazenode[i-1][j];
		neighbour[2]=maze.mazenode[i][j-1];
		neighbour[3]=maze.mazenode[i][j+1];		
		
//		North, south side is true for direction/ East and west direction is set to false
//		neighbour[0]=new MazeNode(maze.mazenode[i+1][j]);
		neighbour[0].direction=true;
//		neighbour[1]=new MazeNode(maze.mazenode[i-1][j]);
		neighbour[1].direction=true;
//		neighbour[2]=new MazeNode(maze.mazenode[i][j+1]);
		neighbour[2].direction=false;
//		neighbour[3]=new MazeNode(maze.mazenode[i][j-1]);	
		neighbour[3].direction=false;
		for(int i=0;i<4;i++)
			neighbour[i].costofdirection=(this.direction!=neighbour[i].direction?3:1);
//			neighbour[i].costofdirection=0;
		return neighbour;
	}
	
	@Override
	public boolean equals(Object obj){
		if(this==obj)
			return true;
		if(!(obj instanceof MazeNode))
			return false;
		MazeNode sb=(MazeNode)obj;
		if(sb.i==this.i&&sb.j==this.j)
			return true;
		return false;
		
	}
	
	public String toString(){
		return i+"\t"+j;	
		
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + i;
		result = prime * result + j;
		return result;
	}
//	@Override
//	public boolean equals(Object obj) {
//		if (this == obj)
//			return true;
//		if (obj == null)
//			return false;
//		if (getClass() != obj.getClass())
//			return false;
//		MazeNode other = (MazeNode) obj;
//		if (i != other.i)
//			return false;
//		if (j != other.j)
//			return false;
//		return true;
//	}
	
}
