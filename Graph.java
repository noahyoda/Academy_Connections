package Academy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Basic Graph class to solve problem in Solution fileS
 * @author Noah Jaussi
 * 
 * TODO:
 * add search (breadth-first)
 * add illegal argument exceptions (maybe)
 */

public class Graph {

	private HashSet<Vertex> vertices;
	
	/**
	 * basic constructor with no parameters
	 */
	Graph(){
		vertices = new HashSet<>();
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
//		int sIndex = vertices.indexOf(start);
//		int eIndex = vertices.indexOf(end);
		//if vertices were not in list before
		if(!vertices.contains(start))
			vertices.add(start);
		if(!vertices.contains(end))
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
	
	public ArrayList<Vertex> search(String start, String goal){
		//use iterator to find vertices of start and goal
		Iterator<Vertex> it = vertices.iterator();
		Vertex first = null, last = null;
		while(it.hasNext()) {
			Vertex temp = it.next();
			if(temp.getName().equals(start)) {
				first = temp;
			}
			if(temp.getName().equals(goal)){
				last = temp;
			}
			if(first != null && last != null)
				break;
		}
		//if they don't exist in graph throw exception
		if(first == null || last == null)
			throw new IllegalArgumentException();
		
		return search(first, last);
	}
	/**
	 * perform a breadth-first search to find the shortest un-weighted connection
	 * between the two given vertices
	 * 
	 * @param starting vertex
	 * @param goal vertex
	 * @return list of path from beginning to end vertex
	 * @throws IllegalArgumentException if end is never reached or one of the vertices
	 * isn't in the graph
	 */
	public ArrayList<Vertex> search(Vertex begin, Vertex end){
		if(vertices.contains(begin) && vertices.contains(end)) {
			//initialize queue to track path as we search
			Queue<Vertex> toVisit = new LinkedList<>();
			//add start vertex to queue
			toVisit.offer(begin);
			begin.setDistance(0);
			
			while(!toVisit.isEmpty()) {
				Vertex x = toVisit.poll();
				Iterator<Vertex> connect = x.getConnections().iterator();
				while(connect.hasNext()) {
					Vertex w = connect.next();
					//if we haven't visited w yet
					if(w.getDistance() == -1) {
						w.setDistance(x.getDistance() + 1);
						w.setPrevious(x);
						toVisit.offer(w);
					}
				}
			}
			//convert queue into the list
			ArrayList<Vertex> path = new ArrayList<>();
			Vertex curr = end;
			while(curr != null && curr.getDistance() >= 0) {
				path.add(0, curr);
				curr = curr.getPrevious();
			}
			//if destination was never reached throw exception
			if(end.getDistance() == -1)
				throw new IllegalArgumentException();
			
			return path;
		} else {
			throw new IllegalArgumentException();
		}
	}
	
}
