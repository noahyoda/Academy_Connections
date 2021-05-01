package Academy;

import java.util.HashMap;

public class Vertex {
	
	private String name;
	private HashMap<String, Vertex> adjacent;
	
	public Vertex(String value){
		this.name = value;
	}
	
	public boolean equals(Vertex other) {
		return this.name == other.getName();
	}

	public String getName() {
		return name;
	}

}
