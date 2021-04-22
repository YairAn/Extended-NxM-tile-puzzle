import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import javax.xml.soap.Detail;
import javax.xml.stream.events.StartDocument;;
public class Ex1 {


	public static void main(String[] args) throws Exception
	{   
		//read from file all the details and assign them.
		Scanner scanner = new Scanner(Ex1.class.getResourceAsStream("input.txt"));
		String s = new String();
		s = s + scanner.nextLine();
		while(scanner.hasNextLine()){
			s = s + "\n";
			s = s + scanner.nextLine();
		}
		scanner.close();

		int num_empty_tiles = 0;
		String[] details = s.split("\n");
		String algo_name = details[0];
		boolean with_time = false;
		boolean with_open = false;
		if(details[1].equals("with time")) {with_time = true;}
		if(details[2].equals("with open")) {with_open = true;}
		int rows_num = Character.getNumericValue(details[3].charAt(0));
		int colls_num = Character.getNumericValue(details[3].charAt(2));
		String[][] start = new String [rows_num][colls_num];
		String[][] goal = new String [rows_num][colls_num];

		//Initial the start vector
		for(int i = 4 ; i < (4 + rows_num) ; i++) {
			for(int j = 0 ; j<colls_num ; j++) {
				String[] tmp = details[i].split(",");
				start[i-4][j] = tmp[j];
				if(tmp[j].equals("_")) {num_empty_tiles++;}
			}
		}
		//Initial the goal vector
		for(int i = (4 + rows_num + 1) ; i < (4 + (2 * rows_num) +1) ; i++ ) {
			for(int j = 0 ; j<colls_num ; j++) {
				String[] tmp = details[i].split(",");
				goal[i-(4 + rows_num + 1)][j] = tmp[j];
			}
		}
		Node initial = new Node(start);
		Node goaln = new Node(goal);
		//choose witch algorithm to activate		
		switch(algo_name)
		{          
		case "BFS":
			BFS b = new BFS(initial,goaln,with_open);
			b.run_bfs();
			String path = "";
			if(b.path.size() == 0) {path = "no path";}
			else{
				for (int i = 0; i < b.path.size(); i++) {			
				path += b.path.get(i) + "-";
				}
				path = path.substring(0, path.length()-1);
			}
			 try {
			      FileWriter myWriter = new FileWriter("output.txt");
			      myWriter.write(path + "\n");
			      myWriter.write("Num: " + b.num_node_generated+ "\n");
			      myWriter.write("Cost: " + b.cost + "\n");
			      if(with_time) { myWriter.write(b.time + " seconds" + "\n");}
			      myWriter.close();
			    } catch (IOException e) {
			      e.printStackTrace();
			    }
			break;
		case "DFID":
			System.out.println("three");
			break;
		case "A*":
			System.out.println("one");
			break;
		case "IDA*":
			System.out.println("three");
			break;
		case "DFBnB":
			System.out.println("three");
			break;
		default:
			System.out.println("no match");
		}


		
		
		
		
		
/*
		System.out.println(Support.is_state_equals(initial,goaln));
		System.out.println(Support.h(initial,goaln,num_empty_tiles));
		ArrayList<Node> operators = new ArrayList<>();
		operators = Support.make_operators(initial);
		String[][] mmm;
		for (int k = 0; k < operators.size(); k++) {
			System.out.println(operators.get(k).to_string());
		}


						
						for(int i = 0 ; i < rows_num ; i++) {
							for(int j = 0 ; j < colls_num ; j++) {
								System.out.print(start[i][j] + " ");
							}
							System.out.println();
						}
						System.out.println();
				
						for(int i = 0 ; i < rows_num ; i++) {
							for(int j = 0 ; j < colls_num ; j++) {
								System.out.print(goal[i][j] + " ");
							}
							System.out.println();
						} 
*/

	}
}

