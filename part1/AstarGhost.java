package part1;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
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

public class AstarGhost{
	Queue<Ghost> frontier;
	Set<Ghost> visited;
	Map<Ghost,List<Ghost>> pathto;
	double distance=0;
	public AstarGhost(Maze maze){
		Comparator<Ghost> comparator=new Ghostcomparator(maze);
		frontier=new PriorityQueue<Ghost>(maze.row*maze.col,comparator);	
		pathto=new HashMap<Ghost,List<Ghost>>();
		visited=new HashSet<Ghost>();
	}
	
	public Ghost Search(Maze maze) throws IOException{	
//	  For output	
		try{
			File file=new File("/Users/newuser/Documents/CS440AI/smallghostresult.txt");
			FileWriter fr=new FileWriter(file,false);
		}catch(FileNotFoundException e){
			e.printStackTrace();
		}

		
//		
		maze.start.distancesofar=0;
		Ghost start=new Ghost(maze.start,maze.ghostposition,1);
		start.ghost=maze.ghostposition;
		start.ghost.ghostdirection=true;
		frontier.add(start);
		visited.add(start);
		pathto.put(start, new ArrayList<Ghost>(Arrays.asList(start)));
		int nodesnumber=1;
		while(!frontier.isEmpty()){
//			Make use of the priorityqueue
			Ghost cur=frontier.poll();
			List<Ghost> prev=new ArrayList<Ghost>(pathto.get(cur));
			if(!visited.add(cur))
				System.out.println("false"+cur);
			int failurecount=0;
			for(Ghost node:cur.setneighbour(maze)){
				if(node.empty&&!visited.contains(node)){
//					System.out.println(nodesnumber);
					nodesnumber++;
					node.distancesofar=cur.distancesofar+1;
					frontier.add(node);
					prev.add(node);
					pathto.put(node, prev);
					if(node.pacman.equals(maze.end)){
						System.out.println(nodesnumber);
						return node;
					}
						
				}
				else if(node.danger){
					failurecount++;
					if(failurecount==4){
						for(Ghost sb:visited)
							System.out.println(sb);
						System.out.println("You lose the game");
						return null;
					}
					
				}
			}
			
		}
//		for(Ghost sb:visited)
//			System.out.println(sb);
		return null;
	}
	
	public double getdistance(Maze maze,Ghost node){
		return pathto.get(node).size();			
	}
}

