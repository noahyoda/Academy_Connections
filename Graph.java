package Academy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Basic Graph class to solve problem in Solution fileS
 * @author Noah Jaussi
 * 
 * TODO:
 * add search (breadth-first)
 * optimize with hashset instead of list for vertices
 * add illegal argument exceptions (maybe)
 */

public class Graph {

	private List<Vertex> vertices;
	
	/**
	 * basic constructor with no parameters
	 */
	Graph(){
		vertices = new ArrayList<>();
	}
	/**
	 * add vertex to graph with no prior connections
	 * @param vertex to add
	 */
	public void addVertex(Vertex newHomie) {
		//if newHomie doesn't already exist add
		if(!vertices.contains(newHomie))
			vertices.add(newHomie);
	}
	/**
	 * connect two vertices in the graph. If they don't exist already then
	 * they will be added as new vertices to the graph
	 * 
	 * @param Vertex connecting
	 * @param Vertex being connected to
	 */
	public void addConnection(Vertex start, Vertex end) {
		//get indices of vertices
		int sIndex = vertices.indexOf(start);
		int eIndex = vertices.indexOf(end);
		//if vertices were not in list before
		if(sIndex < 0)
			vertices.add(start);
		if(eIndex < 0)
			vertices.add(end);
		//then add connection/indegree
		start.addConnection(end);
		end.addIndegree(start);

	}
	/**
	 * remove a vertex and all references to if from the graph
	 * @param Vertex to remove
	 */
	public void removeVertex(Vertex goner) {
		if(vertices.contains(goner)) {
			//for each indegree remove connection to removed vertex
			for(Vertex parent : goner.getIndegrees()) {
				parent.removeConnection(goner);
			}
			vertices.remove(goner);
		}
	}
	/**
	 * returns a list of all vertices the given vertex is connected to (not indegrees)
	 * @param vertex to check connections to
	 * @return list of vertices connected to vertex
	 */
	public List<Vertex> getConnections(Vertex curr){
		if(!vertices.contains(curr))
			throw new NullPointerException();
		return curr.getConnections();
	}
	/**
	 * perform a breadth-first search to find the shortest unweighted connection
	 * between the two given vertices
	 * 
	 * @param starting vertex
	 * @param goal vertex
	 * @return list of path from beginning to end vertex
	 */
	public List<Vertex> search(Vertex beginning, Vertex end){
		if(vertices.contains(beginning) && vertices.contains(end)) {
			ArrayList<Vertex> paths = new ArrayList<>();
			Queue<Vertex> toVisit = new LinkedList<>();
			
			
			
		}
		return null;
	}
	
}
