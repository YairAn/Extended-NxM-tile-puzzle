import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Stack;

public class DFBnB {
	Node initial;
	Node goal;
	Hashtable<String, Node> hash = new Hashtable<>();
	Stack<Node> st = new Stack<Node>();
	ArrayList<String> path = new ArrayList<>();
	int num_node_generated = 1;
	double time  = 0.0;
	int cost = 0;
	double t;
	int num_empty_tiles;
	boolean with_open;


	public DFBnB(Node state,Node goal1,boolean b,int num) {
		initial = state;
		goal = goal1;
		with_open = b;
		num_empty_tiles = num;
	}


	public void run_DFBnB() {
		long start = System.currentTimeMillis();
		int board_size = initial.state.length*initial.state[0].length;
		t = Math.min(Double.MAX_VALUE , factorial(board_size)); //upper bound on the depth of the solution
		st.push(initial);
		hash.put(initial.to_string(), initial);	
		while(!(st.empty())) {		
			Node n = st.pop();
			if(n.out) {
				if(with_open) {
					System.out.println("********* open list in iteration : *********");
					Iterator<String> itr = hash.keySet().iterator();		 
					while(itr.hasNext()){
						System.out.println(itr.next());
					}
					System.out.println("********* end of iteration: *********\n");
				}
				hash.remove(n.to_string());
			}else{
				n.out = true;
				st.push(n);
				ArrayList<Node> op = new ArrayList<>();
				for(int i = 1 ; i  <=  12 ; i++) {
					Node g = Support.make_operators(n,i);
					if(g == null) {
						continue;
					}
					op.add(g);
					num_node_generated++;				
				}
				for(Node g : op) {
					g.f = g.distance + Support.h(g, goal, num_empty_tiles);
				}
				op.sort(new CustomComparator());
				for(int i = 0; i < op.size() ; i++) {
					Node g = op.get(i);
					if(g.f >= t) {
						op.subList(i, op.size()).clear();

					}else if(hash.containsKey(g.to_string()) && (hash.get(g.to_string()).out == true)) {
						op.remove(i);
						i--;
					}else if(hash.containsKey(g.to_string()) && (hash.get(g.to_string()).out == false)) {
						if(hash.get(g.to_string()).f <= g.f) {
							op.remove(i);
							i--;
						}else {
							st.remove(hash.get(g.to_string()));
							hash.remove(g.to_string());
						}
					}else if(g.equals(goal)) {
						t = g.f;
						path.clear();
						cost = 0;
						g.out = true;
						st.push(g);
						Stack<Node> tmp = new Stack<Node>();

						while (!st.empty()) { //save the past to this current goal node.
							Node node = st.pop();
							tmp.push(node);
							if(node.out) {
								path.add(0,node.direction);
								cost += node.cost;
							}
						}
						while(!tmp.empty()) {
							st.push(tmp.pop()); // put the elements back in the stack
						}
						st.pop().out=false;; // remove g from stack
						op.subList(i, op.size()).clear();

					}
				}
				for(int j = op.size()-1 ; j >= 0; j--) {//insert op to stack and hash in  a reverse order
					Node in = op.get(j);
					st.add(in);
					hash.put(in.to_string() , in);
				}
			}

		}
		long end = System.currentTimeMillis() ;
		time = (double)(end - start) / 1000;
		return;
	}

	private double factorial(int board_size) {
		if(board_size == 1 || board_size == 0) return 1;
		else
			return board_size*(factorial(board_size-1));
	}

	class CustomComparator implements Comparator<Node> {

		@Override
		public int compare(Node n1, Node n2) {
			double value =  (n1.f) - (n2.f);
			if (value > 0) {
				return 1;
			}
			else if (value < 0) {
				return -1;
			}
			else if(n1.when_burn < n2.when_burn){
				return -1;
			} else {
				return 1;
			}

		}
	}
}
