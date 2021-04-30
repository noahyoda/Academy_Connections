package Academy;

import java.util.LinkedList;
import java.util.Iterator;

/**
 * This class represents a vertex (AKA node) in a directed graph. The vertex is
 * not generic, assumes that a string name is stored there.
 * 
 * @author Evan Taylor and Noah Jaussi
 * @version March 15, 2021
 */
public class Vertex<T> {

	// used to id the Vertex
	private T value;
	private int distanceFromStart;
	private boolean visited;
	private Vertex<T> previous;
	private int indegree;

	// adjacency list
	private LinkedList<Edge<T>> adj;

	/**
	 * Creates a new Vertex object, using the given name.
	 * 
	 * @param name - string used to identify this Vertex
	 */
	public Vertex(T value) {
		this.value = value;
		this.adj = new LinkedList<Edge<T>>();
		this.visited = false;
		setDistance(-1);
		this.indegree = 0;
	}

	/**
	 * @return the string used to identify this Vertex
	 */
	public T getName() {
		return value;
	}

	/**
	 * Adds a directed edge from this Vertex to another.
	 * 
	 * @param otherVertex - the Vertex object that is the destination of the edge
	 */
	public void addEdge(Vertex<T> otherVertex) {
		adj.add(new Edge<T>(otherVertex));
	}

	/**
	 * @return a iterator for accessing the edges for which this Vertex is the source
	 */
	public Iterator<Edge<T>> edges() {
		return adj.iterator();
	}

	/**
	 * Generates and returns a textual representation of this Vertex.
	 */
	public String toString() {
		String s = "Vertex " + value + " adjacent to vertices ";
		Iterator<Edge<T>> itr = adj.iterator();
		while(itr.hasNext())
			s += itr.next() + "  ";
		return s;
	}

	/**
	 * @return distance from start
	 */
	public int getDistance() {
		return distanceFromStart;
	}

	/**
	 * sets distance from start variable
	 * @param distanceFromStart
	 */
	public void setDistance(int distanceFromStart) {
		this.distanceFromStart = distanceFromStart;
	}

	/**
	 * 
	 * @return visited boolean flag
	 */
	public boolean isVisited() {
		return visited;
	}

	/**
	 * sets the visited boolean flag
	 * @param visited
	 */
	public void setVisited(boolean visited) {
		this.visited = visited;
	}

	/**
	 * 
	 * @return previous vertex
	 */
	public Vertex<T> getPrevious() {
		return previous;
	}

	/**
	 * sets previous vertex
	 * @param previous
	 */
	public void setPrevious(Vertex<T> previous) {
		this.previous = previous;
	}

	/**
	 * sets indegree value
	 * @param indegree
	 */
	public void setIndegree(int indegree) {
		this.indegree = indegree;
	}

	/**
	 * 
	 * @return indegree count
	 */
	public int getIndegree() {
		return this.indegree;
	}
}