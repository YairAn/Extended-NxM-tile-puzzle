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
		t = 60; //Support.h(initial, goal, num_empty_tiles);
		Node n  = new Node(initial);
		while(t != Integer.MAX_VALUE) {
			System.out.println("t start : " + t);
			int min_f = Integer.MAX_VALUE;
			//initial.out = false;
			hash.clear();
			st.clear();
			st.push(initial);
			hash.put(initial.to_string(), initial);
			while(!st.empty()) {
				//System.out.println("while loop");
				//System.out.println(st.peek().to_string());
				//System.out.println(st.peek().out);

			    n = st.pop();
				if(n.out) {
					hash.remove(n.to_string());
				}else{
					System.out.println("in the else");
					n.out = true;
					st.push(n);
					ArrayList<Node> op = Support.make_operators(n);
					num_node_generated += op.size();
				    System.out.println("****************");
					System.out.println(op.size());
					for(Node g : op) {
						int f = g.distance + (Support.h(g, goal, num_empty_tiles));
						System.out.println("f: " +f);
						System.out.println("t: "+t);
						if(f > t) {
							min_f = Math.min(min_f , f);
							//System.out.println("min f : " +min_f);
							continue;
						}
						if(hash.containsKey(g.to_string()) && (hash.get(g.to_string()).out == true)) {
							continue;
						}
						if(hash.containsKey(g.to_string()) && (hash.get(g.to_string()).out == false)) {
							Node g_tag = hash.get(g.to_string()) ;
							int f_tag = g_tag.distance + (Support.h(g_tag, goal, num_empty_tiles));
							if(f_tag > f) {
								hash.remove(g_tag.to_string());
								st.remove(g_tag);	
							}else {
								continue;
							}
						}
						if(g.equals(goal)) {
							System.out.println("im here");
                            g.out = true;
                            st.push(g);
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
						//System.out.println("push");
						//System.out.println(st.toString());
					}
				}
			}
			t = min_f;
			//n.out = false;
			//st.clear(); // maybe no need???
			System.out.println("t end : " +t);
		}
		long end = System.currentTimeMillis() ;
		time = (double)(end - start) / 1000;
		return;
	}
}
