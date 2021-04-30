package Academy;

/**
 * 
 * @author Evan Taylor, & Noah Jaussi
 * @version March 15, 2021
 */
public class Edge<T> {

	//vertex destination of edge
	private Vertex<T> dst;

	/**
	 * Creates an Edge object, given the Vertex that is the destination.
	 * 
	 * @param dst - the destination Vertex
	 */
	public Edge(Vertex<T> dst) {
		this.dst = dst;
	}

	/**
	 * @return the destination Vertex of this Edge
	 */
	public Vertex<T> getOtherVertex() {
		return this.dst;
	}

	/**
	 * Returns the value of the edge as a string
	 */
	public String toString() {
		return this.dst.getName().toString();
	}
}
