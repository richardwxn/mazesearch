package part1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

public class AstarDots {
	Queue<State> frontier;
	Set<State> visited;
	Map<State,List<State>> pathto;
	double distance=0;
	public AstarDots(Maze maze){
		Comparator<State> comparator=new StateComparator(maze);
		frontier=new PriorityQueue<State>(maze.row*maze.col,comparator);	
		pathto=new HashMap<State,List<State>>();
		visited=new HashSet<State>();
	}
	
	public State Search(Maze maze){	
		maze.start.distancesofar=1;
		State start=new State(maze.start,maze.dot,1);		
		frontier.add(start);
		visited.add(start);
		System.out.println(start);
		pathto.put(start, new ArrayList<State>(Arrays.asList(start)));
		int nodesnumber=0;
		while(!frontier.isEmpty()){
//			Make use of the priorityqueue
			State cur=frontier.poll();
//			System.out.println(cur);
//			List<State> prev=new ArrayList<State>(pathto.get(cur));

			distance++;
			for(State node:cur.setneighbour(maze)){
				State hehe=new State(node);
				if(hehe.empty&&!frontier.contains(hehe)){
//					if(node.dotposition.size()!=14)
					System.out.println(nodesnumber);
//					System.out.println(node.dotposition.size());
					nodesnumber++;	
					hehe.distancesofar=cur.distancesofar+1;
					frontier.add(hehe);
//					prev.add(node);
//					pathto.put(node, prev);
					if(hehe.dotposition.size()==0){
						return hehe;
					}
				}
			}
			
		}
		for(State nima:visited){
			System.out.println(nima.pacman.i+"\t"+nima.pacman.j+"\t"+nima.dotposition.size());
		}
		System.out.println("hehe");
		return null;
	}
	
	public double getdistance(Maze maze,State node){
		return pathto.get(node).size();			
	}
}
