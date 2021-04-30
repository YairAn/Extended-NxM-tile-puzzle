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
			//write to the output file
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
			DFID d = new DFID(initial,goaln);
			d.run_dfid();
			//write to the output file
			String pathd = "";
			if(d.path.size() == 0) {pathd = "no path";}
			else{
				for (int i = 0; i < d.path.size(); i++) {			
					pathd += d.path.get(i) + "-";
				}
				pathd = pathd.substring(0, pathd.length()-1);
			}
			try {
				FileWriter myWriter = new FileWriter("output.txt");
				myWriter.write(pathd + "\n");
				myWriter.write("Num: " + d.num_node_generated+ "\n");
				myWriter.write("Cost: " + d.cost + "\n");
				if(with_time) { myWriter.write(d.time + " seconds" + "\n");}
				myWriter.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			break;
		case "A*":
			A_star a = new A_star(initial,goaln,with_open,num_empty_tiles);
			a.run_A_star();
			//a.chek();
			//write to the output file
			String pathA = "";
			if(a.path.size() == 0) {path = "no path";}
			else{
				for (int i = 0; i < a.path.size(); i++) {			
					pathA += a.path.get(i) + "-";
				}
				pathA = pathA.substring(0, pathA.length()-1);
			}
			try {
				FileWriter myWriter = new FileWriter("output.txt");
				myWriter.write(pathA + "\n");
				myWriter.write("Num: " + a.num_node_generated+ "\n");
				myWriter.write("Cost: " + a.cost + "\n");
				if(with_time) { myWriter.write(a.time + " seconds" + "\n");}
				myWriter.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			break;

		case "IDA*":
			IDA_star id = new IDA_star(initial,goaln,num_empty_tiles);
			id.run_IDA();
			//write to the output file
			String pathid = "";
			if(id.path.size() == 0) {pathid = "no path";}
			else{
				for (int i = 1; i < id.path.size(); i++) {			
					pathid += id.path.get(i) + "-";
				}
				pathid = pathid.substring(0, pathid.length()-1);
			}
			try {
				FileWriter myWriter = new FileWriter("output.txt");
				myWriter.write(pathid + "\n");
				myWriter.write("Num: " + id.num_node_generated+ "\n");
				myWriter.write("Cost: " + id.cost + "\n");
				if(with_time) { myWriter.write(id.time + " seconds" + "\n");}
				myWriter.close();
			} catch (IOException e) {
				e.printStackTrace();
			}			
			break;
		case "DFBnB":
			DFBnB df = new DFBnB(initial,goaln,num_empty_tiles);
			df.run_DFBnB();
			//write to the output file
			String pathdf = "";
			if(df.path.size() == 0) {pathdf = "no path";}
			else{
				for (int i = 1; i < df.path.size(); i++) {			
					pathdf += df.path.get(i) + "-";
				}
				pathdf = pathdf.substring(0, pathdf.length()-1);
			}
			try {
				FileWriter myWriter = new FileWriter("output.txt");
				myWriter.write(pathdf + "\n");
				myWriter.write("Num: " + df.num_node_generated+ "\n");
				myWriter.write("Cost: " + df.cost + "\n");
				if(with_time) { myWriter.write(df.time + " seconds" + "\n");}
				myWriter.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			break;
		default:
			ArrayList<Integer> sss = new ArrayList<>();
			sss.add(2);
			sss.add(3);
			sss.add(4);
			sss.add(5);
			sss.add(6);
			sss.add(7);
			sss.add(8);
			sss.add(9);
            sss.subList(3, sss.size()).clear();
            for(int i : sss) {
            	System.out.println(i);
            }
			

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

