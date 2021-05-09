import java.lang.Thread.State;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Hashtable;
import java.util.List;

import javax.swing.text.Position;
public class Support {

	public static Node make_operators(Node node,int order) {
		String[][] s = node.state;
		String[][] create;
		Node n = null;
		boolean next_blank = false;
		if(order > 8) next_blank = true;
		int once = 0;
		for (int i = 0; i < s.length; i++) {
			for (int j = 0; j < s[0].length; j++) {
				if(s[i][j].equals("_")) {
					once++;
					if(next_blank == true && once == 1) {
						continue;
					}
					if(i < s.length-1 && s[i+1][j].equals("_")) {		//move two vertical	
						
						if((order == 1) && j < s[0].length-1 ) {      //move left
							create = copy(s);
							String direction = create[i][j+1] + "&" + create[i+1][j+1] + "L";
							create[i][j] = s[i][j+1];
							create[i][j+1] = "_";
							create[i+1][j] = s[i+1][j+1];
							create[i+1][j+1] = "_";
							n = new Node(create,node,6,direction,(node.distance+6));							
							n.when_burn = Node.burn;
							if(node.parent == null) {								
								return n;
							}else if(  !create[i][j].equals(node.parent.state[i][j])
									&& !create[i+1][j].equals(node.parent.state[i+1][j])) {						
								return n;
							}
							return null;
							
						}
						if((order == 2 ) && j > 0) {               //move right
							create = copy(s);
							String direction = create[i][j-1] + "&" + create[i+1][j-1] + "R";
							create[i][j] = create[i][j-1];
							create[i][j-1] = "_";
							create[i+1][j] = create[i+1][j-1];
							create[i+1][j-1] = "_";	
							n = new Node(create,node,6,direction,(node.distance+6));
							n.when_burn = Node.burn; 							
							if(node.parent == null) {								
								return n;
							}else if(  !create[i][j].equals(node.parent.state[i][j])
									&& !create[i+1][j].equals(node.parent.state[i+1][j])) {		 						
								return n;
							}
							return null;
						}
					}
					
					if(j < s[0].length-1 && s[i][j+1].equals("_")) { //move two horizontal
						
						if( (order == 3) && i < s.length-1) {      //move up
							create = copy(s);
							String direction = create[i+1][j] + "&" + create[i+1][j+1] + "U";
							create[i][j] = create[i+1][j];
							create[i+1][j] = "_";
							create[i][j+1] = create[i+1][j+1];
							create[i+1][j+1] = "_";	
							n = new Node(create,node,7,direction,(node.distance+7));
							n.when_burn = Node.burn;							
							if(node.parent == null) {								
								return n;
							}else if(
									 !create[i][j].equals(node.parent.state[i][j])
									&& !create[i][j+1].equals(node.parent.state[i][j+1])
									) {		 							
								return n;
							}
							return null;
						}
						if( (order == 4) && i > 0 ) {      //move down
							create = copy(s);
							String direction = create[i-1][j] + "&" + create[i-1][j+1] + "D";
							create[i][j] = create[i-1][j];
							create[i-1][j] = "_";
							create[i][j+1] = create[i-1][j+1];
							create[i-1][j+1] = "_";	
							n = new Node(create,node,7,direction,(node.distance+7));
							n.when_burn = Node.burn;							
							if(node.parent == null) {
								return n;
							}else if(  !create[i][j].equals(node.parent.state[i][j])
									&& !create[i][j+1].equals(node.parent.state[i][j+1])
									) {
								return n;
							}	
							return null;
						}
					}
					if( (order == 5 || order == 9) &&  j < s[0].length-1) {  //move one left
						create = copy(s);
						String direction = create[i][j+1] + "L";
						create[i][j] = create[i][j+1];
						create[i][j+1] = "_";
						n = new Node(create,node,5,direction,(node.distance+5));
						n.when_burn = Node.burn;
						
						if(node.parent == null && !(create[i][j].equals("_"))) {								
							return n;
						}else if(node.parent!= null && 
								!(create[i][j].equals("_")) && !create[i][j].equals(node.parent.state[i][j])) {						
							return n;
						}
						return null;
					}
					if((order == 6 || order == 10) && i < s.length-1) {      //move one up

						create = copy(s);
						String direction = create[i+1][j] + "U";

						create[i][j] = create[i+1][j];
						create[i+1][j] = "_";
						n = new Node(create,node,5,direction,(node.distance+5));
						n.when_burn = Node.burn;
						
						if(node.parent == null && !(create[i][j].equals("_"))) {								
							return n;
						}else if(node.parent != null && 
								!(create[i][j].equals("_")) && !create[i][j].equals(node.parent.state[i][j])) {						
							return n;
						}
						return null;
					}
					if( (order == 7 || order == 11) && j > 0) {               //move one right
						create = copy(s);
						String direction = create[i][j-1] + "R";

						create[i][j] = create[i][j-1];
						create[i][j-1] = "_";
						n = new Node(create,node,5,direction,(node.distance+5));
						n.when_burn = Node.burn;
						
						if(node.parent == null && !(create[i][j].equals("_"))) {								
							return n;
						}else if(node.parent!= null &&  
								!(create[i][j].equals("_")) && !create[i][j].equals(node.parent.state[i][j])) {						
							return n;
						}
						return null;
					}

					if((order == 8 || order == 12) && i > 0 ) {            //move one down
						create = copy(s);
						String direction = create[i-1][j] + "D";
						create[i][j] = create[i-1][j];
						create[i-1][j] = "_";
						n = new Node(create,node,5,direction,(node.distance+5));
						n.when_burn = Node.burn;
						
						if(node.parent == null && !(create[i][j].equals("_"))) {								
							return n;
						}else if(node.parent!= null && 
								!(create[i][j].equals("_")) && !create[i][j].equals(node.parent.state[i][j])) {						
							return n;
						}
						return null;
					}

					return null;
				}
			}
		}
		return null;
	}
	
	//checks if two matrix are the same
	public static boolean is_state_equals(Node st,Node goal) {
		boolean b  = true;
		for(int i = 0 ; i < st.state.length ; i++) {
			for(int j = 0 ; j < st.state[0].length ; j++) {
				if(!(st.state[i][j].equals(goal.state[i][j]))) {
					b = false;
					return b;
				}
			}		
		}
		return b;
	}


	// Manhattan distance heuristic function - not used right now
	public static double h2(Node st,Node goal,int t) { 

		int dist = 0;
		int rows = st.state.length;
		int colls = st.state[0].length;
		Hashtable<Integer,int[]> position = new Hashtable<>();
		for(int i = 0 ; i < rows ; i++) {
			for(int j = 0 ; j < colls ; j++) {
				String str = goal.state[i][j];
				if(str.equals("_")) {
					continue;
				}else{
					int key = Integer.parseInt(str);
					int[] pos = {i,j};
					position.put(key, pos);				 
				} 
			}
		}
		for(int i = 0 ; i < rows ; i++) {
			for(int j = 0 ; j < colls ; j++) {
				String str = st.state[i][j];	
				int val;
				if(str.equals("_")) {
					continue;
				}else{
					val = Integer.parseInt(str);
				} 
				if(!st.state[i][j].equals(goal.state[i][j])) {
					int goal_i = position.get(val)[0];
					int goal_j = position.get(val)[1];
					int d = Math.abs(i - goal_i) + Math.abs(j - goal_j);
					dist+=d;

				}

			}
		}
		double factor = 5;
		if(t == 2) factor = 3; //Uses a weighted average in case of two empty tiles
		return (dist*factor);
	}

	//based on Manhattan distance heuristic function with addition
	public static double h(Node st,Node goal,int t) { 

		int dist = 0;
		int rows = st.state.length;
		int colls = st.state[0].length;
		Hashtable<Integer,int[]> position = new Hashtable<>();
		for(int i = 0 ; i < rows ; i++) {
			for(int j = 0 ; j < colls ; j++) {
				String str = goal.state[i][j];
				if(str.equals("_")) {
					continue;
				}else{
					int key = Integer.parseInt(str);
					int[] pos = {i,j};
					position.put(key, pos);				 
				} 
			}
		}
		for(int i = 0 ; i < rows ; i++) {
			for(int j = 0 ; j < colls ; j++) {
				String str = st.state[i][j];	
				int val;
				if(str.equals("_")) {
					continue;
				}else{
					val = Integer.parseInt(str);
				} 
				if(!(st.state[i][j].equals(goal.state[i][j]))) {
					int goal_i = position.get(val)[0];
					int goal_j = position.get(val)[1];
					int d = Math.abs(i - goal_i) + Math.abs(j - goal_j);
					dist+=d;
					//the addition
					List<List<String>> list = new ArrayList<>();
					if(i <= goal_i && j <= goal_j) {       //the goal is down and right
						list = find_all_Paths(st.state, i,  j, goal_i, goal_j,1);
					}else if(i >= goal_i && j >= goal_j) { //the goal is up and left
						list = find_all_Paths(st.state,  goal_i, goal_j,i,  j,1);
					}else if(i <= goal_i && j >= goal_j) { //the goal is down and left
						list = find_all_Paths(st.state,  goal_i, goal_j,i,  j,-1);
					}else if(i >= goal_i && j <= goal_j) { //the goal is up and right
						list = find_all_Paths(st.state, i,  j, goal_i, goal_j,-1);
					}
					int no_move = 0;
					for(List<String> l : list ) {
						int tmp = 0;
						for(String s : l) {
							if(s.equals("_"))
								tmp++;
						}
						if(tmp > no_move) {
							no_move = tmp;
						}
						if(l.size() == 0) {
							no_move = d;
						}
					}
					dist += (d-no_move);
				}
			}
		}

		double factor = 5;     //one tile - all moves cost the same
		if(t == 2) factor = 3; //two tiles - the lower bound of each tile to move one step
		return (dist*factor);
	}


	public static List<List<String>> find_all_Paths(String [][] matrix,int i, int j,int g_i,int g_j,int mull)
	{
		List<List<String>> list = new ArrayList<>();
		matrixPathsHelper(list, new ArrayList<String>(), matrix, i,j,g_i,g_j,mull);
		return list;
	}
	/*
	find all the paths from a given point to a goal point
	the mull (as multiply) param is use to determine if to go up or down on the "i" scale -> 1 or -1
	 */
	private static void matrixPathsHelper(List<List<String>> list , List<String> paths, String [][] matrix,
			int row,int column,int g_i,int g_j,int mull){

		// base case
		if(row==g_i)
		{
			ArrayList<String> pathsTemp=new ArrayList<>(paths);
			for (int i = column; i <= g_j; i++) {
				pathsTemp.add(matrix[row][i]);
			}
			list.add(pathsTemp);
			return;
		}
		// base case
		if(column==g_j)
		{   
			ArrayList<String> pathsTemp=new ArrayList<>(paths);
			if(mull == -1) {
				for (int i = row; i >= g_i ; i--) {
					pathsTemp.add(matrix[i][column]);
				}
				list.add(pathsTemp);
				return;
			} else {
				for (int i = row; i <= g_i; i++) {
					pathsTemp.add(matrix[i][column]);
				}
				list.add(pathsTemp);
				return;
			}
		}
		// Add to list
		paths.add(matrix[row][column]);
		// go down
		matrixPathsHelper(list, paths, matrix,row+(1*mull),column,g_i,g_j,mull);
		// go right
		matrixPathsHelper(list, paths, matrix,row,column+1,g_i,g_j,mull);
		// Remove from list : backtrack
		paths.remove(paths.size() - 1);
	}


	//deep copy of a matrix of strings
	public static String[][] copy(String[][] s){
		String[][] copy = new String[s.length][s[0].length];
		for(int i = 0 ; i < s.length ; i++) {
			for(int j = 0 ; j < s[0].length ; j++) {
				copy[i][j] = s[i][j];
			}
		}
		return copy;
	}
}

