import java.util.*;

public class MyClient {

	public static void main(String[] args) {
		Collection<Vertex> v = makeVertices(5);
		Collection<Edge> e = makeEdges();
		MyGraph mg = new MyGraph(v,e); 
		System.out.println(mg.vertices());
		System.out.println(mg.edges());
		// System.out.println(mg.adjacentVertices(new Vertex("Vertex 1")));
		// System.out.println(mg.edgeCost(new Vertex("Vertex 1"), new Vertex("Vertex 2")));
	}
	
	private static Collection<Vertex> makeVertices(int num){
		Collection<Vertex> temp = new HashSet<Vertex>();
		for(int i = 1; i < num + 1; i++){
			Vertex v = new Vertex("Vertex " + i);
			temp.add(v);
		}
		// temp.add(new Vertex("Vertex 1"));
		return temp;
	}
	
	private static Collection<Edge> makeEdges(){
		Collection<Edge> temp = new HashSet<Edge>();
		Vertex v1 = new Vertex("Vertex 1");
		Vertex v2 = new Vertex("Vertex 2");
		Vertex v3 = new Vertex("Vertex 3");
		Vertex v4 = new Vertex("Vertex 4");
		Vertex v5 = new Vertex("Vertex 5");
		Vertex v6 = new Vertex("Vertex 6");
		temp.add(new Edge(v1,v2, 3));
		temp.add(new Edge(v3,v5, 4));
		temp.add(new Edge(v3,v2, 1));
		temp.add(new Edge(v2,v4, 3));
		temp.add(new Edge(v1,v3, 2));
		temp.add(new Edge(v4,v2, 5));
		temp.add(new Edge(v4,v2, 7));
		return temp; 
	}
}