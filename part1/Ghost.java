package part1;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.builder.HashCodeBuilder;

public class Ghost {
	
//	double heuristic;
	MazeNode pacman;
	MazeNode ghost;
	MazeNode ghost2;
	double distancesofar=0;
	Ghost[] neighbour;
	Heuristic heuristic;
	boolean empty=false;
	boolean danger=false;
//	True means from left to right, false from right to left
	boolean ghostdirection=true;
	public Ghost(MazeNode pacman, MazeNode Ghost,MazeNode Ghost2,double distancesofar){
		this.pacman=new MazeNode(pacman);
		this.ghost=new MazeNode(Ghost);
		this.ghost2=new MazeNode(Ghost2);
		this.distancesofar=distancesofar;
		this.empty=pacman.empty;
	}
	public Ghost(Ghost copy){
		this.pacman=new MazeNode(copy.pacman);
		this.distancesofar=copy.distancesofar;
		this.ghost=copy.ghost;
		this.ghost2=copy.ghost2;
		this.empty=copy.empty;
	}
	public boolean lose(){
		if(pacman.equals(ghost)||pacman.equals(ghost2))
			return true;
		return false;
	}
	public double calculateheuristic(Maze maze){	
			return manhattendistance(pacman, maze.end)+distancesofar;

	}
	
	public double manhattendistance(MazeNode pacman,MazeNode dot){
		return Math.abs(dot.i-pacman.i)+Math.abs(dot.j-pacman.j);
	}
	
	
//   To be decided later	
	public Ghost [] setneighbour(Maze maze){
		neighbour=new Ghost[4];
		int i=pacman.i;
		int count=0;
		int j=pacman.j;
//		ghost=maze.ghostposition;
//		
		MazeNode temp=ghost.move(maze);
		MazeNode temp2=ghost.move2(maze);
		neighbour[0]=new Ghost(maze.mazenode[i+1][j],temp,temp2,pacman.distancesofar);
		if(neighbour[0].lose()||(temp==pacman&&ghost==maze.mazenode[i+1][j])||(temp2==pacman&&ghost2==maze.mazenode[i+1][j]))
			neighbour[0].danger=true;
				
		neighbour[1]=new Ghost(maze.mazenode[i-1][j],temp,temp2,pacman.distancesofar);
		if(neighbour[1].lose()||(temp==pacman&&ghost==maze.mazenode[i-1][j])||(temp2==pacman&&ghost2==maze.mazenode[i+1][j]))
			neighbour[1].danger=true;	
		neighbour[2]=new Ghost(maze.mazenode[i][j-1],temp,temp2,pacman.distancesofar);
		if(neighbour[2].lose()||(temp==pacman&&ghost==maze.mazenode[i][j+1])||(temp2==pacman&&ghost2==maze.mazenode[i+1][j]))
			neighbour[2].danger=true;
		neighbour[3]=new Ghost(maze.mazenode[i][j+1],temp,temp2,pacman.distancesofar);
		if(neighbour[3].lose()||(temp==pacman&&ghost==maze.mazenode[i][j-1])||(temp2==pacman&&ghost2==maze.mazenode[i+1][j]))
			neighbour[3].danger=true;		
		return neighbour;
	}

	
	@Override
	public String toString(){
		return String.valueOf(pacman.i)+"\t"+String.valueOf(pacman.j)+"\t"+ghost.i+"\t"+ghost.j;
		
		
	}
	@Override
	public boolean equals(Object obj){
//		return true;
		if(this==obj)
			return true;
		if(!(obj instanceof Ghost))
			return false;
		Ghost sb=(Ghost)obj;
		if(sb.pacman.equals(this.pacman)&&sb.ghost.equals(this.ghost)&&sb.ghost2.equals(this.ghost2))
			return true;
		return false;
		
	}
	  @Override
	    public int hashCode() {
		  final int prime = 31;
			int result = 1;
			result = prime * result + pacman.hashCode();
			result = prime * result + ghost.hashCode();
			result = prime * result + ghost2.hashCode();
			return result;
//	      return result;
	    }
	
}

