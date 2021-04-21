
public class Node {
	String[][] state;
	String direction;
	Node parent;
	int cost;

	public Node(String[][] s) {
		state = s;
		parent = null;
		cost = 0;
		direction = "";
		
	}

	public Node(String[][] s,Node p,int c,String d) {
		state = s;
		parent = p;
		cost = c;
		direction = d;
		
	}
	public String to_string(){
		String str = "";
		for(int i = 0 ; i < state.length ; i++) {
			for(int j = 0 ; j < state[0].length ; j++) {
				str += state[i][j] + " ";
			}
            str += "\n";
		}
		return str;
	}
	
	public boolean equals(Node goal) {
		boolean b  = true;
		for(int i = 0 ; i < this.state.length ; i++) {
			for(int j = 0 ; j < this.state[0].length ; j++) {
				if(!(this.state[i][j].equals(goal.state[i][j]))) {
					b = false;
					return b;
				}
			}		
		}
		return b;
	}
}
