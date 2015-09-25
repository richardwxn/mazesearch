package part1;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.builder.HashCodeBuilder;

public class Ghost {
	
//	double heuristic;
	MazeNode pacman;
	MazeNode ghost;
	double distancesofar=0;
	Ghost[] neighbour;
	Heuristic heuristic;
	boolean empty=false;
	public Ghost(MazeNode pacman, MazeNode Ghost,double distancesofar){
		this.pacman=pacman;
		this.ghost=Ghost;
		this.distancesofar=distancesofar;
		this.empty=pacman.empty;
	}
	public Ghost(Ghost copy){
		this.pacman=copy.pacman;
		this.distancesofar=copy.distancesofar;
		this.ghost=copy.ghost;
		this.empty=copy.empty;
	}
	public boolean lose(){
		if(pacman.equals(ghost))
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
		MazeNode temp=new MazeNode(ghost.move(maze));
		neighbour[0]=new Ghost(maze.mazenode[i+1][j],temp,pacman.distancesofar);
		if(neighbour[0].lose()||(temp==pacman&&ghost==maze.mazenode[i+1][j]))
			neighbour[0].empty=false;
				
		neighbour[1]=new Ghost(maze.mazenode[i-1][j],temp,pacman.distancesofar);
		if(neighbour[1].lose()||(temp==pacman&&ghost==maze.mazenode[i-1][j]))
			neighbour[1].empty=false;	
		neighbour[2]=new Ghost(maze.mazenode[i][j+1],temp,pacman.distancesofar);
		if(neighbour[2].lose()||(temp==pacman&&ghost==maze.mazenode[i][j+1]))
			neighbour[2].empty=false;
		neighbour[3]=new Ghost(maze.mazenode[i][j+1],temp,pacman.distancesofar);
		if(neighbour[3].lose()||(temp==pacman&&ghost==maze.mazenode[i][j+1]))
			neighbour[3].empty=false;		
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
		if(sb.pacman.equals(this.pacman)&&sb.ghost.equals(this.ghost))
			return true;
		return false;
		
	}
	  @Override
	    public int hashCode() {
		  final int prime = 31;
			int result = 1;
			result = prime * result + pacman.hashCode();
			result = prime * result + ghost.hashCode();
			return result;
//	      return result;
	    }
	
}

