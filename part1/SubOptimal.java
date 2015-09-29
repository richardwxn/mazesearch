package part1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

public class SubOptimal {
	Queue<State> frontier;
	Set<State> visited;
	Map<State,State> predecessor;
	LinkedHashSet<Integer> dotindex=new LinkedHashSet<Integer>();
	double distance=0;
	public SubOptimal(Maze maze){
		Comparator<State> comparator=new StateComparator(maze);
		frontier=new PriorityQueue<State>(maze.row*maze.col,comparator);	
//		pathto=new HashMap<State,List<State>>();
		predecessor=new HashMap<State,State>();
		visited=new HashSet<State>();
	}
	
	public State Search(Maze maze){	
		maze.start.distancesofar=1;
		State start=new State(maze.start,maze.dot,1);		
		frontier.add(start);
		visited.add(start);
		System.out.println(start);
		int nodesnumber=0;
		State cur=start;
		AstarSearch astar=new AstarSearch(maze);
		int j=0;
		while(cur.dotposition.size()!=0){
			double min=0;
			Maze copy=new Maze(maze);
			MazeNode temptail=null;
			State node;
			for(int i=0;i<cur.dotposition.size();i++){
			copy.end=cur.dotposition.get(i);
			copy.start=cur.pacman;
			MazeNode result=astar.Search(copy);
			if(astar.printsolution(copy, result)<min){
				temptail=cur.dotposition.get(i);
				min=astar.printsolution(copy, result);
			}
			
			}
			cur.dotposition.remove(temptail);
			node=new State(copy.end,cur.dotposition,nodesnumber);
			nodesnumber++;
			visited.add(cur);
			if(maze.mazenode[cur.pacman.i][cur.pacman.j].isdot())
				dotindex.add((maze.col+1)*cur.pacman.i+cur.pacman.j);

					if(node.dotposition.size()==0){
						dotindex.add((maze.col+1)*node.pacman.i+node.pacman.j);
						System.out.println("Expanded Nodes Number:"+nodesnumber);
						return node;
					}
				
			}
			
		
		if(dotindex.size()==start.dotposition.size()){
			System.out.println("Expanded Nodes Number:"+nodesnumber);
			return cur;
		}
		else
			return null;
		}
	
	public int printsolution(Maze maze, State result){
//		File file=new File()
		int distance = 0;
		Maze copy=new Maze(maze);
		int i=0;
		char s;
		while(predecessor.containsKey(result)) {
			distance++;		
			result = predecessor.get(result);
		}
			Iterator<Integer> itr = dotindex.iterator();
			while(itr.hasNext()){
				if(0<=i&&i<=9)
					s=(char)(48+i);
					
				else if(9<i&&i<36){
					s=(char)(97+i-10);			
				}
				else
					s=(char)(65+i-36);
				copy.builder.setCharAt(itr.next(), s);
				i++;
			}
	
		System.out.println(copy.builder.toString());
		return distance;
	}
}
