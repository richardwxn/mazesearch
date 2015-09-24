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

public class AstarSearch {
	Queue<MazeNode> frontier;
	Set<MazeNode> visited;
	Map<MazeNode,List<MazeNode>> pathto;
	double distance=0;
	public AstarSearch(Maze maze){
		Comparator<MazeNode> comparator=new Newcomparator(maze);
		frontier=new PriorityQueue<MazeNode>(maze.row*maze.col,comparator);	
		pathto=new HashMap<MazeNode,List<MazeNode>>();
		visited=new HashSet<MazeNode>();
	}
	
	public MazeNode Search(Maze maze){	
		maze.start.distancesofar=0;
		frontier.add(maze.start);
		visited.add(maze.start);
		pathto.put(maze.start, new ArrayList<MazeNode>(Arrays.asList(maze.start)));
		int nodesnumber=1;
		while(!frontier.isEmpty()){
//			Make use of the priorityqueue
			MazeNode cur=frontier.poll();
			List<MazeNode> prev=new ArrayList<MazeNode>(pathto.get(cur));
			visited.add(cur);
			System.out.println(cur);
			distance++;
			for(MazeNode node:cur.setneighbour(maze)){
				if(node.empty&&!visited.contains(node)){
//					System.out.println(nodesnumber);
					nodesnumber++;
//					This part changes for different cost function
					node.distancesofar=cur.distancesofar+node.costofdirection;
					frontier.add(node);
					prev.add(node);
					pathto.put(node, prev);
					if(node==maze.end){
						System.out.println(nodesnumber);
						return node;
					}
						
				}
			}
			
		}
		return null;
	}
	
	public double getdistance(Maze maze,MazeNode node){
		return pathto.get(node).size();			
	}
}
