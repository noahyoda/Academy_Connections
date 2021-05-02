/**
 * 
 */
package Academy;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


/**
 * @author Noah Jaussi
 *
 */
public class Solution {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static String Connection(String[] args) throws IOException {
		ArrayList<String> naming = names(args[0]);
		StringBuilder result = new StringBuilder();
		for(int i = naming.size() - 1; i >= 0; i--) {
			result.append(naming.get(i) + "\n");
		}
		
		return result.toString();
	}
	
	public static ArrayList<String> names(String file) throws IOException{
		Graph names = new Graph();
		String goal = "neil armstrong";
		//get file reader and first line
		BufferedReader scan = new BufferedReader(new FileReader(file));
		String line = scan.readLine().toLowerCase();
		String start = line;
		//get number of name pairs
		line = scan.readLine();
		Integer size = Integer.parseInt(line);
		line = scan.readLine();
		//get rest of names and add them to graph
		for(int i = 0; i < size && line != null; i++) {
			//get index to split string
			int dash = line.indexOf("-");
			//get the names before and after arrow
			String first = line.substring(0, dash - 1).toLowerCase();
			String second = line.substring(dash + 3, line.length()).toLowerCase();
			Vertex aPoint = new Vertex(first);
			Vertex bPoint = new Vertex(second);
			//if left to right then first point to second, else reverse
			if(line.charAt(dash + 1) == '>')
				names.addConnection(aPoint, bPoint);
			else
				names.addConnection(bPoint, aPoint);
			//get next line
			line = scan.readLine();
		}
		ArrayList<Vertex> result = names.search(start, goal);
		ArrayList<String> lineage = new ArrayList<>();
		for(Vertex x : result) {
			lineage.add(x.getName());
		}

		return lineage;
	}

}
