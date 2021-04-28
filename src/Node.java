
public class Node {
	String[][] state;
	String direction;
	Node parent;
	int cost;
	boolean out;
	int distance;
	int f = 0;

	public Node(String[][] s) {
		state = s;
		parent = null;
		cost = 0;
		direction = "";
		distance = 0;
		out = false;
		
	}

	public Node(String[][] s,Node p,int c,String d,int dist) {
		state = s;
		parent = p;
		cost = c;
		direction = d;
		distance = dist;
		out = false;
		
	}
	
	public Node(Node other) {
		this.cost = other.cost;
		this.direction = other.direction;
		this.parent = other.parent;
		this.state = other.state;
		this.out = other.out;
		this.distance = other.distance;
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
