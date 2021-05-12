import java.util.ArrayList;
import java.util.Comparator;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

import javax.swing.text.Position;

public class A_star {
	Node initial;
	Node goal;
	PriorityQueue<Node> q = new PriorityQueue<>(new CustomComparator());
	Hashtable<String, Node> hash_close = new Hashtable<>();
	Hashtable<String, Node> hash_open = new Hashtable<>();
	ArrayList<String> path = new ArrayList<>();
	int num_node_generated = 1;
	double time  = 0.0;
	int cost = 0;
	boolean with_open;
	int num_empty_tiles;



	public A_star(Node state,Node goal1,boolean b , int n) {
		initial = state;
		goal = goal1;
		with_open = b;
		num_empty_tiles = n;
	}

	public void run_A_star() {
		long start = System.currentTimeMillis();
		Hashtable<Integer,int[]> position = get_position(goal);
		q.add(initial);
		hash_open.put(initial.to_string, initial);
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
			hash_open.remove(n.to_string);
			if(n.equals(goal)) {
				while(n.parent != null) {
					path.add(0,n.direction);
					cost += n.cost;
					n = n.parent;
				}
				long end = System.currentTimeMillis() ;
				time = (double)(end - start) / 1000;
				return ;
			}			
			hash_close.put(n.to_string, n);
			for(int i = 1 ; i  <=  12 ; i++) {
				Node g = Support.make_operators(n,i);
				if(g == null) {
					continue;
				}
				num_node_generated ++;
				double f = g.distance + Support.h(g, goal,position, num_empty_tiles);
				g.f = f;
				if(!(hash_open.containsKey(g.to_string)) && !(hash_close.containsKey(g.to_string))) {
					q.add(g);
					hash_open.put(g.to_string, g);

				}else if(hash_open.containsKey(g.to_string)){
					if(hash_open.get(g.to_string).f > f) {
						q.remove(hash_open.get(g.to_string));
						q.add(g);
						hash_open.remove(g.to_string);
						hash_open.put(g.to_string, g);
					}					
				}
			}
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