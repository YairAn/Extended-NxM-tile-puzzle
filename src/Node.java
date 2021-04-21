
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
	public void to_string(){
		for(int i = 0 ; i < state.length ; i++) {
			for(int j = 0 ; j < state[0].length ; j++) {
				System.out.print(state[i][j] + " ");
			}
			System.out.println();

		}
	}
}
