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
	int num_node_generated = 1;
	double time  = 0.0;
	int cost = 0;
	double t;
	int num_empty_tiles;
	boolean with_open;

	public IDA_star(Node state,Node goal1,boolean b ,int num) {
		initial = state;
		goal = goal1;
		with_open = b;
		num_empty_tiles = num;
	}
	public void run_IDA() {
		long start = System.currentTimeMillis();
		Hashtable<Integer,int[]> position = get_position(goal);
		t = Support.h(initial, goal,position, num_empty_tiles);
		Node n ;// = new Node(initial);
		while(t != Integer.MAX_VALUE) {
			double min_f = Integer.MAX_VALUE;
			initial.out = false;
			hash.clear();
			st.clear();
			st.push(initial);
			hash.put(initial.to_string, initial);
			while(!st.empty()) {
			    n = st.pop();
				if(n.out) {
					if(with_open) {
						System.out.println("********* open list in iteration : *********");
						Iterator<String> itr = hash.keySet().iterator();		 
						while(itr.hasNext()){
							System.out.println(itr.next());
						}
						System.out.println("********* end of iteration: *********\n");
					}
					hash.remove(n.to_string);
				}else{
					n.out = true;
					st.push(n);
					for(int i = 1 ; i  <=  12 ; i++) {
						Node g = Support.make_operators(n,i);
						if(g == null) {
							continue;
						}
						num_node_generated ++;
						double f = g.distance + (Support.h(g, goal,position, num_empty_tiles));
						if(f > t) {
							min_f = Math.min(min_f , f);
							continue;
						}
						if(hash.containsKey(g.to_string) && (hash.get(g.to_string).out == true)) {
							continue;
						}
						if(hash.containsKey(g.to_string) && (hash.get(g.to_string).out == false)) {
							Node g_tag = hash.get(g.to_string) ;
							double f_tag = g_tag.distance + (Support.h(g_tag, goal,position, num_empty_tiles));
							if(f_tag > f) {
								hash.remove(g_tag.to_string);
								st.remove(g_tag);	
							}else {
								continue;
							}
						}
						if(g.equals(goal)) {
                            g.out = true;
                            st.push(g);
							while (!st.empty()) {
								Node node = st.pop();
								if(node.out) {
									path.add(0,node.direction);
									cost += node.cost;
								}
							}
							long end = System.currentTimeMillis() ;
							time = (double)(end - start) / 1000;
							return;

						}					
						st.push(g);
						hash.put(g.to_string, g);	
						
					}
				}
			}
			t = min_f;
		}
		long end = System.currentTimeMillis() ;
		time = (double)(end - start) / 1000;
		return;
	}
	
	public Hashtable<Integer,int[]> get_position(Node goal){
		int rows = goal.state.length;
		int colls = goal.state[0].length;
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
		return position;
	}
}
