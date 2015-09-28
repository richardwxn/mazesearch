package part1;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.builder.HashCodeBuilder;

public class State {
	
//	double heuristic;
	MazeNode pacman;
	ArrayList<MazeNode> dotposition;
	double distancesofar=0;
	State[] neighbour;
	MazeNode lostdot;
	Heuristic heuristic;
	boolean empty=false;
	public State(MazeNode pacman, ArrayList<MazeNode> dotposition,double distancesofar){
		this.pacman=new MazeNode(pacman);
		this.dotposition=new ArrayList<MazeNode>(dotposition);
		this.distancesofar=distancesofar;
		this.empty=pacman.empty;
	}
	public State(State copy){
		this.pacman=new MazeNode(copy.pacman);
		this.dotposition=new ArrayList<MazeNode>(copy.dotposition);
		this.distancesofar=copy.distancesofar;
		this.empty=copy.pacman.empty;
	}

	public double calculateheuristic(Maze maze){	
		MazeNode temp=new MazeNode(pacman.i,pacman.j,true);
		double min=Integer.MAX_VALUE;
		double max=Integer.MIN_VALUE;
		double mintoend=Integer.MAX_VALUE;
		double sum=0;
		for(MazeNode dot:this.dotposition){
			min=Math.min(manhattendistance(pacman,dot), min);
		}
		for(MazeNode dot:this.dotposition){
			if(manhattendistance(pacman,dot)>max)
				temp=new MazeNode(dot);
			max=Math.max(manhattendistance(pacman,dot), max);
		}
		
		for(MazeNode dot:this.dotposition){
			mintoend=Math.min(manhattendistance(pacman,maze.end), min);
			sum+=manhattendistance(temp, dot);
		}
		
//		return manhattendistance(temp, maze.end)+max+dotposition.size()+distancesofar;
		return 1.1*sum+dotposition.size()+distancesofar;
	}
	
	public double manhattendistance(MazeNode pacman,MazeNode dot){
		return Math.abs(dot.i-pacman.i)+Math.abs(dot.j-pacman.j);
	}
	
	
//   To be decided later	
	public State [] setneighbour(Maze maze){
		neighbour=new State[4];
		int i=pacman.i;
		int count=0;
		int j=pacman.j;
//		
		neighbour[0]=new State(maze.mazenode[i+1][j],this.dotposition,pacman.distancesofar);
		if(maze.mazenode[i+1][j].isdot()){
//			dotposition.remove(new MazeNode(i+1,j,true));
			neighbour[0].dotposition.remove(maze.mazenode[i+1][j]);
//				System.out.println(neighbour[0].dotposition.size());
		}
//		MazeNode next=new MazeNode(i+1,j,maze.mazenode[i+1][j].empty)
		
		
//	
		neighbour[1]=new State(maze.mazenode[i-1][j],dotposition,pacman.distancesofar);
		if(maze.mazenode[i-1][j].isdot()){
//			dotposition.remove(new MazeNode(i-1,j,true));
			neighbour[1].dotposition.remove(maze.mazenode[i-1][j]);
				
		}
		
//		
		neighbour[2]=new State(maze.mazenode[i][j+1],dotposition,pacman.distancesofar);
		if(maze.mazenode[i][j+1].isdot()){
//			dotposition.remove(new MazeNode(i,j+1,true));
			neighbour[2].dotposition.remove(maze.mazenode[i][j+1]);
//			System.out.println(neighbour[2].dotposition.size());
		}
		
//		
		neighbour[3]=new State(maze.mazenode[i][j-1],dotposition,pacman.distancesofar);
		if(maze.mazenode[i][j-1].isdot()){
//			dotposition.remove(new MazeNode(i,j-1,true));	
			neighbour[3].dotposition.remove(maze.mazenode[i][j-1]);
//				System.out.println(neighbour[3].dotposition.size());
		}
		
			
		return neighbour;
	}


//	@Override
//	public int hashCode() {
////		return 31;
//		final int prime = 31;
//		int result = 1;
//		result = prime * result
//				+ ((dotposition == null) ? 0 : dotposition.hashCode());
//		result = prime * result + ((pacman == null) ? 0 : pacman.hashCode());
//		return result;
//	}


//	@Override
//	public boolean equals(Object obj) {
//		if (this == obj)
//			return true;
//		if (obj == null)
//			return false;
//		if (getClass() != obj.getClass())
//			return false;
//		State other = (State) obj;
//		if (dotposition == null) {
//			if (other.dotposition != null)
//				return false;
//		} else if (!dotposition.equals(other.dotposition))
//			return false;
//		if (pacman == null) {
//			if (other.pacman != null)
//				return false;
//		} else if (!pacman.equals(other.pacman))
//			return false;
//		return true;
//	}
	
	@Override
	public String toString(){
		return String.valueOf(pacman.i)+"\t"+String.valueOf(pacman.j)+"\t"+dotposition.size();
		
		
	}
	@Override
	public boolean equals(Object obj){
//		return true;
		if(this==obj)
			return true;
		if(!(obj instanceof State))
			return false;
		State sb=(State)obj;
//		if(sb.pacman.equals(this.pacman)&&sb.dotposition.containsAll(this.dotposition)&&this.dotposition.containsAll(sb.dotposition))
			
		if(sb.pacman.equals(this.pacman)&&sb.dotposition.size()==this.dotposition.size())
			return true;
		return false;
		
	}
	  @Override
	    public int hashCode() {
//		  final int prime = 31;
//			int result = 1;
//			result = prime * result + pacman.hashCode();
//			result = prime * result + dotposition.hashcode();
			return pacman.hashCode();
//	      return result;
	    }
	
}
