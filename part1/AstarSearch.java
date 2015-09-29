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
//	Map<MazeNode,List<MazeNode>> pathto;
	double distance=0;
	Map<MazeNode,MazeNode> predecessor;
	public AstarSearch(Maze maze){
		Comparator<MazeNode> comparator=new Newcomparator(maze);
		frontier=new PriorityQueue<MazeNode>(maze.row*maze.col,comparator);	
//		pathto=new HashMap<MazeNode,List<MazeNode>>();
		visited=new HashSet<MazeNode>();
		predecessor=new HashMap<MazeNode,MazeNode>();
	}
	
	public MazeNode Search(Maze maze){	
		maze.start.distancesofar=0;
		frontier.add(maze.start);
		visited.add(maze.start);
//		pathto.put(maze.start, new ArrayList<MazeNode>(Arrays.asList(maze.start)));
		int nodesnumber=0;
		while(!frontier.isEmpty()){
//			Make use of the priorityqueue
			MazeNode cur=frontier.poll();
			nodesnumber++;
			visited.add(cur);
			distance++;
			for(MazeNode node:cur.setneighbour(maze)){
				if(node.empty&&!visited.contains(node)){
					
//					This part changes for different cost function
					node.distancesofar=cur.distancesofar+node.costofdirection;
					frontier.add(node);
					predecessor.put(node, cur);
					if(node==maze.end){
//						System.out.println("Expanded Nodes Number:"+nodesnumber);
						return node;
					}
						
				}
			}
			
		}
//		System.out.println("hehe");
		return null;
	}
	
	public int printsolution(Maze maze, MazeNode result){
//		File file=new File()
		int distance = 0;
		Maze copy=new Maze(maze);
		while(predecessor.containsKey(result)) {
			distance+=1;
			result = predecessor.get(result);
			
//			distance+=result.costofdirection;
			
			int index=(maze.col+1)*result.i+result.j;
			if(!result.equals(maze.start))			
				copy.builder.setCharAt(index, '.');

		}
//		System.out.println(copy.builder.toString());
		return distance;
	}
	

}
