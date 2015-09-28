package part1;


import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

import javax.imageio.ImageIO;

public class AstarGhost{
	Queue<Ghost> frontier;
	Set<Ghost> visited;
	double distance=0;
	Map<Ghost,Ghost> predecessor;
	LinkedHashSet<Integer> animation=new LinkedHashSet<Integer>();
	public AstarGhost(Maze maze){
		Comparator<Ghost> comparator=new Ghostcomparator(maze);
		frontier=new PriorityQueue<Ghost>(maze.row*maze.col,comparator);	
		visited=new LinkedHashSet<Ghost>();
		predecessor=new HashMap<Ghost,Ghost>();
	}
	
	public Ghost Search(Maze maze) throws IOException{	
//	  For output	
	
//		
		maze.start.distancesofar=0;
		Ghost start=new Ghost(maze.start,maze.ghostposition,1);
		start.ghost=maze.ghostposition;
		start.ghost.ghostdirection=true;
		frontier.add(start);
		visited.add(start);
//		pathto.put(start, new ArrayList<Ghost>(Arrays.asList(start)));
		int nodesnumber=0;
		while(!frontier.isEmpty()){
//			Make use of the priorityqueue
			Ghost cur=frontier.poll();
			
//			if(cur.lose())
			nodesnumber++;
			visited.add(cur);
//			animation.add((maze.col+1)*cur.pacman.i+cur.pacman.j);
			int failurecount=0;
			for(Ghost node:cur.setneighbour(maze)){
				if(node.danger)
					System.out.println("danger"+"\t"+node);
				if(node.empty&&!visited.contains(node)&&!node.danger){
//					System.out.println(nodesnumber);
					
					node.distancesofar=cur.distancesofar+1;
					frontier.add(node);
					predecessor.put(node, cur);
					if(node.pacman.equals(maze.end)){
						System.out.println("Expanded Nodes Number:"+nodesnumber);
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
	
	public int printsolution(Maze maze, Ghost result) throws FileNotFoundException{
//		File file=new File()
//		FileOutputStream output=new FileOutputStream(new File("/Users/newuser/Documents/CS440AI/smallGhost.txt"));
		Stack <Ghost> stack=new Stack<Ghost>();
		
		int distance = 0;
		Maze copy=new Maze(maze);
		Maze copy2=new Maze(maze);
		while(predecessor.containsKey(result)) {
			distance++;
//			System.out.println(result);
			
			result = predecessor.get(result);
			stack.push(new Ghost(result));
			int index=(maze.col+1)*result.pacman.i+result.pacman.j;
			if(!result.equals(maze.start))			
				copy.builder.setCharAt(index, '.');

		}
		int count=0;
		int prev=0;
		while(!stack.isEmpty()){
			Ghost target=stack.pop();
			int index2=(maze.col+1)*target.pacman.i+target.pacman.j;
			int index3=(maze.col+1)*target.ghost.i+target.ghost.j;
			copy2.builder.setCharAt(index2, '.');
			copy2.builder.setCharAt(index3, 'g');
			if(count>0)
				copy2.builder.setCharAt(prev, ' ');
			prev=index3;
			count++;
			System.out.println(copy2.builder.toString());
		}
//		
		
//		for(Ghost target:visited){
//			int index2=(maze.col+1)*target.pacman.i+target.pacman.j;
//			int index3=(maze.col+1)*target.ghost.i+target.ghost.j;
//			
//			copy2.builder.setCharAt(index2, '.');
//			copy2.builder.setCharAt(index3, 'g');
//			if(count>0)
//				copy2.builder.setCharAt(prev, ' ');
//			prev=index3;
//			count++;
//			System.out.println(copy2.builder.toString());
//		}
		System.out.println(copy.builder.toString());
		return distance;
	}
	
	 public static void saveImageAsJPEG(BufferedImage image, OutputStream stream)
		      throws IOException {
		    ImageIO.write(image, "jpg", stream);
		  }
}

