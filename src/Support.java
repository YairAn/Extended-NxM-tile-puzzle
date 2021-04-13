import java.lang.Thread.State;

public class Support {

	//checks if two matrix are the same
	public static boolean is_goal(Node st,Node goal) {
		boolean b  = true;
		for(int i = 0 ; i < st.state.length ; i++) {
			for(int j = 0 ; j < st.state[0].length ; j++) {
				if(!(st.state[i][j].equals(goal.state[i][j]))) {
					b = false;
					return b;
				}
			}		
		}
		return b;
	}

	//Haming distance heuristic function	
	public static int h(Node st,Node goal,int t) {
		int dist = 0;
		int rows = st.state.length;
		int colls = st.state[0].length;
		String[] s = new String[rows*colls];
		for(int i = 0; i < st.state.length; i++) {
			String[] row = st.state[i];
			for(int j = 0; j < row.length; j++) {
				String number = st.state[i][j];
				s[i*row.length+j] = number;
			}
		}
		for(int i = 0; i < s.length-t ;i++) {
			if(s[i].equals("_")) {
				dist++;
			}else {
				if(Integer.parseInt(s[i]) != (i+1))
					dist++; 	   
			}
		}
		for(int i = 1; i <= t ;i++) {

			if(!(s[s.length-i].equals("_"))) dist++;
		}
		return dist;
	}
}
