import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;



public class DFID {
	Node initial;
	Node goal;
	Hashtable<String, Node> hash = new Hashtable<>();
	ArrayList<String> path = new ArrayList<>();
	int num_node_generated = 0;
	double time  = 0.0;
	int cost = 0;

	public DFID(Node state,Node goal1) {
		initial = state;
		goal = goal1;
	}

	public void run_dfid() {
		long start = System.currentTimeMillis();
		for(int depth = 1 ; depth < Integer.MAX_VALUE ; depth++) {
			hash.clear();
			limited_dfs(initial,goal,depth);
			if(!(path.get(0).equals("cutoff"))) {
                return;
			}
		}
		long end = System.currentTimeMillis() ;
		time = (double)(end - start) / 1000;
		return;
	}

	private void limited_dfs(Node node, Node goal, int depth) {
		if(node.equals(goal)) {
			while(node.parent != null) {
				path.add(0,node.direction);
				cost += node.cost;
				node = node.parent;
			} else if{
				
			}
		//fbdnbd
		
		return;
	}

	hash_open.put(initial.to_string(), initial);
	while(!q.isEmpty()) {
		Node n = q.poll();
		hash_open.remove(n.to_string());
		hash_close.put(n.to_string(), n);
		ArrayList<Node> op = Support.make_operators(n);
		num_node_generated += op.size();
		for(Node g : op) {
			if(!(hash_open.containsKey(g.to_string())) && !(hash_close.containsKey(g.to_string()))) {
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
				hash_open.put(g.to_string(), g);
			}
		}
	}
	long end = System.currentTimeMillis() ;
	time = (double)(end - start) / 1000;
	return;
}


}
