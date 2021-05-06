import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;



public class BFS {
	Node initial;
	Node goal;
	Queue<Node> q = new LinkedList<>();
	Hashtable<String, Node> hash_close = new Hashtable<>();
	Hashtable<String, Node> hash_open = new Hashtable<>();
	ArrayList<String> path = new ArrayList<>();
	int num_node_generated = 1;
	double time  = 0.0;
	int cost = 0;
	boolean with_open;

	public BFS(Node state,Node goal1,boolean b) {
		initial = state;
		goal = goal1;
		with_open = b;
	}

	public void run_bfs() {
		long start = System.currentTimeMillis();
		q.add(initial);
		hash_open.put(initial.to_string(), initial);
		int iteration = 0;
		while(!q.isEmpty()) {
			if(with_open) {
				System.out.println("********* open list in iteration number " + (++iteration) + ":*********");
				Iterator<String> itr = hash_open.keySet().iterator();		 
				while(itr.hasNext()){
					System.out.println(itr.next());
				}
				System.out.println("********* end of iteration: *********\n");
			}
			Node n = q.poll();
			hash_open.remove(n.to_string());
			hash_close.put(n.to_string(), n);
			for(int i = 1 ; i  <=  12 ; i++) {
				Node g = Support.make_operators(n,i);
				if(g == null) {
					continue;
				}
				num_node_generated ++;
				if(!(hash_open.containsKey(g.to_string())) && !(hash_close.containsKey(g.to_string()))) {
					if(g.equals(goal)) {
						while(g.parent != null) {
							path.add(0,g.direction);
							cost += g.cost;
							g = g.parent;
						}
						long end = System.currentTimeMillis() ;
						time = (double)(end - start) / 1000;
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
