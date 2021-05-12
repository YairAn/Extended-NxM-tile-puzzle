
public class Node {
	String[][] state;
	String to_string;
	String direction;
	Node parent;
	int cost;
	boolean out;
	int distance;
	double f = 0;
	int when_burn; //this is for comparator check in A* and DFBnB
	static int burn;//this too

	public Node(String[][] s) {
		state = s;
		parent = null;
		to_string = to_string_fun(s);
		cost = 0;
		direction = "";
		distance = 0;
		out = false;
		burn++;
		
	}

	public Node(String[][] s,Node p,int c,String d,int dist) {
		state = s;
		parent = p;
		to_string = to_string_fun(s);
		cost = c;
		direction = d;
		distance = dist;
		out = false;
		burn++;
		
	}
	
	public Node(Node other) {
		this.cost = other.cost;
		this.direction = other.direction;
		this.parent = other.parent;
		this.to_string = other.to_string;
		this.state = other.state;
		this.out = other.out;
		this.distance = other.distance;
		burn++;
	}
	public String to_string_fun(String[][] s){
		String str = "";
		for(int i = 0 ; i < s.length ; i++) {
			for(int j = 0 ; j < s[0].length ; j++) {
				str += s[i][j] + " ";
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
