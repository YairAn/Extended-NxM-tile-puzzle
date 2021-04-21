import java.util.ArrayList;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.Queue;



public class BFS {
	Node initial;
	Node goal;
	Queue<Node> q = new LinkedList<>();
	Hashtable<String, Node> hash_close = new Hashtable<>();
	Hashtable<String, Node> hash_open = new Hashtable<>();
	ArrayList<String> path = new ArrayList<>();
	int num_node_generated = 0;
	double time  = 0.0;
	int cost = 0;

	public BFS(Node state,Node goal1) {
		initial = state;
		goal = goal1;
	}
	
	public void run_bfs() {
		long start = System.currentTimeMillis();
		q.add(initial);
		while(!q.isEmpty()) {
			Node n = q.poll();
			hash_close.put(n.to_string(), n);
			ArrayList<Node> op = Support.make_operators(n);
			num_node_generated += op.size();
			for(Node g : op) {
				if(!(q.contains(g)) && !(hash_close.containsKey(g.to_string()))) {
					if(g.equals(goal)) {
						while(g.parent != null) {
							path.add(0,g.direction);
							cost += g.cost;
							g = g.parent;
						}
						long end = System.currentTimeMillis() ;
						time = (double)(end - start) / 1000;
						op.clear();
						return ;
					}
					q.add(g); 
				}
			}
		}
		long end = System.currentTimeMillis() ;
		time = (double)(end - start) / 1000;
		return;
	}
}
