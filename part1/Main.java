package part1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {

	Set<MazeNode> visited;
	static Map<MazeNode,List<MazeNode>> pathto;
	Map<MazeNode, MazeNode> predecessor;
	
	public Main() throws IOException{
//	input
		visited=new HashSet<MazeNode>();
		pathto=new HashMap<MazeNode,List<MazeNode>>();
		predecessor=new HashMap<MazeNode, MazeNode>();
	}

	public MazeNode BFSsearch(Maze maze){
		Maze copy=new Maze(maze);
		int nodesnumber=0;
//		Put the first start point into visted
		visited.add(maze.start);	
		List<MazeNode> frontier=new LinkedList<MazeNode>();
		frontier.add(maze.start);
		pathto.put(maze.start, new ArrayList<MazeNode>(Arrays.asList(maze.start)));
		while(!frontier.isEmpty()){
			MazeNode cur=frontier.remove(0);
			nodesnumber++;
			visited.add(cur);

//			Update route record
//			List<MazeNode> prev=new ArrayList<MazeNode>(pathto.get(cur));

			for(MazeNode node:cur.setneighbour(maze)){
				if(node.gettype()&&!visited.contains(node)){					
					predecessor.put(node, cur);				
					frontier.add(node);
					if(node.equals(maze.end)){
						System.out.println(nodesnumber);
						return node;
					}
					
				}
			}

		}
		return null;
	}
	public int printsolution(Maze maze, MazeNode result){
//		File file=new File()
		int distance = 0;
		Maze copy=new Maze(maze);
		while(predecessor.containsKey(result)) {
			distance++;
			result = predecessor.get(result);
			int index=(maze.col+1)*result.i+result.j;
			if(!result.equals(maze.start))			
				copy.builder.setCharAt(index, '.');

		}
		System.out.println(copy.builder.toString());
		return distance;
	}
	public MazeNode DFSsearch(Maze maze){
		int nodesnumber=0;
		Stack<MazeNode> frontier=new Stack<MazeNode>();
		visited.add(maze.start);
		frontier.push(maze.start);
//		pathto.put(maze.start, new ArrayList<MazeNode>(Arrays.asList(maze.start)));
			while(!frontier.isEmpty()){
				MazeNode cur=frontier.pop();
				nodesnumber++;
				visited.add(cur);
//				List<MazeNode> prev=new ArrayList<MazeNode>(pathto.get(cur));
			for(MazeNode node:cur.setneighbour(maze)){				
				if(node.gettype()&&!visited.contains(node)){
					
					predecessor.put(node, cur);	
					frontier.add(node);
					if(node.equals(maze.end)){
						System.out.println("Expanded Nodes Number:"+nodesnumber);
						return node;
					}
				}
				
			}	
			}
			return null;
	}

		
	public MazeNode Greedy(Maze maze){
		MazeNodeComparator comparator=new MazeNodeComparator(maze);
		Queue<MazeNode> frontier=new PriorityQueue<MazeNode>(maze.row*maze.col,comparator);
		if(maze.start==null)
			return null;
//		put it into visted set and also the frontier
		visited.add(maze.start);
		frontier.add(maze.start);
//		
		int nodesnumber=0;
		while(!frontier.isEmpty()){
		MazeNode cur=frontier.poll();
		nodesnumber++;
		visited.add(cur);
		for(MazeNode node:cur.setneighbour(maze)){
			if(node.gettype()&&!visited.contains(node)){
					frontier.add(node);
					
					predecessor.put(node, cur);	
					if(node.equals(maze.end)){
						System.out.println("Expanded Nodes Number:"+nodesnumber);
						return node;
					}
			}		
		}
		}
			return null;
		
	}
	
	public static void main(String[] args) throws IOException{
		Main test=new Main();
		Maze maze=new Maze();
		
		/*
		 * basic pathfinding for dfs, bfs and greedy
		 * 
		 */
//		MazeNode result=test.BFSsearch(maze);		
//		MazeNode result=test.DFSsearch(maze);
//		MazeNode result=test.Greedy(maze);
		
	
//		This part for Astar search with only one dot
//		AstarSearch astar=new AstarSearch(maze);
//		MazeNode result=astar.Search(maze);	
//		int nodes=astar.printsolution(maze, result);
//		System.out.print("pathcost:"+nodes);
	    
//		This part for Astar search with multiple dots
//		AstarDots astardots=new AstarDots(maze);
//		State result=astardots.Search(maze);
//		int nodes=astardots.printsolution(maze, result);
//		if(result!=null)
//		System.out.print("pathcost:"+nodes);
		
		
//		This part for Astar search with ghost
		AstarGhost astarghost=new AstarGhost(maze);
		Ghost result=astarghost.Search(maze);
		int nodes=astarghost.printsolution(maze, result);
		System.out.print("pathcost:"+nodes);
//		System.out.println(result.pacman.i+"\t"+result.pacman.j);
		
	}

}

