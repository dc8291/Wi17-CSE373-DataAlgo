import java.util.*;

/**
 * A representation of a graph.
 * Assumes that we do not have negative cost edges in the graph.
 */
public class MyGraph implements Graph {
    // you will need some private fields to represent the graph
    // you are also likely to want some private helper methods

	private final Map<Vertex, Collection<Edge>> graph;
	
    /**
     * Creates a MyGraph object with the given collection of vertices
     * and the given collection of edges.
     * @param v a collection of the vertices in this graph
     * @param e a collection of the edges in this graph
     */
    public MyGraph(Collection<Vertex> v, Collection<Edge> e) {
    	if(v == null || e == null){
    		System.out.println("Null Collections");
    		throw new IllegalArgumentException();
    	}
    	this.graph = new HashMap<Vertex, Collection<Edge>>();
    	for (Vertex vertex : v){
			Collection<Edge> edgeSet = new HashSet<Edge>(); 
    		for (Edge edge : e){
    			Vertex source = edge.getSource();
    			
    			// Check for negative weight
    			if (edge.getWeight() < 0){
    				System.out.println("No negative weight");
    				throw new IllegalArgumentException(); 
    			}
    			
    			// Check if source vertex exists
    			if (!v.contains(source)){
    				System.out.println("No such vertex");
    				throw new IllegalArgumentException();
    			}
    			
    			// Check for duplicate edges with different weight
				if (source.equals(vertex)){
					if(this.graph.get(vertex) != null){
						for (Edge tempEdge : this.graph.get(vertex)){
							if(tempEdge.getWeight() != edge.getWeight()){
								System.out.println("Illegal Edge Weight");
								throw new IllegalArgumentException();
							}
						}
					}
    				edgeSet.add(edge); 
    			}
    		}
    		this.graph.put(vertex, edgeSet);
    	}
    }
    
    /** 
     * Return the collection of vertices of this graph
     * @return the vertices as a collection (which is anything iterable)
     */
    public Collection<Vertex> vertices() {
    	Collection<Vertex> key = new HashSet<Vertex>();
    	for(Vertex v : this.graph.keySet()){
    		key.add(v);
    	}
    	return key;
    }

    /** 
     * Return the collection of edges of this graph
     * @return the edges as a collection (which is anything iterable)
     */
    public Collection<Edge> edges() {
    	Collection<Edge> value = new HashSet<Edge>();
    	for(Collection<Edge> edge : this.graph.values()){
    		value.addAll(edge);
    	}
    	return value;
    }

    /**
     * Return a collection of vertices adjacent to a given vertex v.
     *   i.e., the set of all vertices w where edges v -> w exist in the graph.
     * Return an empty collection if there are no adjacent vertices.
     * @param v one of the vertices in the graph
     * @return an iterable collection of vertices adjacent to v in the graph
     * @throws IllegalArgumentException if v does not exist.
     */
    public Collection<Vertex> adjacentVertices(Vertex v) {
    	Collection<Edge> e = this.graph.get(v);
    	Collection<Vertex> vertex = new HashSet<Vertex>();
    	if(e == null){
    		System.out.println("No adjacent vertices");
    		throw new IllegalArgumentException();
    	}
    	for(Edge edge : e){
    		vertex.add(edge.getDestination());
    	}
    	return vertex; 
    }

    /**
     * Test whether vertex b is adjacent to vertex a (i.e. a -> b) in a directed graph.
     * Assumes that we do not have negative cost edges in the graph.
     * @param a one vertex
     * @param b another vertex
     * @return cost of edge if there is a directed edge from a to b in the graph, 
     * return -1 otherwise.
     * @throws IllegalArgumentException if a or b do not exist.
     */
    public int edgeCost(Vertex a, Vertex b) {
    	
    	// Check if graph contains both vertices
    	if(!this.graph.containsKey(a) || !this.graph.containsKey(b)){
    		System.out.println("Vertex does not exist");
    		throw new IllegalArgumentException();
    	}
    	Collection<Edge> e = this.graph.get(a);
    	for (Edge edge : e){
    		if(edge.getDestination().equals(b)){
    			return edge.getWeight();
    		}
    	}
    	return -1; 
    }
}
