package Academy;

import java.util.ArrayList;
import java.util.List;

public class Vertex {
	
	private String name;
	private List<Vertex> indegrees;
	private List<Vertex> connections;
	
	public Vertex(String value){
		this.name = value;
		connections = new ArrayList<>();
		indegrees = new ArrayList<>();
	}
	/**
	 * custom equals method to check if values are the same between vertices
	 * @param vertex comparing with
	 * @return	true if vertex values are the same
	 */
	public boolean equals(Vertex other) {
		return this.name == other.getName();
	}
	/**
	 * method to get value at this vertex
	 * @return
	 */
	public String getName() {
		return name;
	}
	/**
	 * method to get all the connections from this vertex
	 * @return list of vertices this vertex goes to
	 */
	public List<Vertex> getConnections() {
		return connections;
	}
	/**
	 * method to add a connection to the given vertex
	 * @param vertex to connect
	 */
	public void addConnection(Vertex another) {
		this.connections.add(another);
	}
	/**
	 * method to remove a connection from given vertex
	 * @param vertex connection to remove
	 */
	public void removeConnection(Vertex loserName) {
		this.connections.remove(loserName);
	}
	/**
	 * method to get list of indegrees for vertex
	 * @return list of indegrees for vertex
	 */
	public List<Vertex> getIndegrees() {
		return indegrees;
	}
	/**
	 * method to add indegree to current vertex
	 * @param Indegree Vertex to add
	 */
	public void addIndegree(Vertex comingHome) {
		this.indegrees.add(comingHome);
	}
	/**
	 * method to remove given indegree
	 * @param Vertex to remove
	 */
	public void removeIndegree(Vertex loserName) {
		this.indegrees.remove(loserName);
	}

}
