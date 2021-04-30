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
		/*TODO
		 * organize graph based on results of file
		 * sort from base to solution
		 * 
		*/
		ArrayList<String> naming = names(args[0]);
		StringBuilder result = new StringBuilder();
		for(int i = naming.size() - 1; i >= 0; i--) {
			result.append(naming.get(i) + "\n");
		}
		
		return result.toString();
	}
	
	public static ArrayList<String> names(String file) throws IOException{
		Graph<String> names = new Graph<>();
		String goal = "neil armstrong";
		//get file reader and first line
		BufferedReader scan = new BufferedReader(new FileReader(file));
		String line = scan.readLine().toLowerCase();
		String start = line;
		//get number of name pairs
		line = scan.readLine();
		Integer size = Integer.parseInt(line);
		//get rest of names and add them to graph
		for(int i = 0; i < size; i++) {
			//get next line
			line = scan.readLine();
			//get index to split string
			int dash = line.indexOf("-");
			//get the names before and after arrow
			String first = line.substring(0, dash - 1);
			String second = line.substring(dash + 3, line.length() - 1);
			//if left to right then first point to second, else reverse
			if(line.charAt(dash + 1) == '>')
				names.addEdge(first.toLowerCase(), second.toLowerCase());
			else
				names.addEdge(second.toLowerCase(), first.toLowerCase());
		}
		
		ArrayList<String> result = new ArrayList<>(names.breadthFirstSearch(goal, start));
		
		return result;
	}

}
