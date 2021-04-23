import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Stack;

public class IDA_star {
	Node initial;
	Node goal;
	Hashtable<String, Node> hash = new Hashtable<>();
	Stack<Node> st = new Stack<Node>();
	ArrayList<String> path = new ArrayList<>();
	int num_node_generated = 0;
	double time  = 0.0;
	int cost = 0;
	int t;
	int num_empty_tiles;

	public IDA_star(Node state,Node goal1,int num) {
		initial = state;
		goal = goal1;
		num_empty_tiles = num;
	}
	public void run_IDA() {
		long start = System.currentTimeMillis();
		t = Support.h(initial, goal, num_empty_tiles)*5;
		while(t != Integer.MAX_VALUE) {
			System.out.println("t start : " + t);
			int min_f = Integer.MAX_VALUE;
			st.push(initial);
			hash.put(initial.to_string(), initial);
			while(!st.empty()) {
				Node n = st.pop();
				if(n.out) {
					hash.remove(n.to_string());
				}else{
					n.out = true;
					st.push(n);
					ArrayList<Node> op = Support.make_operators(n);
					num_node_generated += op.size();
					for(Node g : op) {
						int f = g.distance + (Support.h(g, goal, num_empty_tiles)*5);
						System.out.println("f: " +f);
						System.out.println("t: "+t);

						if(f > t) {
							min_f = Math.min(min_f , f);
							System.out.println("min f : " +min_f);
							continue;
						}
						

						if(hash.containsKey(g.to_string()) && (hash.get(g.to_string()).out == true)) {
							continue;
						}
						if(hash.containsKey(g.to_string()) && (hash.get(g.to_string()).out == false)) {
							Node g_tag = hash.get(g.to_string()) ;
							int f_tag = g_tag.distance + (Support.h(g_tag, goal, num_empty_tiles)*5);
							if(f_tag > f) {
								hash.remove(g.to_string());
								st.remove(g);	
							}else {
								continue;
							}
						}

						if(g.equals(goal)) {
							System.out.println("im here");

							while (!st.empty()) {
								Node node = st.pop();
								if(node.out) {
									path.add(0,node.direction);
									cost += node.cost;
								}
							}
							return;
						}
						st.push(g);
						hash.put(g.to_string(), g);	
					}
				}
			}
			t = min_f;
			System.out.println("t end : " +t);
		}
		long end = System.currentTimeMillis() ;
		time = (double)(end - start) / 1000;
		return;
	}
}
