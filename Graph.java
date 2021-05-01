package Academy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Basic Graph class to solve problem in Solution fileS
 * @author Noah Jaussi
 * 
 * TODO:
 * add search (breadth-first)
 */

public class Graph {

	private HashMap<Vertex, List<Vertex>> connections;
	private HashMap<Vertex, List<Vertex>> indegrees;
	
	Graph(){
		connections = new HashMap<Vertex, List<Vertex>>();
	}
	
	public void addVertex(Vertex newHomie) {
		//if newHomie doesn't already exist
		if(!connections.containsKey(newHomie) || !indegrees.containsKey(newHomie)) {
			
		}
	}
	
	public void addConnection(Vertex start, Vertex end) {
		//if start exist add end to connections, else create new vertex with connection
		if(connections.containsKey(start)) {
			connections.get(start).add(end);
		} else {
			ArrayList<Vertex> adj = new ArrayList<>();
			adj.add(end);
			connections.put(start, adj);
		}
		//if end has no indegrees create new list and add start, else add start to indegree
		if(!indegrees.containsKey(end)) {
			ArrayList<Vertex> adj = new ArrayList<>();
			adj.add(start);
			indegrees.put(end, adj);
		} else {
			indegrees.get(end).add(start);
		}
		
	}
	
	public void removeVertex(Vertex goner) {
		//if goner vertex is in the graph
		if(connections.containsKey(goner) || indegrees.containsKey(goner)) {
			connections.remove(goner);
			indegrees.remove(goner);
		}
	}
	
	public List<Vertex> getConnections(Vertex curr){
		if(!connections.containsKey(curr))
			throw new NullPointerException();
		return connections.get(curr);
	}
	
}
