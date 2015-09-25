package part1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Maze {
	
//  
	FileReader fr=null;
	boolean [][] place;
	MazeNode [][] mazenode;
	MazeNode start;
	MazeNode end;
	MazeNode ghostposition;
	int row=0;
	int col=0;
	StringBuilder builder;
	ArrayList<MazeNode> dot;
	public Maze() throws IOException{
		dot=new ArrayList<MazeNode>();
		builder=new StringBuilder();

//	Use another method to do input and count number of lines
	List<String> lines=null;
	try{
	 lines= Files.readAllLines(Paths.get("/Users/newuser/Documents/CS440AI/bigGhost.txt"), Charset.defaultCharset());
	}catch(FileNotFoundException s){
		s.printStackTrace();
	}
	
	row=lines.size();
	col=lines.get(0).length();
	place=new boolean [row][col];
	mazenode=new MazeNode[row][col];
//  This part is to decide character
	try{
		File file=new File("/Users/newuser/Documents/CS440AI/bigGhost.txt");
		fr=new FileReader(file);
	}catch(FileNotFoundException e){
		e.printStackTrace();
	}
	BufferedReader br=new BufferedReader(fr);
	int i=0;
	String sb;
	while((sb=br.readLine())!=null){	
		
		for(int j=0;j<sb.length();j++){
			builder.append(sb.charAt(j));
			if(sb.charAt(j)=='%'){
				
				mazenode[i][j]=new MazeNode(i,j,false);
			}
			else if(sb.charAt(j)==' '||sb.charAt(j)=='g'){
//				builder.append(sb.charAt(j));
				mazenode[i][j]=new MazeNode(i,j,true);
			}
			else if(sb.charAt(j)=='P'){
//				builder.append(sb.charAt(j));
				start=new MazeNode(i,j,true);
				mazenode[i][j]=start;
			}
			else if(sb.charAt(j)=='.'){
//				The below three lines are for basic pathfinding case
				end=new MazeNode(i,j,true);
				end.dot=true;
				mazenode[i][j]=end;
				
//				The below lines are for state: which means multiple dots exist
//				MazeNode dotnode=new MazeNode(i,j,true);
//				dotnode.dot=true;
//				mazenode[i][j]=dotnode;
//				dot.add(dotnode);
				
			}
//			The below branch for games with ghost
			else if(sb.charAt(j)=='G'){
				ghostposition=new MazeNode(i,j,true);
				mazenode[i][j]=ghostposition;
			}
			
		}
		i++;
	}
	br.close();
	}
	
	public String toString(){
		StringBuilder sb=new StringBuilder();
		for(int i=0;i<row;i++){
			for(int j=0;j<col;j++){
				sb.append(mazenode[i][j]);			
			}
			sb.append("\n");
		}
		return sb.toString();		
	}
	
	
}
