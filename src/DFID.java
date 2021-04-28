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
			String result  = limited_dfs(initial,goal,depth);
			if(!(result.equals("cutoff"))) {
				break;  //return; //break; because time
			}
		}
		long end = System.currentTimeMillis() ;
		time = (double)(end - start) / 1000;
		return;
	}

	private String limited_dfs(Node node, Node goal, int depth) {
		

		String result = "";
		boolean is_cutoff = false;
		if(node.equals(goal)) {
			while(node.parent != null) {
				path.add(0,node.direction);
				cost += node.cost;
				node = node.parent;
			}
			return "path";
		} else if(depth == 0){
			return "cutoff";
		}else {
			hash.put(node.to_string(),node);
			is_cutoff = false;
			ArrayList<Node> op = Support.make_operators(node);
			num_node_generated += op.size();
			for(Node g : op) {
				if(hash.containsKey(g.to_string())) continue;
				result = limited_dfs(g, goal, depth-1);     //Recursion call
				if(result.equals("cutoff")) {
					is_cutoff = true;
				} else if(!result.equals("fail")) {
					return result;
				}
			}
			hash.remove(node.to_string());
			if(is_cutoff == true) return "cutoff";
			return "fail";
		}
	}		



}
