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
//	boolean [][] place;

	Set<MazeNode> visited;
//	List<Integer> start=new ArrayList<Integer>();
//	List<Integer> end=new ArrayList<Integer>();
	Map<MazeNode,List<MazeNode>> pathto;
//	Map<MazeNode,Double> hero=new HashMap<MazeNode,Double>();
	
	public Main() throws IOException{
//	input
		visited=new HashSet<MazeNode>();
		pathto=new HashMap<MazeNode,List<MazeNode>>();	
	}

	public void BFSsearch(Maze maze){
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
			List<MazeNode> prev=new ArrayList<MazeNode>(pathto.get(cur));
					
//			decide whether to end the loop
//			if(visited.contains(maze.end)){
//				List<MazeNode> result=pathto.get(maze.end);
//				break;
//			}

			for(MazeNode node:cur.setneighbour(maze)){
				if(node.gettype()&&!visited.contains(node)){
//					System.out.println(nodesnumber);
					prev.add(node);
					pathto.put(node, prev);
					frontier.add(node);
					if(node.equals(maze.end)){
						System.out.println(nodesnumber);
						System.out.println(pathto.get(node));
						return;
					}
					
				}
			}

		}
		
	}
	
	public void DFSsearch(Maze maze){
//		Stack<MazeNode> stack=new Stack<MazeNode>();
//		stack.push(start);
		int nodesnumber=0;
		Stack<MazeNode> frontier=new Stack<MazeNode>();
		visited.add(maze.start);
		frontier.push(maze.start);
		pathto.put(maze.start, new ArrayList<MazeNode>(Arrays.asList(maze.start)));
			while(!frontier.isEmpty()){
				MazeNode cur=frontier.pop();
				visited.add(cur);
				List<MazeNode> prev=new ArrayList<MazeNode>(pathto.get(cur));
			for(MazeNode node:cur.setneighbour(maze)){				
				if(node.gettype()&&!visited.contains(node)){
					nodesnumber++;
					System.out.println(nodesnumber);
					prev.add(node);
					pathto.put(node, prev);
					frontier.add(node);
					if(node.equals(maze.end)){
						System.out.println(nodesnumber);
						System.out.println(pathto.get(node));
						return;
					}
				}
				
			}	
			}
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
		visited.add(cur);
		for(MazeNode node:cur.setneighbour(maze)){
			if(node.gettype()&&!visited.contains(node)){
					nodesnumber++;
					System.out.println(nodesnumber);
					frontier.add(node);
					if(node.equals(maze.end))
						return node;
			}		
		}
		}
			return null;
		
	}
	
	public static void main(String[] args) throws IOException{
		Main test=new Main();
		Maze maze=new Maze();
//		System.out.print(maze.toString());
		
//		This part for Astar search with only one dot
//		AstarSearch astar=new AstarSearch(maze);
//		MazeNode result=astar.Search(maze);	
//		System.out.print(result.i+"|"+result.j);
	    
//		This part for Astar search with multiple dots
		AstarDots astardots=new AstarDots(maze);
		State result=astardots.Search(maze);
		System.out.println(result==null);
		
	}

}

