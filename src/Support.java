import java.lang.Thread.State;
import java.util.ArrayList;
import java.util.List;

public class Support {

	public static ArrayList<Node> make_operators(Node node) {
		ArrayList<Node> operators = new ArrayList<>();
		String[][] s = copy(node.state);
		String[][] create;
		Node n;

		for (int i = 0; i < s.length; i++) {
			for (int j = 0; j < s[0].length; j++) {
				if(s[i][j].equals("_")) {
					if(i < s.length-1 && s[i+1][j].equals("_")) {		//move two vertical	

						if(j < s[0].length-1) {      //move left
							create = copy(s);
							String direction = create[i][j+1] + "&" + create[i+1][j+1] + "L";
							create[i][j] = create[i][j+1];
							create[i][j+1] = "_";
							create[i+1][j] = create[i+1][j+1];
							create[i+1][j+1] = "_";
							n = new Node(create,node,6,direction,(node.distance+6));
							if(node.parent == null) {								
								operators.add(n);
							}else if(!is_state_equals(n, node.parent)) {						
								operators.add(n);
							}
						}
						if(j > 0) {               //move right
							create = copy(s);
							String direction = create[i][j-1] + "&" + create[i+1][j-1] + "R";
							create[i][j] = create[i][j-1];
							create[i][j-1] = "_";
							create[i+1][j] = create[i+1][j-1];
							create[i+1][j-1] = "_";	
							n = new Node(create,node,6,direction,(node.distance+6));
							if(node.parent == null) {								
								operators.add(n);
							}else if(!is_state_equals(n, node.parent)) {						
								operators.add(n);
							}
						}
					}
					if(j < s[0].length-1 && s[i][j+1].equals("_")) { //move two horizontal
						if(i < s.length-1) {      //move up

							create = copy(s);
							String direction = create[i+1][j] + "&" + create[i+1][j+1] + "U";

							create[i][j] = create[i+1][j];
							create[i+1][j] = "_";
							create[i][j+1] = create[i+1][j+1];
							create[i+1][j+1] = "_";	
							n = new Node(create,node,7,direction,(node.distance+7));
							if(node.parent == null) {								
								operators.add(n);
							}else if(!is_state_equals(n, node.parent)) {						
								operators.add(n);
							}		
						}
						if(i > 0) {      //move down
							create = copy(s);
							String direction = create[i-1][j] + "&" + create[i-1][j+1] + "D";

							create[i][j] = create[i-1][j];
							create[i-1][j] = "_";
							create[i][j+1] = create[i-1][j+1];
							create[i-1][j+1] = "_";	
							n = new Node(create,node,7,direction,(node.distance+7));
							if(node.parent == null) {								
								operators.add(n);
							}else if(!is_state_equals(n, node.parent)) {						
								operators.add(n);
							}	
						}
					}
					if(j < s[0].length-1) {  //move one left
						create = copy(s);
						String direction = create[i][j+1] + "L";
						create[i][j] = create[i][j+1];
						create[i][j+1] = "_";
						n = new Node(create,node,5,direction,(node.distance+5));
						boolean b = true;
						if(node.parent!= null && create[i][j].equals(node.parent.state[i][j])) { b = false;}
						if(node.parent == null && !(create[i][j].equals("_")) && b) {								
							operators.add(n);
						}else if(node.parent!= null && !is_state_equals(n, node.parent) && !(create[i][j].equals("_")) && b) {						
							operators.add(n);
						}		
					}
					if(i < s.length-1) {      //move one up

						create = copy(s);
						String direction = create[i+1][j] + "U";

						create[i][j] = create[i+1][j];
						create[i+1][j] = "_";
						n = new Node(create,node,5,direction,(node.distance+5));
						boolean b = true;
						if(node.parent!= null && create[i][j].equals(node.parent.state[i][j])) { b = false;}
						if(node.parent == null && !(create[i][j].equals("_")) && b) {								
							operators.add(n);
						}else if(node.parent!= null && !is_state_equals(n, node.parent) && !(create[i][j].equals("_")) && b) {						
							operators.add(n);
						}	
					}
					if(j > 0) {               //move one right
						create = copy(s);
						String direction = create[i][j-1] + "R";

						create[i][j] = create[i][j-1];
						create[i][j-1] = "_";
						n = new Node(create,node,5,direction,(node.distance+5));
						boolean b = true;
						if(node.parent!= null && create[i][j].equals(node.parent.state[i][j])) { b = false;}
						if(node.parent == null && !(create[i][j].equals("_")) && b) {								
							operators.add(n);
						}else if(node.parent!= null && !is_state_equals(n, node.parent) && !(create[i][j].equals("_")) && b) {						
							operators.add(n);
						}				
					}
					if(i > 0) {            //move one down
						create = copy(s);
						String direction = create[i-1][j] + "D";
						create[i][j] = create[i-1][j];
						create[i-1][j] = "_";
						n = new Node(create,node,5,direction,(node.distance+5));
						boolean b = true;
						if(node.parent!= null && create[i][j].equals(node.parent.state[i][j])) { b = false;}
						if(node.parent == null && !(create[i][j].equals("_")) && b) {								
							operators.add(n);
						}else if(node.parent!= null && !is_state_equals(n, node.parent) && !(create[i][j].equals("_")) && b) {						
							operators.add(n);
						}	
					}
				}
			}
		}
		return operators;

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

	//Haming distance heuristic function	
	public static int h2(Node st,Node goal,int t) {

		int dist = 0;
		int rows = st.state.length;
		int colls = st.state[0].length;
		String[] s = new String[rows*colls];
		for(int i = 0; i < st.state.length; i++) {
			String[] row = st.state[i];
			for(int j = 0; j < row.length; j++) {
				String number = st.state[i][j];
				s[i*row.length+j] = number;
			}
		}
		for(int i = 0; i < s.length-t ;i++) {
			if(s[i].equals("_")) {
				dist++;
			}else {
				if(Integer.parseInt(s[i]) != (i+1))
					dist++; 	   
			}
		}
		//		for(int i = 1; i <= t ;i++) {
		//
		//			if(!(s[s.length-i].equals("_"))) dist++;
		//		}
		return dist;
	}
	
	
	
    // Manhattan distance heuristic function
	public static int h(Node st,Node goal,int t) { //to do - improve to any kind of goal node
		int dist = 0;
		int rows = st.state.length;
		int colls = st.state[0].length;
		for(int i = 0 ; i < rows ; i++) {
			for(int j = 0 ; j < colls ; j++) {
			   String str = st.state[i][j];	
			   int val;
               if(str.equals("_")) {continue;} //{val = rows*colls;} {continue;}
               else{val = Integer.parseInt(str);} 
               if(!st.state[i][j].equals(goal.state[i][j])) {
            	   int d = Math.abs(i - ((val-1)/colls)) + Math.abs(j - ((val-1)%colls));
            	   dist+=d;
               }

			}
		}
		//System.out.println("dist : " + dist + ", type :" + st.direction);
		return (dist*4);
	}


	public static String[][] copy(String[][] s){
		String[][] copy = new String[s.length][s[0].length];
		for(int i = 0 ; i < s.length ; i++) {
			for(int j = 0 ; j < s[0].length ; j++) {
				copy[i][j]= s[i][j];
			}
		}
		return copy;
	}
}
