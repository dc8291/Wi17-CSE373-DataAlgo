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
    
    // Comparator for the PriorityQueue
	public class EdgeComparator implements Comparator<ArrayList<Edge>> {
	
		public int compare(ArrayList<Edge> e1, ArrayList<Edge> e2) {
			int sum1 = 0;
			int sum2 = 0;
			
			for (Edge e : e1) {
				sum1 += e.getWeight();
			}
			
			for (Edge e : e2) {
				sum2 += e.getWeight();
			}
			
			return Integer.compare(sum1, sum2); // Return if first ArrayList is larger than second ArrayList
		}
	
	}
    /**
     * Returns the shortest path from a to b in the graph, or null if there is
     * no such path.  Assumes all edge weights are nonnegative.
     * Uses Dijkstra's algorithm.
     * @param a the starting vertex
     * @param b the destination vertex
     * @return a Path where the vertices indicate the path from a to b in order
     *   and contains a (first) and b (last) and the cost is the cost of 
     *   the path. Returns null if b is not reachable from a.
     * @throws IllegalArgumentException if a or b does not exist.
     */
    public Path shortestPath(Vertex a, Vertex b) {
    	PriorityQueue<ArrayList<Edge>> active = new PriorityQueue<ArrayList<Edge>>(new EdgeComparator());
		HashSet<Vertex> finished = new HashSet<Vertex>();
		
		// Initialize the PriorityQueue with an origin
		ArrayList<Edge> initial = new ArrayList<Edge>();
		initial.add(new Edge(a, a, 0)); // Set the cost of source 0 
		active.add(initial);
		
		
		while (!active.isEmpty()) {
			ArrayList<Edge> minPath = active.remove();
			Vertex minDest = minPath.get(minPath.size() - 1).getDestination();
			
			// If destination is found
			if (minDest.equals(b)) {
				int cost = 0;
				ArrayList<Vertex> vertices = new ArrayList<Vertex>();
				for (Edge e : minPath) {
					vertices.add(e.getDestination());
					cost += e.getWeight();
				}
				return new Path(vertices, cost);
			}
			
			// Destination cannot be reached
			if(finished.contains(minDest)) {
				continue; // Abort Mission!
			}
			
			// Checking adjacent vertices
			for (Vertex v : adjacentVertices(minDest)) {
				if (!finished.contains(v)) { // is it "known"?
					ArrayList<Edge> newPath = new ArrayList<Edge>(minPath);
					newPath.add(new Edge(minDest, v, edgeCost(minDest, v))); // Adding adjacent edges
					active.add(newPath); // Adding to PriorityQueue to find next lowest cost
				}
			}
			finished.add(minDest); // Done looking through all destinations from this vertex
		}
		return new Path(new ArrayList<Vertex>(), -1);
    }
    
    
}
