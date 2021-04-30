/**
 * 
 */
package Academy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/**
 * @author Noah
 *
 */

public class Graph<T> {

	// the graph -- a set of vertices (String name mapped to Vertex instance)
	private HashMap<T, Vertex<T>> vertices;
	private T destData;
	private boolean stopRecursion;
	
	/**
	 * Constructs an empty graph.
	 */
	public Graph() {
		vertices = new HashMap<T, Vertex<T>>();
	}

	/**
	 * Adds to the graph a directed edge from the vertex with name "name1" 
	 * to the vertex with name "name2".  (If either vertex does not already 
	 * exist in the graph, it is added.)
	 * 
	 * @param name1 - string name for source vertex
	 * @param name2 - string name for destination vertex
	 */
	public void addEdge(T name1, T name2) {
		Vertex<T> vertex1;
		// if vertex already exists in graph, get its object
		if(vertices.containsKey(name1))
			vertex1 = vertices.get(name1);
		// else, create a new object and add to graph
		else {
			vertex1 = new Vertex<T>(name1);
			vertices.put(name1, vertex1);
		}

		Vertex<T> vertex2;
		if(vertices.containsKey(name2))
			vertex2 = vertices.get(name2);
		else {
			vertex2 = new Vertex<T>(name2);
			vertices.put(name2, vertex2);
		}

		// add new directed edge from vertex1 to vertex2
		vertex1.addEdge(vertex2);
		vertex2.setIndegree(vertex2.getIndegree() + 1);
	}
	
	/**
	 * Generates the DOT encoding of this graph as string, which can be 
	 * pasted into http://www.webgraphviz.com to produce a visualization.
	 */
	public String generateDot() {
		StringBuilder dot = new StringBuilder("digraph d {\n");
		
		// for every vertex 
		for(Vertex<T> v : vertices.values()) {
			// for every edge
			Iterator<Edge<T>> edges = v.edges();
			while(edges.hasNext()) 
				dot.append("\t" + v.getName() + " -> " + edges.next() + "\n");
			
		}
		
		return dot.toString() + "}";
	}
	
	/**
	 * Generates a simple textual representation of this graph.
	 */
	public String toString() {
		StringBuilder result = new StringBuilder();
		
		for(Vertex<T> v : vertices.values()) 
			result.append(v + "\n");
		
		return result.toString();
	}
	
	/**
	 * main topological sort method to sort a graph
	 * 
	 * @return sorted list
	 */
	public List<T> toposort(){
		Queue<Vertex<T>> toVisit = new LinkedList<>();
		for(Map.Entry<T, Vertex<T>> x : vertices.entrySet()) {
			if(x.getValue().getIndegree() == 0) {
				toVisit.offer(x.getValue());
			}
		}
		
		ArrayList<T> sortedList = new ArrayList<>();
		
		while(!toVisit.isEmpty()) {
			Vertex<T> x = toVisit.poll();
			sortedList.add(x.getName());
			Iterator<Edge<T>> edges = x.edges();
			while(edges.hasNext()) {
				Edge<T> e = edges.next();
				Vertex<T> w = e.getOtherVertex();
				w.setIndegree(w.getIndegree() - 1);
				if(w.getIndegree() == 0) {
					toVisit.offer(w);
				}
			}
		}

		if(sortedList.size() < vertices.size()) {
			throw new IllegalArgumentException();
		}
				
		return sortedList;
	}
	
	/**
	 * performs main breadth search logic
	 * 
	 * @param start data
	 * @param destination data
	 * @return shortest list from point start to destination
	 */
	public List<T> breadthFirstSearch(T start, T destData){
		Queue<Vertex<T>> toVisit = new LinkedList<>();
		Vertex<T> startVertex = vertices.get(start);
		
		toVisit.offer(startVertex);
		startVertex.setDistance(0);
		
		while(!toVisit.isEmpty()) {
			Vertex<T> x = toVisit.poll();
			Iterator<Edge<T>> edges = x.edges();
			while(edges.hasNext()) {
				Edge<T> e = edges.next();
				Vertex<T> w = e.getOtherVertex();
				if (w.getDistance() == -1) {
					w.setDistance(x.getDistance() + 1);
					w.setPrevious(x);
					toVisit.offer(w);
				}
			}
		}

		//convert the queue to a list we can return
		ArrayList<T> finalList = new ArrayList<T>();
		Vertex<T> current = vertices.get(destData);
		while(current != null && current.getDistance() >= 0) {
			finalList.add(0, current.getName());
			current = current.getPrevious();
		}
		//if destination was never reached from start
		if(vertices.get(destData).getDistance() == -1) {
			throw new IllegalArgumentException();
		}
		
		return finalList;
	}
	
	
	/**
	 * Helper method to perform main depth first search
	 * 
	 * @param graph to search
	 * @param starting vertex value
	 * @param destination vertex values
	 */
	public boolean depthFirstSearch(T start, T destData) {
		if(start.equals(destData))
			return true;
		if(!vertices.containsKey(start) || !vertices.containsKey(destData))
			throw new IllegalArgumentException();
		this.destData = destData;
		Vertex<T> startVertex = vertices.get(start);
		startVertex.setVisited(true);
		stopRecursion = false;
		
		depthRecursive(startVertex);
		return stopRecursion;
	}
	/**
	 * performs main depth search recursion
	 * @param current vertex
	 */
	private void depthRecursive(Vertex<T> curr) {
		if(!stopRecursion) {
			Iterator<Edge<T>> iter = curr.edges();
			while(iter.hasNext()) {
				Edge<T> x = iter.next();
				//if we have found the destination return true
				if(x.getOtherVertex().getName().equals(destData)) {
					stopRecursion = true;
				} 
				//if we haven't visited edge x already
				if(!x.getOtherVertex().isVisited()) {
					//set vertex visited flag to true
					x.getOtherVertex().setVisited(true);
					//call search again on next vertex
					depthRecursive(x.getOtherVertex());
				}
			}
		}
	}
}
