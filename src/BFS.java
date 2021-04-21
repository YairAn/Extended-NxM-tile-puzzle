import java.util.ArrayList;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.Queue;



public class BFS {
   Node initial;
   Node goal;
   Queue<Node> q = new LinkedList<>();
   Hashtable<Integer, Node> hash_close = new Hashtable<>();
   Hashtable<Integer, Node> hash_open = new Hashtable<>();
   ArrayList<String> path = new ArrayList<>();
   int num_node_generated = 0;
   double time  = 0;
   int cost =0;
   
   public BFS(Node state,Node goal1) {
	   initial = state;
	   goal = goal1;
	   q.add(initial);
	   
   }
   public void run_bfs() {
	   cost = 32;
   }
}
